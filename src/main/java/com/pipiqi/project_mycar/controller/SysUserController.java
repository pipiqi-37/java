package com.pipiqi.project_mycar.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.dto.UserDto;
import com.pipiqi.project_mycar.pojo.SysUser;
import com.pipiqi.project_mycar.service.SysUserService;
import com.pipiqi.project_mycar.utils.MD5Utils;
import com.pipiqi.project_mycar.utils.R;
import com.pipiqi.project_mycar.utils.ShiroUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private DefaultKaptcha kaptcha;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) {
        // 设置缓存 --- 设置为不缓存
        response.setHeader("Cache-Control", "no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        // 生成验证码文本
        String text = kaptcha.createText();
        // 生成验证码图片
        BufferedImage image = kaptcha.createImage(text);

        // 将验证码储存到shiro的session中
        ShiroUtils.setKaptcha(text);

        try {
            // 将图片返回到页面
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDto userDto) {
        // 比对验证码
        String kaptcha = ShiroUtils.getKaptcha();
        if (!kaptcha.equalsIgnoreCase(userDto.getCaptcha())) {
            return R.error("验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        // 对用户输入的密码进行加密
        String pwd = MD5Utils.md5(userDto.getPassword(), userDto.getUsername(), 1024);
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(), pwd);
        if (userDto.isRememberMe()) {
            token.setRememberMe(true);
        }
        // 自动调用realm
        subject.login(token);
        return R.ok();
    }

    @RequestMapping("/logout")
    public String loginOut() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

    @RequestMapping("/sys/user/list")
    @ResponseBody
    public DataGridResult findUserByPage(QueryDto queryDto) {
        return sysUserService.findUserByPage(queryDto);
    }

    @RequestMapping("/sys/user/export")
    public void exportUser(HttpServletResponse response) {
        Workbook workbook = sysUserService.exportUser();
        try {
            // 设置响应头
            response.setContentType("application/octet-stream");
            // 设置文件名称
            String fileName = "员工信息.xls";
            // 编码格式
            fileName = URLEncoder.encode(fileName, "utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            // 文件下载
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 获取用户信息
    @RequestMapping("/sys/user/info")
    @ResponseBody
    public R findUserInfo() {
        SysUser userEntity = ShiroUtils.getUserEntity();
        return R.ok().put("user", userEntity);
    }

}
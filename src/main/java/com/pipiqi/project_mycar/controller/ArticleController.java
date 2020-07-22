package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.ArticleDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Article;
import com.pipiqi.project_mycar.service.ArticleService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/sys/article/list")
    @ResponseBody
    public DataGridResult findList(QueryDto queryDto) {
        return articleService.findByPage(queryDto);
    }

    @RequestMapping("/sys/article/del")
    @ResponseBody
    public R delArticle(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            articleService.delArt(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/article/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Long id) {
        Article byId = articleService.findById(id);
        return R.ok().put("article", byId);
    }

    @RequestMapping("/sys/article/save")
    @ResponseBody
    public R addArticle(@RequestBody ArticleDto articleDto) {
        int i = articleService.addArticle(articleDto);
        return i > 0 ? R.ok():R.error("新增失败");
    }

    @RequestMapping("/sys/article/update")
    @ResponseBody
    public R updateArticle(@RequestBody ArticleDto articleDto) {
        int i = articleService.updateArticle(articleDto);
        return i > 0 ? R.ok():R.error("修改失败");
    }

    @RequestMapping("/ytupload")
    @ResponseBody
    public R upload(@RequestParam("mypic") MultipartFile multipartFile, HttpServletRequest httpServletRequest) {
        // 获得文件名称
        String filename = multipartFile.getOriginalFilename();
        // 上传目的地， 不做分布式，只存储到本地,  不考虑文件名称重复问题
        File dest = new File("D:\\java" + filename);
        try {
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("file", filename);
    }
}

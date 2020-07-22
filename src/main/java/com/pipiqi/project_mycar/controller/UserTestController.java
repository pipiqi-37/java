package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.pojo.SysUser;
import com.pipiqi.project_mycar.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserTestController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/findAll")
    @ResponseBody
//    @RequiresRoles("qf")
    public List<SysUser> findAll() {
//        int i = 1 / 0;
        return sysUserService.findAll();
    }
}

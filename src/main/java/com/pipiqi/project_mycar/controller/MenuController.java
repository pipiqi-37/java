package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysMenu;
import com.pipiqi.project_mycar.service.MenuService;
import com.pipiqi.project_mycar.utils.R;
import com.pipiqi.project_mycar.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/sys/menu/list")
    @ResponseBody
    public DataGridResult findMenu(QueryDto queryDto) {
        return menuService.findMenu(queryDto);
    }

    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenu(@RequestBody List<Long> ids) {

        return menuService.deleteMenu(ids);
    }

    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public R findMenu() {
        return menuService.findMenu();
    }

    @RequestMapping("/sys/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody SysMenu sysMenu) {
        return menuService.saveMenu(sysMenu);
    }

    @RequestMapping("/sys/menu/info/{menuId}")
    @ResponseBody
    public R findMenuById(@PathVariable("menuId") Long menuId) {
        return menuService.findMenuById(menuId);
    }

    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R updateMenu(@RequestBody SysMenu sysMenu) {
        return menuService.updateMenu(sysMenu);
    }

    @RequestMapping("/sys/menu/user")
    @ResponseBody
    public R findUserMenu() {
        long userId = ShiroUtils.getUserId();
        return menuService.findUserMenu(userId);
    }

}


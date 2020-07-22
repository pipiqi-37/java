package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.SysMenuMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysMenu;
import com.pipiqi.project_mycar.service.MenuService;
import com.pipiqi.project_mycar.utils.R;
import com.pipiqi.project_mycar.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    public SysMenuMapper sysMenuMapper;

    @Override
    public DataGridResult findMenu(QueryDto queryDto) {
        // 分页
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        // 设置排序字段
        if (queryDto.getSort() != null && !queryDto.getSort().equals("")) {
            queryDto.setSort("menu_id");
        }
        // 获得查询结果集
        List<SysMenu> menuByPage = sysMenuMapper.findMenuByPage(queryDto);
        // 封装
        PageInfo<SysMenu> info = new PageInfo<SysMenu>(menuByPage);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, info.getList());
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public R deleteMenu(List<Long> ids) {
        for (Long id : ids) {
            if (id < 51) {
                return R.error("系统菜单，不支持删除");
            }
        }

        int i = sysMenuMapper.deleteMenu(ids);
        if (i > 0) {
            return R.ok();
        } else {
            return R.error(-200, "删除失败");
        }
    }

    @Override
    public R findMenu() {
        // 查出来的目录   包含 0  目录   1  菜单
        List<SysMenu> menu = sysMenuMapper.findMenu();
        // 添加一个根目录
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(0L);
        sysMenu.setType(0);
        sysMenu.setParentId(-1L);
        sysMenu.setName("一级菜单");
        menu.add(sysMenu);
        return R.ok().put("menuList", menu);
    }

    @Override
    public R saveMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.insertSelective(sysMenu);
        return i > 0 ? R.ok() : R.error("新增失败!");
    }

    @Override
    public R findMenuById(Long menuId) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
        return R.ok().put("menu", sysMenu);
    }

    @Override
    public R updateMenu(SysMenu sysMenu) {
        int i = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return i > 0 ? R.ok() : R.error("修改失败!");
    }

    @Override
    public List<String> findPermsByUserId(Long userId) {
        // 查询出用户所有权限
        List<String> permsByUserId = sysMenuMapper.findPermsByUserId(userId);
        // 创建集合 将用户的多权限储存到集合
        Set<String> set = new HashSet<String>();
        for (String s : permsByUserId) {
            if (s != null && !s.equals("")) {
                String[] split = s.split(",");
                for (String s1 : split) {
                    set.add(s1);
                }
            }
        }
        // 返回List类型数据
        List<String> perms = new ArrayList<>();
        perms.addAll(set);
        return perms;
    }

    @Override
    public R findUserMenu(Long userId) {
        // 查询用户的一级目录
        List<Map<String, Object>> dirMenuByUserId = sysMenuMapper.findDirMenuByUserId(userId);
        // 查询目录对应的子菜单
        for (Map<String, Object> map: dirMenuByUserId) {
            Long menuId = Long.parseLong(map.get("menuId") + "");
            // 查询一级菜单对应的菜单
            List<Map<String, Object>> subList = sysMenuMapper.findMenuNotButtonByUserId(userId, menuId);
            // 将菜单添加到页面
            map.put("list", subList);
        }
        List<String> permsByUserId = sysMenuMapper.findPermsByUserId(userId);
        return R.ok().put("menuList", dirMenuByUserId).put("permissions", permsByUserId);
    }


}

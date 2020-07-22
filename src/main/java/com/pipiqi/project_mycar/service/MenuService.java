package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysMenu;
import com.pipiqi.project_mycar.utils.R;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {

    public DataGridResult findMenu(QueryDto queryDto);

    public R deleteMenu(List<Long> ids);

    // 静态目录 前期用于测试
    public R findMenu();

    public R saveMenu(SysMenu sysMenu);

    public R findMenuById( Long menuId);

    public R updateMenu(SysMenu sysMenu);

    public List<String> findPermsByUserId(Long userId);

    public R findUserMenu(Long userId);
}

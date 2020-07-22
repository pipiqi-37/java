package com.pipiqi.project_mycar.dao;

import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysMenu;
import com.pipiqi.project_mycar.pojo.SysMenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(Long menuId);

//    int deleteMenu(List<Long> ids);

    int deleteMenu(List<Long> ids);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findMenuByPage(QueryDto queryDto);

    // 菜单
    List<SysMenu> findMenu();

    // 根据用户ID返回用户权限
    List<String> findPermsByUserId(@Param("userId") Long userId);

    // 查询菜单一级目录
    List<Map<String, Object>> findDirMenuByUserId(@Param("userId") Long userId);

    // 一级目录对应的菜单
    List<Map<String, Object>> findMenuNotButtonByUserId(@Param("userId") Long userId, @Param("parentId") Long parentId);

}
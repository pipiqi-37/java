package com.pipiqi.project_mycar.service;



import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.SysUser;
import org.apache.poi.ss.usermodel.Workbook;


import java.util.List;

public interface SysUserService {
    public List<SysUser> findAll();

    public DataGridResult findUserByPage(QueryDto queryDto);

    // 导出
    public Workbook exportUser();

    // 根据用户名 查询用户信息
    public SysUser findUserInfo(String username);
}

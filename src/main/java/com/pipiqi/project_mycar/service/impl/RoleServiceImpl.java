package com.pipiqi.project_mycar.service.impl;

import com.pipiqi.project_mycar.dao.SysRoleMapper;
import com.pipiqi.project_mycar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findRolesByUserId(Long userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }
}

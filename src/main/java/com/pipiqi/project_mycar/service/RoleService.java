package com.pipiqi.project_mycar.service;

import java.util.List;

public interface RoleService {

    public List<String> findRolesByUserId(Long userId);
}

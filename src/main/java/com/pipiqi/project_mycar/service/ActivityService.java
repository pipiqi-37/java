package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.ActivityDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;



public interface ActivityService {

    public int addActivity(ActivityDto activityDto);

    public void delActivity(Long id);

    public int updateActivity(ActivityDto activityDto);

    public ActivityDto findById(Long id);

    public DataGridResult findByPage(QueryDto queryDto);
}

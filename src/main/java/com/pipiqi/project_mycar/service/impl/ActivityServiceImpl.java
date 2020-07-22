package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.ActivityMapper;
import com.pipiqi.project_mycar.dto.ActivityDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.ActivityExample;
import com.pipiqi.project_mycar.pojo.ActivityWithBLOBs;
import com.pipiqi.project_mycar.service.ActivityService;
import com.pipiqi.project_mycar.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int addActivity(ActivityDto activityDto) {
        ActivityWithBLOBs record = new ActivityWithBLOBs();
        BeanUtils.copyProperties(activityDto, record);
        // 解决数据库字段不一致问题
        try {
            Long beginTime = DateUtils.strToLong(activityDto.getBeginTime());
            Long endTime = DateUtils.strToLong(activityDto.getEndTime());
            record.setBeginTime(beginTime);
            record.setEndTime(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return activityMapper.insertSelective(record);
    }

    @Override
    public void delActivity(Long id) {
        activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateActivity(ActivityDto activityDto) {
        ActivityWithBLOBs record = new ActivityWithBLOBs();
        BeanUtils.copyProperties(activityDto, record);
        // 解决数据库字段不一致问题
        try {
            Long beginTime = DateUtils.strToLong(activityDto.getBeginTime());
            Long endTime = DateUtils.strToLong(activityDto.getEndTime());
            record.setBeginTime(beginTime);
            record.setEndTime(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return activityMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public ActivityDto findById(Long id) {
        ActivityWithBLOBs activityWithBLOBs = activityMapper.selectByPrimaryKey(id);
        ActivityDto activityDto = new ActivityDto();
        BeanUtils.copyProperties(activityWithBLOBs, activityDto);
        activityDto.setBeginTime(DateUtils.longToStr(activityWithBLOBs.getBeginTime()));
        activityDto.setEndTime(DateUtils.longToStr(activityWithBLOBs.getEndTime()));
        return activityDto;
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        List<ActivityDto> list = new ArrayList<>();
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        ActivityExample example = new ActivityExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<ActivityWithBLOBs> activityWithBLOBs = activityMapper.selectByExampleWithBLOBs(example);
        for (ActivityWithBLOBs activityWithBLOB : activityWithBLOBs) {
            ActivityDto activityDto = new ActivityDto();
            BeanUtils.copyProperties(activityWithBLOB, activityDto);
            activityDto.setBeginTime(DateUtils.longToStr(activityWithBLOB.getBeginTime()));
            activityDto.setEndTime(DateUtils.longToStr(activityWithBLOB.getEndTime()));
            list.add(activityDto);
        }
        PageInfo<ActivityWithBLOBs> info = new PageInfo<>(activityWithBLOBs);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, list);
        return result;
    }
}

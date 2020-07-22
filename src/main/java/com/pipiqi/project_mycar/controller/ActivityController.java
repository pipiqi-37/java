package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.ActivityDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.service.ActivityService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/sys/activity/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return activityService.findByPage(queryDto);
    }

    @RequestMapping("/sys/activity/del")
    @ResponseBody
    public R delActivity(@RequestBody List<Long> ids) {
        for (Long id : ids) {
            activityService.delActivity(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/activity/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Long id) {
        ActivityDto byId = activityService.findById(id);
        return R.ok().put("activity", byId);
    }

    @RequestMapping("/sys/activity/save")
    @ResponseBody
    public R addActivity(@RequestBody ActivityDto activityDto) {
        int i = activityService.addActivity(activityDto);
        return i > 0 ? R.ok() : R.error("新增失败!");
    }

    @RequestMapping("/sys/activity/update")
    @ResponseBody
    public R updateActivity(@RequestBody ActivityDto activityDto) {
        int i = activityService.updateActivity(activityDto);
        return i > 0 ? R.ok() : R.error("修改失败!");
    }

}

package com.pipiqi.project_mycar.controller;


import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarMake;
import com.pipiqi.project_mycar.service.CarMakeService;
import com.pipiqi.project_mycar.utils.R;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarMakeController {

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping("/sys/make/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return carMakeService.findByPage(queryDto);
    }

    @RequestMapping("/sys/make/del")
    @ResponseBody
    public R delCarMake(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carMakeService.delCarMake(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/make/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        CarMake byId = carMakeService.findById(id);
        return R.ok().put("make", byId);
    }

    @RequestMapping("/sys/make/save")
    @ResponseBody
    public R addCarMake(@RequestBody CarMake carMake) {
        int i = carMakeService.addCarMake(carMake);
        return i > 0 ? R.ok() : R.error("新增失败");
    }

    @RequestMapping("/sys/make/update")
    @ResponseBody
    public R updateCarMake(@RequestBody CarMake carMake) {
        int i = carMakeService.updateCarMake(carMake);
        return i > 0 ? R.ok() : R.error("修改失败");
    }

}

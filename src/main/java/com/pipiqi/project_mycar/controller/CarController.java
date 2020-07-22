package com.pipiqi.project_mycar.controller;


import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Car;
import com.pipiqi.project_mycar.pojo.CarMake;
import com.pipiqi.project_mycar.service.CarMakeService;
import com.pipiqi.project_mycar.service.CarService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping("/sys/car/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return carService.findByPage(queryDto);
    }

    @RequestMapping("/sys/car/del")
    @ResponseBody
    public R delCar(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carService.dalCar(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/car/typeall")
    @ResponseBody
    public R finAll() {
        List<CarMake> all = carMakeService.findAll();
        return R.ok().put("sites", all);
    }

    @RequestMapping("/sys/car/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        Car byId = carService.findById(id);
        return R.ok().put("car", byId);
    }

    @RequestMapping("/sys/car/save")
    @ResponseBody
    public R addCar(@RequestBody Car car) {
        int i = carService.addCar(car);
        return i > 0 ? R.ok() : R.error("新增失败！");
    }


    @RequestMapping("/sys/car/update")
    @ResponseBody
    public R updateCar(@RequestBody Car car) {
        int i = carService.updateCar(car);
        return i > 0 ? R.ok() : R.error("修改失败！");
    }

}

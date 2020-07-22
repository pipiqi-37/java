package com.pipiqi.project_mycar.controller;


import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarMake;
import com.pipiqi.project_mycar.pojo.CarManufacturer;
import com.pipiqi.project_mycar.pojo.CarParamType;
import com.pipiqi.project_mycar.pojo.CarParams;
import com.pipiqi.project_mycar.service.CarMakeService;
import com.pipiqi.project_mycar.service.CarManufacturerService;
import com.pipiqi.project_mycar.service.CarParamTypeService;
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
public class CarManufacturerController {

    @Autowired
    private CarManufacturerService carManufacturerService;

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping("/sys/manufacturer/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return carManufacturerService.findByPage(queryDto);
    }
    @RequestMapping("/sys/manufacturer/del")
    @ResponseBody
    public R delCarManufacturer(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carManufacturerService.delCarManufacturer(id);
        }
        return R.ok();
    }
    @RequestMapping("/sys/manufacturer/typeall")
    @ResponseBody
    public R findAll() {
        List<CarMake> all = carMakeService.findAll();
        return R.ok().put("sites", all);
    }

    @RequestMapping("/sys/manufacturer/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        CarManufacturer byId = carManufacturerService.findById(id);
        return R.ok().put("manufacturer", byId);
    }

    @RequestMapping("/sys/manufacturer/save")
    @ResponseBody
    public R addCarParamType(@RequestBody CarManufacturer carManufacturer) {
        int i = carManufacturerService.addCarManufacturer(carManufacturer);
        return i >0 ? R.ok():R.error("新增失败");
    }

    @RequestMapping("/sys/manufacturer/update")
    @ResponseBody
    public R updateCarParamType(@RequestBody CarManufacturer carManufacturer) {
        int i = carManufacturerService.updateCarManufacturer(carManufacturer);
        return i >0 ? R.ok():R.error("修改失败");
    }

}

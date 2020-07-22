package com.pipiqi.project_mycar.controller;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarMake;
import com.pipiqi.project_mycar.pojo.CarModel;
import com.pipiqi.project_mycar.service.CarMakeService;
import com.pipiqi.project_mycar.service.CarModelService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarMakeService carMakeService;

    @RequestMapping("/sys/model/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return carModelService.findByPage(queryDto);
    }

    @RequestMapping("/sys/model/del")
    @ResponseBody
    public R delCarModel(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carModelService.delCarModel(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/model/typeall")
    @ResponseBody
    public R findAll() {
        List<CarMake> all = carMakeService.findAll();
        return R.ok().put("sites", all);
    }

    @RequestMapping("/sys/model/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        CarModel byId = carModelService.findById(id);
        return R.ok().put("model", byId);
    }

    @RequestMapping("/sys/model/save")
    @ResponseBody
    public R addCarModel(@RequestBody CarModel carModel) {
        int i = carModelService.addCarModel(carModel);
        return i > 0 ? R.ok() : R.error("新增失败！");
    }

    @RequestMapping("/sys/model/update")
    @ResponseBody
    public R updateCarModel(@RequestBody CarModel carModel) {
        int i = carModelService.updateCarModel(carModel);
        return i > 0 ? R.ok() : R.error("修改失败！");
    }

}

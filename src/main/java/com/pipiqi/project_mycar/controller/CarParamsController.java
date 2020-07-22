package com.pipiqi.project_mycar.controller;



import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarParamType;
import com.pipiqi.project_mycar.pojo.CarParams;
import com.pipiqi.project_mycar.service.CarParamTypeService;
import com.pipiqi.project_mycar.service.CarParamsService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarParamsController {
    @Autowired
    private CarParamsService carParamsService;
    @Autowired
    private CarParamTypeService carParamTypeService;

    @RequestMapping("/sys/params/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return carParamsService.findByPage(queryDto);
    }

    @RequestMapping("/sys/params/del")
    @ResponseBody
    public R delParams(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carParamsService.delCarParams(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/params/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        CarParams byId = carParamsService.findById(id);
        return R.ok().put("params", byId);
    }

    @RequestMapping("/sys/params/save")
    @ResponseBody
    public R addCarParamType(@RequestBody CarParams carParams) {
        int i = carParamsService.addCarParams(carParams);
        return i >0 ? R.ok():R.error("新增失败");
    }

    @RequestMapping("/sys/params/update")
    @ResponseBody
    public R updateCarParamType(@RequestBody CarParams carParams) {
        int i = carParamsService.updateCarParams(carParams);
        return i >0 ? R.ok():R.error("修改失败");
    }

    @RequestMapping("/sys/params/typeall")
    @ResponseBody
    public R findAll() {
        List<CarParamType> all = carParamTypeService.findAll();
        return R.ok().put("sites", all);
    }
}

package com.pipiqi.project_mycar.controller;


import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarParamType;
import com.pipiqi.project_mycar.service.CarParamTypeService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CarParamTypeController {

    @Autowired
    private CarParamTypeService carParamTypeService;

    @RequestMapping("/sys/paramtype/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto){
        return carParamTypeService.findByPage(queryDto);
    }
    @RequestMapping("/sys/paramtype/del")
    @ResponseBody
    public R delCarParamType(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            carParamTypeService.delParamType(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/paramtype/info/{id}")
    @ResponseBody
    public R findById(@PathVariable("id") Integer id) {
        CarParamType byId = carParamTypeService.findById(id);
        return R.ok().put("paramtype", byId);
    }

    @RequestMapping("/sys/paramtype/save")
    @ResponseBody
    public R addCarParamType(@RequestBody CarParamType carParamType) {
        int i = carParamTypeService.addParamType(carParamType);
        return i >0 ? R.ok():R.error("新增失败");
    }

    @RequestMapping("/sys/paramtype/update")
    @ResponseBody
    public R updateCarParamType(@RequestBody CarParamType carParamType) {
        int i = carParamTypeService.updateParamType(carParamType);
        return i >0 ? R.ok():R.error("修改失败");
    }

}

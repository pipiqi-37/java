package com.pipiqi.project_mycar.controller;


import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Product;
import com.pipiqi.project_mycar.pojo.ProductWithBLOBs;
import com.pipiqi.project_mycar.service.ProductService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductCarController {

    @Autowired
    private ProductService productCarService;

    @RequestMapping("/sys/product/list")
    @ResponseBody
    public DataGridResult findByPage(QueryDto queryDto) {
        return productCarService.findByPage(queryDto);
    }

    @RequestMapping("/sys/product/del")
    @ResponseBody
    public R delProduct(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            productCarService.delProductCar(id);
        }
        return R.ok();
    }

    @RequestMapping("/sys/product/info/{id}")
    @ResponseBody
    public R findByPage(@PathVariable("id") Integer id) {
        ProductWithBLOBs byId = productCarService.findById(id);
        return R.ok().put("product", byId);
    }

    @RequestMapping("/sys/product/save")
    @ResponseBody
    public R addProductCar(@RequestBody ProductWithBLOBs productWithBLOBs) {
        int i = productCarService.addProductCar(productWithBLOBs);
        return i > 0 ? R.ok() : R.error("新增失败！");
    }

    @RequestMapping("/sys/product/update")
    @ResponseBody
    public R updateProductCar(@RequestBody ProductWithBLOBs productWithBLOBs) {
        int i = productCarService.updateProductCar(productWithBLOBs);
        return i > 0 ? R.ok() : R.error("修改失败！");
    }

}

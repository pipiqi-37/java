package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;

import com.pipiqi.project_mycar.pojo.ProductWithBLOBs;

public interface ProductService {
    public int addProductCar(ProductWithBLOBs productWithBLOBs);

    public void delProductCar(Integer id);

    public int updateProductCar(ProductWithBLOBs productWithBLOBs);

    public ProductWithBLOBs findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);
}

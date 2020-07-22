package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarManufacturer;

public interface CarManufacturerService {
    public int addCarManufacturer(CarManufacturer carManufacturer);

    public void delCarManufacturer(Integer id);

    public int updateCarManufacturer(CarManufacturer carManufacturer);

    public CarManufacturer findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);
}

package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarModel;

public interface CarModelService {
    public int addCarModel(CarModel carModel);

    public void delCarModel(Integer id);

    public int updateCarModel(CarModel carModel);

    public CarModel findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);
}

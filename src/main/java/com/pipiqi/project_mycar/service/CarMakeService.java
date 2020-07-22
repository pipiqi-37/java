package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarMake;
import io.swagger.models.auth.In;

import java.util.List;


public interface CarMakeService {
    public int addCarMake(CarMake carMake);

    public void delCarMake(Integer id);

    public int updateCarMake(CarMake carMake);

    public CarMake findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);

    public List<CarMake> findAll();

}

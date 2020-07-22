package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Car;
import com.pipiqi.project_mycar.pojo.CarParamType;

import java.util.List;

public interface CarParamTypeService {

    public int addParamType(CarParamType carParamType);

    public void delParamType(Integer id);

    public int updateParamType(CarParamType carParamType);

    public CarParamType findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);

    public List<CarParamType> findAll();
}

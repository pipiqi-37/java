package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Car;

public interface CarService {
    public int addCar(Car car);

    public void dalCar(Integer id);

    public int updateCar(Car car);

    public Car findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);
}

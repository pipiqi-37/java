package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarParams;

public interface CarParamsService {
    public DataGridResult findByPage(QueryDto queryDto);

    public CarParams findById(Integer id);

    public int addCarParams(CarParams carParams);

    public void delCarParams(Integer id);

    public int updateCarParams(CarParams carParams);

}

package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarManufacturerMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarManufacturer;
import com.pipiqi.project_mycar.pojo.CarManufacturerExample;
import com.pipiqi.project_mycar.service.CarManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class CarManufacturerServiceImpl implements CarManufacturerService {

    @Autowired
    private CarManufacturerMapper carManufacturerMapper;
    @Override
    public int addCarManufacturer(CarManufacturer carManufacturer) {
        return carManufacturerMapper.insertSelective(carManufacturer);
    }

    @Override
    public void delCarManufacturer(Integer id) {
        carManufacturerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarManufacturer(CarManufacturer carManufacturer) {
        return carManufacturerMapper.updateByPrimaryKeySelective(carManufacturer);
    }

    @Override
    public CarManufacturer findById(Integer id) {
        return carManufacturerMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarManufacturerExample example = new CarManufacturerExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<CarManufacturer> carManufacturers = carManufacturerMapper.selectByExample(example);
        PageInfo<CarManufacturer> info = new PageInfo<>(carManufacturers);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, carManufacturers);
        return result;
    }
}

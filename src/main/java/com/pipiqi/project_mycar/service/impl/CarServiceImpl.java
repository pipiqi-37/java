package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Car;
import com.pipiqi.project_mycar.pojo.CarExample;
import com.pipiqi.project_mycar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public int addCar(Car car) {
        return carMapper.insert(car);
    }

    @Override
    public void dalCar(Integer id) {
        carMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCar(Car car) {
        return carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public Car findById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarExample example = new CarExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<Car> cars = carMapper.selectByExample(example);
        PageInfo<Car> info = new PageInfo<>(cars);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, cars);
        return result;
    }
}

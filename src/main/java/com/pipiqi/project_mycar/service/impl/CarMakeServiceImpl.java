package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarMakeMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarMake;
import com.pipiqi.project_mycar.pojo.CarMakeExample;
import com.pipiqi.project_mycar.service.CarMakeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class CarMakeServiceImpl implements CarMakeService {

    @Autowired
    private CarMakeMapper carMakeMapper;

    @Override
    public int addCarMake(CarMake carMake) {
        return carMakeMapper.insertSelective(carMake);
    }

    @Override
    public void delCarMake(Integer id) {
        carMakeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarMake(CarMake carMake) {
        return carMakeMapper.updateByPrimaryKeySelective(carMake);
    }

    @Override
    public CarMake findById(Integer id) {
        return carMakeMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarMakeExample example = new CarMakeExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<CarMake> carMakes = carMakeMapper.selectByExample(example);
        PageInfo<CarMake> info = new PageInfo<>(carMakes);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, carMakes);
        return result;
    }

    @Override
    public List<CarMake> findAll(){
        return carMakeMapper.selectByExample(null);
    }
}

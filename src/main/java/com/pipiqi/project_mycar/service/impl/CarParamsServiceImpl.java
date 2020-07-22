package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarParamsMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarParams;
import com.pipiqi.project_mycar.pojo.CarParamsExample;
import com.pipiqi.project_mycar.service.CarParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class CarParamsServiceImpl implements CarParamsService {

    @Autowired
    CarParamsMapper carParamsMapper;

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarParamsExample example = new CarParamsExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<CarParams> carParams = carParamsMapper.selectByExample(example);
        PageInfo<CarParams> info = new PageInfo<>(carParams);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, carParams);
        return result;
    }

    @Override
    public CarParams findById(Integer id) {
        return carParamsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addCarParams(CarParams carParams) {
        return carParamsMapper.insertSelective(carParams);
    }

    @Override
    public void delCarParams(Integer id) {
        carParamsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarParams(CarParams carParams) {
        return carParamsMapper.updateByPrimaryKeySelective(carParams);
    }
}

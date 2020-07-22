package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarParamTypeMapper;
import com.pipiqi.project_mycar.dao.CarParamsMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarParamType;
import com.pipiqi.project_mycar.pojo.CarParamTypeExample;
import com.pipiqi.project_mycar.service.CarParamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class CarParamTypeServiceImpl implements CarParamTypeService {

    @Autowired
    private CarParamTypeMapper carParamTypeMapper;

    @Override
    public int addParamType(CarParamType carParamType) {
        return carParamTypeMapper.insertSelective(carParamType);
    }

    @Override
    public void delParamType(Integer id) {
        carParamTypeMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int updateParamType(CarParamType carParamType) {
        return carParamTypeMapper.updateByPrimaryKeySelective(carParamType);
    }

    @Override
    public CarParamType findById(Integer id) {
        return carParamTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarParamTypeExample example = new CarParamTypeExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<CarParamType> carParamTypes = carParamTypeMapper.selectByExample(example);
        PageInfo<CarParamType> info = new PageInfo<>(carParamTypes);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, carParamTypes);
        return result;
    }

    @Override
    public List<CarParamType> findAll() {
        return carParamTypeMapper.selectByExample(null);
    }
}
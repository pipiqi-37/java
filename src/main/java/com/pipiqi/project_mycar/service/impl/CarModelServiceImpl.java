package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.CarMakeMapper;
import com.pipiqi.project_mycar.dao.CarModelMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.CarModel;
import com.pipiqi.project_mycar.pojo.CarModelExample;
import com.pipiqi.project_mycar.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.crypto.Data;
import java.util.List;


@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelMapper carModelMapper;

    @Override
    public int addCarModel(CarModel carModel) {
        return carModelMapper.insertSelective(carModel);
    }

    @Override
    public void delCarModel(Integer id) {
        carModelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCarModel(CarModel carModel) {
        return carModelMapper.updateByPrimaryKeySelective(carModel);
    }

    @Override
    public CarModel findById(Integer id) {
        return carModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        CarModelExample example = new CarModelExample();
        String sort = queryDto.getSort();
        if (!StringUtils.isEmpty(sort)) {
            example.setOrderByClause("id");
        }
        List<CarModel> carModels = carModelMapper.selectByExample(example);
        PageInfo<CarModel> info = new PageInfo<>(carModels);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, carModels);
        return result;
    }
}

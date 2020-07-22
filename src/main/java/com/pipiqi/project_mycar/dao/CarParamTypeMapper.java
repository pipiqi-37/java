package com.pipiqi.project_mycar.dao;

import com.pipiqi.project_mycar.pojo.CarParamType;
import com.pipiqi.project_mycar.pojo.CarParamTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarParamTypeMapper {
    int countByExample(CarParamTypeExample example);

    int deleteByExample(CarParamTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarParamType record);

    int insertSelective(CarParamType record);

    List<CarParamType> selectByExample(CarParamTypeExample example);

    CarParamType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarParamType record, @Param("example") CarParamTypeExample example);

    int updateByExample(@Param("record") CarParamType record, @Param("example") CarParamTypeExample example);

    int updateByPrimaryKeySelective(CarParamType record);

    int updateByPrimaryKey(CarParamType record);
}
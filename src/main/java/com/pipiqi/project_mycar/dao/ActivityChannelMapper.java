package com.pipiqi.project_mycar.dao;

import com.pipiqi.project_mycar.pojo.ActivityChannel;
import com.pipiqi.project_mycar.pojo.ActivityChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityChannelMapper {
    int countByExample(ActivityChannelExample example);

    int deleteByExample(ActivityChannelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivityChannel record);

    int insertSelective(ActivityChannel record);

    List<ActivityChannel> selectByExample(ActivityChannelExample example);

    ActivityChannel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivityChannel record, @Param("example") ActivityChannelExample example);

    int updateByExample(@Param("record") ActivityChannel record, @Param("example") ActivityChannelExample example);

    int updateByPrimaryKeySelective(ActivityChannel record);

    int updateByPrimaryKey(ActivityChannel record);
}
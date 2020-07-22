package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Tag;
import com.pipiqi.project_mycar.utils.R;

public interface TagService {

    public int addTag(Tag tag);

    public void delTag(Integer id);

    public int updateTag(Tag tag);

    public Tag findById(Integer id);

    public DataGridResult findByPage(QueryDto queryDto);

    // 折线图方法
    public R findLine();

    // 柱状图
    public R findHistogram();

    // 饼图
    public R findPie();

}

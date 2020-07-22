package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.TagMapper;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Tag;
import com.pipiqi.project_mycar.pojo.TagExample;
import com.pipiqi.project_mycar.service.TagService;
import com.pipiqi.project_mycar.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int addTag(Tag tag) {
        return tagMapper.insertSelective(tag);
    }

    @Override
    public void delTag(Integer id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public Tag findById(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        TagExample example = new TagExample();
        if (queryDto.getSort() != null && !queryDto.getSort().equals("")) {
            example.setOrderByClause("id" + queryDto.getOrder());
        }
        List<Tag> tags = tagMapper.selectByExample(example);
        PageInfo<Tag> info = new PageInfo<>(tags);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, tags);
        return result;
    }

    @Override
    public R findLine() {
        List<String> nameList = new ArrayList<>();
        List numberList = new ArrayList();
        List<Tag> tags = tagMapper.selectByExample(null);
        for (Tag tag : tags) {
            String name = tag.getName();
            nameList.add(name);
            Long clickCount = tag.getClickCount();
            numberList.add(clickCount);
        }
        return R.ok().put("xAxis", nameList).put("seriesData", numberList);
    }

    @Override
    public R findHistogram() {
        List nList = new ArrayList();
        List numList = new ArrayList();
        List<Tag> tags = tagMapper.selectByExample(null);
        for (Tag tag : tags) {
            String name = tag.getName();
            nList.add(name);
            Long clickCount = tag.getClickCount();
            numList.add(clickCount);
        }
        return R.ok().put("xAxis", nList).put("seriesData", numList);
    }

    @Override
    public R findPie() {
        List<String> nList = new ArrayList<>();
        List<Map<String, Object>> numList = new ArrayList<>();
        List<Tag> tags = tagMapper.selectByExample(null);
        for (Tag tag : tags) {
            String name = tag.getName();
            Long clickCount = tag.getClickCount();
            nList.add(name);
            Map<String, Object> MapList = new HashMap<>();
            MapList.put("name", name);
            MapList.put("value", clickCount);
            numList.add(MapList);
        }
        return R.ok().put("legendData", nList).put("seriesData", numList);
    }
}

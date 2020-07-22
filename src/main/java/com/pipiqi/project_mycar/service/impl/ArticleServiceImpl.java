package com.pipiqi.project_mycar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pipiqi.project_mycar.dao.ArticleMapper;
import com.pipiqi.project_mycar.dto.ArticleDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Article;
import com.pipiqi.project_mycar.pojo.ArticleExample;
import com.pipiqi.project_mycar.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int addArticle(ArticleDto articleDto) {
        Article record = new Article();
        BeanUtils.copyProperties(articleDto, record);
        Boolean allowUp = articleDto.getAllowUp();
        if (allowUp != null) {
            if (allowUp) {
                record.setAllowUp((byte) 1);
            } else {
                record.setAllowUp((byte) 0);
            }
            Boolean allowFav = articleDto.getAllowFav();
            if (allowFav != null) {
                if (allowFav) {
                    record.setAllowFav((byte) 1);
                } else {
                    record.setAllowFav((byte) 0);
                }
            }
        }
        int i = articleMapper.insertSelective(record);
        return i;
    }

    @Override
    public void delArt(Long id) {
        articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateArticle(ArticleDto articleDto) {
        Article record = new Article();
        BeanUtils.copyProperties(articleDto, record);
        Boolean allowUp = articleDto.getAllowUp();
        if (allowUp != null) {
            if (allowUp) {
                record.setAllowUp((byte) 1);
            } else {
                record.setAllowUp((byte) 0);
            }
        }

        Boolean allowFav = articleDto.getAllowFav();
        if (allowFav != null) {
            if (allowFav) {
                record.setAllowFav((byte) 1);
            } else {
                record.setAllowFav((byte) 0);
            }
        }

        int i = articleMapper.updateByPrimaryKeySelective(record);
        return i;
    }

    @Override
    public Article findById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findByPage(QueryDto queryDto) {
        PageHelper.offsetPage(queryDto.getOffset(), queryDto.getLimit());
        ArticleExample example = new ArticleExample();
        if (queryDto.getSort() != null && !queryDto.getSort().equals("")) {
            queryDto.setSort("id");
        }
        List<Article> articles = articleMapper.selectByExample(example);
        PageInfo<Article> info = new PageInfo<>(articles);
        long total = info.getTotal();
        DataGridResult result = new DataGridResult(total, articles);
        return result;
    }
}

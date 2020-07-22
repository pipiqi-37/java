package com.pipiqi.project_mycar.service;

import com.pipiqi.project_mycar.dto.ArticleDto;
import com.pipiqi.project_mycar.dto.DataGridResult;
import com.pipiqi.project_mycar.dto.QueryDto;
import com.pipiqi.project_mycar.pojo.Article;

public interface ArticleService {
    public int addArticle(ArticleDto articleDto);

    public void delArt(Long id);

    public int updateArticle(ArticleDto articleDto);

    public Article findById(Long id);

    public DataGridResult findByPage(QueryDto queryDto);
}

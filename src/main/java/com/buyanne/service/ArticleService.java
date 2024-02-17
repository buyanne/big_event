package com.buyanne.service;

import com.buyanne.pojo.entity.Article;
import com.buyanne.pojo.entity.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void update(Article article);

    Article detail(Integer id);

    void delete(Integer id);
}

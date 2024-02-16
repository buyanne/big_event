package com.buyanne.service;

import com.buyanne.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    public void  add(Category category);

    List<Category> list();

    Category findCategoryById(Integer id);

    void update(Category category);

    void delete(Integer id);
}

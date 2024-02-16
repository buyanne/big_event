package com.buyanne.service.impl;

import com.buyanne.mapper.CategoryMapper;
import com.buyanne.pojo.entity.Category;
import com.buyanne.service.CategoryService;
import com.buyanne.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void add(Category category){
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String ,Object >map= ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String ,Object > map=ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryMapper.findCategoryById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

}

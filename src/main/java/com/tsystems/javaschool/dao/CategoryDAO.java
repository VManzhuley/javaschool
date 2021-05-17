package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    void add(Category category);
    Category getById(int id);
    List<Category> getAllWithoutChild();
    List<Category> getAllWithoutParent();
    void update(Category category);

}

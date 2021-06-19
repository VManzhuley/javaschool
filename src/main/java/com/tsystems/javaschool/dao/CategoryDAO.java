package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Category;

import java.util.List;

public interface CategoryDAO {

    void create(Category category);

    Category getById(long id);

    List<Category> getAllWithoutChild();

    List<Category> getAllWithoutParent();

    void update(Category category);

}

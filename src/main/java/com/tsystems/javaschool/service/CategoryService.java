package com.tsystems.javaschool.service;

import com.tsystems.javaschool.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
}

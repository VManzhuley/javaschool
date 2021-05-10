package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dto.CategoryDTO;
import com.tsystems.javaschool.entity.product.*;
import com.tsystems.javaschool.service.ParametersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParametersServiceImpl implements ParametersService {
    private final ParametersDAO parametersDAO;

    public ParametersServiceImpl(ParametersDAO parametersDAO) {
        this.parametersDAO = parametersDAO;
    }

    @Override
    public List<Composition> gelAllComposition() {
        return parametersDAO.gelAllComposition();
    }

    @Override
    public List<Description> getAllDescription() {
        return parametersDAO.getAllDescription();
    }

    @Override
    public List<Size> getAllSize() {
        return parametersDAO.getAllSize();
    }

    @Override
    public List<Colour> getAllColour() {
        return parametersDAO.getAllColour();
    }

    @Override
    public List<CategoryDTO> getAllCategoryWithoutChild() {
        return parametersDAO.getAllCategoryWithoutChild().stream().map(this::mapToCategoryDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO mapToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setIdCategory(category.getId());
        if (category.getCategoryParent() == null) {
            categoryDTO.setName(category.getName());
        } else {
            categoryDTO.setName(category.getCategoryParent().getName() + "/" + category.getName());
        }
        return categoryDTO;
    }

    @Override
    public Colour getColourById(int id) {
        return parametersDAO.getColourById(id);
    }

    @Override
    public Size getSizeById(int id) {
        return parametersDAO.getSizeById(id);
    }


}

package com.tsystems.javaschool.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private long idCategory;
    private String name;
    private String fullName;
    private List<CategoryDTO> categoriesChild;
    private List<ProductAbsDTO> productsAbs;

}

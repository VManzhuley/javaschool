package com.tsystems.javaschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long id;
    private String productAbsName;
    private String name;
    private String article;
    private SizeDTO size;
    private ColourDTO colour;
    private double price;
    private long quantity;
    private boolean outdated;
}

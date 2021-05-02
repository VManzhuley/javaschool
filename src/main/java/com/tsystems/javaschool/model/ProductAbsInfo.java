package com.tsystems.javaschool.model;

import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.entity.product.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductAbsInfo {
    private int id;
    private String name;
    private List<Size> sizes;
    private List<ProductColour> productColours;
    private List<Product> products;
}

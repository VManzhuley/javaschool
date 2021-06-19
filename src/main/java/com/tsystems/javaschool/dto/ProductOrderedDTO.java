package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class ProductOrderedDTO {

    private ProductDTO product;
    private long quantity;
    private double amount;

    public double getPrice(){
        return amount/quantity;
    }

}

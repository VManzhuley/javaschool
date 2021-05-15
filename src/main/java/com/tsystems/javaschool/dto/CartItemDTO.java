package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private ProductDTO product;
    private int quantity;
    private int missQuantity;

    public int getAmount(){
        return this.product.getPrice()*this.quantity;
    }
}

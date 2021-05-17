package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private ProductDTO product;
    private long quantity;
    private long missQuantity;

    public long getAmount(){
        return this.product.getPrice()*this.quantity;
    }
}

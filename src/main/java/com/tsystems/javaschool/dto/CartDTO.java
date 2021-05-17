package com.tsystems.javaschool.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CartDTO {

    private String userName;
    private List<CartItemDTO> cartItems = new ArrayList<>();
    private boolean isMissQuantity;

    public CartItemDTO findItemByIdProduct(int idProduct) {
        for (CartItemDTO item : this.cartItems
        ) {
            if (item.getProduct().getId() == idProduct) return item;
        }
        return null;
    }

    public void addCartItem(CartItemDTO cartItemDTO) {
        this.cartItems.add(cartItemDTO);
    }

    public void removeCartItem(CartItemDTO cartItemDTO) {
        this.cartItems.remove(cartItemDTO);
    }


    public int getAmountTotal() {
        int total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

    public boolean getIsMissQuantity() {
        for (CartItemDTO item : this.cartItems
        ) {
            if (item.getMissQuantity() > 0) {
                return true;
            }
        }
        return false;
    }
}

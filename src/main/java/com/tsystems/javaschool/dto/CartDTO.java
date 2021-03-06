package com.tsystems.javaschool.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private String userName;
    private List<CartItemDTO> cartItems = new ArrayList<>();
    private boolean isMissQuantity;

    public CartItemDTO findItemByIdProduct(long idProduct) {
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


    public double getAmountTotal() {
        double total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getAmount();
        }
        return total;
    }

    public double getWeightTotal() {
        double total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getProduct().getSize().getWeight() * item.getQuantity();
        }
        return total;
    }

    public double getVolumeTotal() {
        double total = 0;
        for (CartItemDTO item : this.cartItems) {
            total += item.getProduct().getSize().getVolume() * item.getQuantity();
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

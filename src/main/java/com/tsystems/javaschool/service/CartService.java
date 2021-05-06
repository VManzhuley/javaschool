package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;
import com.tsystems.javaschool.dto.CartItemDTO;
import com.tsystems.javaschool.entity.Cart;

public interface CartService {
    CartDTO findByClient(int id);
    CartItemDTO mapToCartItemDTO(Cart cart);
}

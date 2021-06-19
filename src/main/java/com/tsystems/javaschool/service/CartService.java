package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.CartDTO;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface CartService {

    void mergeCart(CartDTO cartDTO, Principal principal);

    void addCartItem(CartDTO cartDTO, long idProductAbs, HttpServletRequest request);

    void updateCartItem(CartDTO cartDTO, HttpServletRequest request);

    void removeCartItem(CartDTO cartDTO, long idProduct);

    void removeAll(CartDTO cartDTO);

    void checkAvailability(CartDTO cartDTO);


}

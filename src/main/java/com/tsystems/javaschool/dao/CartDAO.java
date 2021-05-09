package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Cart;

import java.util.List;

public interface CartDAO {
List<Cart> findByClient(int id);
void addList(List<Cart> cartList);
}

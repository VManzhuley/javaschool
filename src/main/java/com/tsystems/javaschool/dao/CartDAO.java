package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Cart;

import java.util.List;

public interface CartDAO {
List<Cart> findByClient(String email);
void add(Cart cart);
Cart getByClientAndProduct(String email, int idProduct);
void update(Cart cart);
void remove(Cart cart);
void removeAll(int idClient);
}

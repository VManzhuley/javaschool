package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void addProductOrdered(ProductOrdered productOrdered) {
        entityManager.persist(productOrdered);
    }
}

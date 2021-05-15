package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;

    private final int pageSize = 10;


    @Override
    public void addOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void addProductOrdered(ProductOrdered productOrdered) {
        entityManager.persist(productOrdered);
    }

    @Override
    public List<Order> allByPage(int page) {
        Query query = entityManager.createQuery("select o from Order o order by o.date desc")
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public long getTotalPagesToAdmin() {
        long totalCount = (long) entityManager.createQuery("select count(o) from Order o").getSingleResult();

        return (totalCount + pageSize - 1) / pageSize;
    }

    @Override
    public long getAmount(int idOrder) {
        Query query = entityManager.createQuery("select sum (po.price) from ProductOrdered po where po.order.id=:id");
        return (long) query.setParameter("id", idOrder).getSingleResult();
    }

    @Override
    public Order getById(int id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public List<Order> allByClientAndPage(String email, int page) {
        Query query = entityManager.createQuery("select o from Order o where o.client.email=:email and o.client.id>=(select c.id from Client c where c.userName=:email) order by o.date desc")
                .setParameter("email", email)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public long getTotalPagesToUser(String email) {
        long totalCount = (long) entityManager.createQuery("select count(o) from Order o where o.client.email=:email")
                .setParameter("email", email)
                .getSingleResult();

        return (totalCount + pageSize - 1) / pageSize;
    }
}

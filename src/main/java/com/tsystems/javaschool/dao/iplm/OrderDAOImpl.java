package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;

    private final int pageSize=10;
    @Getter
    private int totalPages;


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
        Query query = entityManager.createQuery("select o from Order o order by o.date desc");

        totalPages=(query.getResultList().size()+pageSize-1)/pageSize;

        query.setFirstResult((page - 1)*pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public long getAmount(int idOrder) {
        Query query= entityManager.createQuery("select sum (po.price) from ProductOrdered po where po.order.id=:id");
        return (long) query.setParameter("id",idOrder).getSingleResult();
    }

    @Override
    public Order getById(int id) {
        return entityManager.find(Order.class,id);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }
}

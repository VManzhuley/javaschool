package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.OrderDAO;
import com.tsystems.javaschool.entity.Order;
import com.tsystems.javaschool.entity.ProductOrdered;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private static final int PAGE_SIZE = 10;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void createProductOrdered(ProductOrdered productOrdered) {
        entityManager.persist(productOrdered);
    }

    @Override
    public List<Order> getAllByPage(int page) {
        Query query = entityManager.createQuery("select o from Order o order by o.date desc")
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE);

        return query.getResultList();
    }

    @Override
    public long getTotalPagesToAdmin() {
        long totalCount = entityManager.createQuery("select count(o) from Order o", Long.class).getSingleResult();

        return (totalCount + PAGE_SIZE - 1) / PAGE_SIZE;
    }

    @Override
    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        entityManager.merge(order);
    }

    @Override
    public List<Order> getAllByClientAndPage(long idClient, int page) {

        Query query1 = entityManager.createNativeQuery("select * from orders o where o.client_id in (SELECT c.id FROM client as m\n" +
                "join client as c on m.id=c.client_parent_id where m.id=?1) or o.client_id=?2 order by o.date desc", Order.class)
                .setParameter(1, idClient)
                .setParameter(2, idClient)
                .setFirstResult((page - 1) * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE);


        return query1.getResultList();
    }

    @Override
    public long getTotalPagesToUser(long idClient) {
        Query query = entityManager.createNativeQuery("select count(*) from orders o where o.client_id in (SELECT c.id FROM client as m\n" +
                "join client as c on m.id=c.client_parent_id where m.id=?1) or o.client_id=?2 order by o.date desc")
                .setParameter(1, idClient)
                .setParameter(2, idClient);

        BigInteger bigInteger = (BigInteger) query.getSingleResult();
        long totalCount = bigInteger.longValue();

        return (totalCount + PAGE_SIZE - 1) / PAGE_SIZE;
    }
}

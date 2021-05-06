package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.CartDAO;
import com.tsystems.javaschool.entity.Cart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Cart> findByClient(int id) {
        TypedQuery<Cart> query = entityManager.createQuery("select c from Cart c where c.client.id=:id",
                Cart.class);

        return query.setParameter("id", id).getResultList();

    }
}

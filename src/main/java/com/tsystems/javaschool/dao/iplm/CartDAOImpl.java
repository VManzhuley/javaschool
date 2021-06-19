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
    public List<Cart> findByClient(String userName) {
        TypedQuery<Cart> query = entityManager.createQuery("select c from Cart c where c.client.userName=:userName",
                Cart.class);

        return query.setParameter("userName", userName).getResultList();

    }

    @Override
    public void create(Cart cart) {
        entityManager.persist(cart);
    }

    @Override
    public Cart getByClientAndProduct(String userName, long idProduct) {
        TypedQuery<Cart> query = entityManager.createQuery("select c from Cart c where c.client.userName=:userName and c.product.id=:id",
                Cart.class);
        return query.setParameter("userName", userName)
                .setParameter("id", idProduct)
                .getSingleResult();
    }

    @Override
    public void update(Cart cart) {
        entityManager.merge(cart);
    }

    @Override
    public void remove(Cart cart) {
        entityManager.remove(cart);
    }

    @Override
    public void removeAll(long idClient) {
        entityManager.createQuery("delete from Cart c where c.client.id=:id").setParameter("id", idClient).executeUpdate();
    }


}

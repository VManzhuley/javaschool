package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final EntityManagerFactory entityManagerFactory;

    public ProductDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public Product getProduct(int id) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        return entityManager.find(Product.class,id);
    }

    @Override
    public List<Product> allProducts(int idProductAbs) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.productAbs.id=:id",
                Product.class);
        return query.setParameter("id",idProductAbs).getResultList();
    }
}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Product getById(long id) {
        return entityManager.find(Product.class, id);
    }


    @Override
    public Product getProductByProductABSColourMainColourSecSize(long idProductAbs,
                                                                 long idColourMain,
                                                                 long idColourSec,
                                                                 long idSize) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p " +
                "where p.productAbs.id=:idProductAbs " +
                "and p.colourMain.id=:idColourMain " +
                "and (p.colourSec.id = :idColourSec or p.colourSec is null) " +
                "and p.size.id=:idSize", Product.class);

        query.setParameter("idProductAbs", idProductAbs)
                .setParameter("idColourMain", idColourMain)
                .setParameter("idColourSec", idColourSec)
                .setParameter("idSize", idSize);
        return query.getSingleResult();
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void create(Product product) {
        entityManager.persist(product);
    }


}

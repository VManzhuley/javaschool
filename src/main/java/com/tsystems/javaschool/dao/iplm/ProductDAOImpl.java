package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> allProducts(int idProductAbs) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.productAbs.id=:id",
                Product.class);

        return query.setParameter("id", idProductAbs).getResultList();
    }

    @Override
    public Product getProductByProductABSColourMainColourSecSize(int idProductAbs, int idColourMain, int idColourSec, String size) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.productAbs.id=:id1 and p.colourMain.id=:id2 and (p.colourSec.id = :id3 or p.colourSec is null) and p.size.name=:size",
                Product.class);
        query.setParameter("id1", idProductAbs)
                .setParameter("id2", idColourMain)
                .setParameter("id3", idColourSec)
                .setParameter("size", size);
        return query.getSingleResult();
    }


}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.entity.product.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductAbsDAOImpl implements ProductAbsDAO {
    private final EntityManagerFactory entityManagerFactory;
    private int pageSize=6;

    public ProductAbsDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public List<ProductAbs> allProductAbs() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select p from ProductAbs p").getResultList();
    }

    @Override
    public ProductAbs getProductAbs(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(ProductAbs.class, id);
    }

    @Override
    public List<Size> allSizes(int idProductAbs) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Size> query = entityManager.createQuery("select s from Size s join Product p " +
                "on s=p.size where p.productAbs.id=?1 group by s.name", Size.class);

        return query.setParameter(1, idProductAbs).getResultList();
    }

    @Override
    public List<Product> allProducts(int idProductAbs) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.productAbs.id=?1",
                Product.class);
        return query.setParameter(1, idProductAbs).getResultList();
    }

    @Override
    public List<ProductAbs> allProductsByCategory(int idCategory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ProductAbs> query = entityManager.createQuery("select p from ProductAbs p " +
                "where p.category.id=?1", ProductAbs.class);
        return query.setParameter(1, idCategory).getResultList();
    }

    @Override
    public List<ProductAbs> allProductsByCategoryWithFSP(int idCategory, int page, String sort) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<ProductAbs> criteriaQuery = criteriaBuilder.createQuery(ProductAbs.class);

        Root<ProductAbs> productAbsRoot = criteriaQuery.from(ProductAbs.class);
        Join<ProductAbs,Category> categoryJoin = productAbsRoot.join(ProductAbs_.CATEGORY);
        criteriaQuery.where(criteriaBuilder.equal(categoryJoin.get(Category_.ID),idCategory));

        CriteriaQuery<ProductAbs> select = criteriaQuery.select(productAbsRoot);


        criteriaQuery.orderBy(criteriaBuilder.asc(productAbsRoot.get(sort)));


        TypedQuery<ProductAbs> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult((page - 1)*pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }


}

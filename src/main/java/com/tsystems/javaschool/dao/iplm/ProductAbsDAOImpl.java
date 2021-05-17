package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.entity.product.Category;
import com.tsystems.javaschool.entity.product.Category_;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.ProductAbs_;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductAbsDAOImpl implements ProductAbsDAO {
    private final EntityManagerFactory entityManagerFactory;
    private final int pageSize=6;
    @Getter
    private int totalPages;

    @PersistenceContext
    EntityManager entityManager;


    public ProductAbsDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public ProductAbs getById(int id) {
        return entityManager.find(ProductAbs.class, id);
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
        totalPages=(typedQuery.getResultList().size()+pageSize-1)/pageSize;
        typedQuery.setFirstResult((page - 1)*pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    @Override
    public void add(ProductAbs productAbs) {
        entityManager.persist(productAbs);
    }

    @Override
    public List<ProductAbs> allByCategory(int idCategory) {
        TypedQuery<ProductAbs> query = entityManager.createQuery("select p from ProductAbs p where p.category.id=:id",ProductAbs.class);

        return query.setParameter("id",idCategory).getResultList();
    }

    @Override
    public void update(ProductAbs productAbs) {
        entityManager.merge(productAbs);
    }


}

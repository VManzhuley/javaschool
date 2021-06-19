package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.entity.product.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductAbsDAOImpl implements ProductAbsDAO {

    private static final int PAGE_SIZE = 6;

    @PersistenceContext
    EntityManager entityManager;

    @Getter
    @Setter
    private int totalPages;

    @Override
    public ProductAbs getById(long id) {
        return entityManager.find(ProductAbs.class, id);
    }

    @Override
    public List<ProductAbs> getAllProductsByCategoryWithFSP(long idCategory,
                                                            int page,
                                                            String sort,
                                                            long idComposition,
                                                            long idDescription) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductAbs> criteriaQuery = criteriaBuilder.createQuery(ProductAbs.class);

        Root<ProductAbs> productAbsRoot = criteriaQuery.from(ProductAbs.class);


        Predicate predicate = criteriaBuilder.conjunction();
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productAbsRoot.get(ProductAbs_.CATEGORY).get(Category_.ID), idCategory));
        predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productAbsRoot.get(ProductAbs_.OUTDATED), false));
        if (idComposition != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productAbsRoot.get(ProductAbs_.COMPOSITION).get(Composition_.ID), idComposition));
        }
        if (idDescription != 0) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(productAbsRoot.get(ProductAbs_.DESCRIPTION).get(Description_.ID), idDescription));
        }

        criteriaQuery.where(predicate);

        CriteriaQuery<ProductAbs> select = criteriaQuery.select(productAbsRoot);

        criteriaQuery.orderBy(criteriaBuilder.asc(productAbsRoot.get(sort)));

        TypedQuery<ProductAbs> typedQuery = entityManager.createQuery(select);
        setTotalPages((typedQuery.getResultList().size() + PAGE_SIZE - 1) / PAGE_SIZE);
        typedQuery.setFirstResult((page - 1) * PAGE_SIZE);
        typedQuery.setMaxResults(PAGE_SIZE);

        return typedQuery.getResultList();
    }

    @Override
    public void create(ProductAbs productAbs) {
        entityManager.persist(productAbs);
    }

    @Override
    public List<ProductAbs> getAllByCategory(long idCategory) {
        TypedQuery<ProductAbs> query = entityManager.createQuery("select p from ProductAbs p where p.category.id=:id", ProductAbs.class);

        return query.setParameter("id", idCategory).getResultList();
    }

    @Override
    public void update(ProductAbs productAbs) {
        entityManager.merge(productAbs);
    }


}

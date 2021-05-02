package com.tsystems.javaschool.baeldung;

import com.tsystems.javaschool.entity.product.ProductAbs;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDAOBaeldung implements IProductDAOBaeldung {
    private final EntityManagerFactory entityManagerFactory;

    public ProductDAOBaeldung(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public List<ProductAbs> searchProduct(List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
        final CriteriaQuery<ProductAbs> query = builder.createQuery(ProductAbs.class);
        final Root r = query.from(ProductAbs.class);

        Predicate predicate = builder.conjunction();
        ProductSearchQueryCriteriaConsumer searchConsumer = new ProductSearchQueryCriteriaConsumer(predicate,builder,r);
        params.stream().forEach(searchConsumer);
        predicate=searchConsumer.getPredicate();
        query.where(predicate);

        List<ProductAbs> result = entityManagerFactory.createEntityManager().createQuery(query).getResultList();
        return result;

    }
}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.StatisticDAO;
import com.tsystems.javaschool.entity.*;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.entity.product.Product_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StatisticDAOImpl implements StatisticDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<ProductOrdered> topProductOrdered(String status, String sort, int pageSize) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductOrdered> productOrderedCriteriaQuery = criteriaBuilder.createQuery(ProductOrdered.class);
        Root<ProductOrdered> productOrderedRoot = productOrderedCriteriaQuery.from(ProductOrdered.class);


        productOrderedCriteriaQuery.select(criteriaBuilder.construct(ProductOrdered.class,
                productOrderedRoot.get(ProductOrdered_.ID),
                productOrderedRoot.get(ProductOrdered_.PRODUCT),
                productOrderedRoot.get(ProductOrdered_.ORDER),
                criteriaBuilder.sum(productOrderedRoot.get(ProductOrdered_.QUANTITY)),
                criteriaBuilder.sum(productOrderedRoot.get(ProductOrdered_.PRICE))));

        if (!status.equals("ALL")) {

            productOrderedCriteriaQuery.where(criteriaBuilder.equal(productOrderedRoot.get(ProductOrdered_.ORDER).get(Order_.STATUS), Status.valueOf(status)));
        }

        productOrderedCriteriaQuery.groupBy(productOrderedRoot.get(ProductOrdered_.PRODUCT));


        if (sort.equals("quantity")) {
            productOrderedCriteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(productOrderedRoot.get(ProductOrdered_.QUANTITY))));
        } else {
            productOrderedCriteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(productOrderedRoot.get(ProductOrdered_.PRICE))));
        }

        Query query = entityManager.createQuery(productOrderedCriteriaQuery);

        if (pageSize != 0) {
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public List<Order> topClient(String status, String sort, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> orderCriteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = orderCriteriaQuery.from(Order.class);

        Expression<Double> selection = criteriaBuilder.sum(orderRoot.get(Order_.AMOUNT));
        if (sort.equals("average")) {
            selection = criteriaBuilder.avg(orderRoot.get(Order_.AMOUNT));
        }

        orderCriteriaQuery.select(criteriaBuilder.construct(Order.class,
                criteriaBuilder.count(orderRoot.get(Order_.ID)),
                orderRoot.get(Order_.CLIENT),
                orderRoot.get(Order_.DATE),
                orderRoot.get(Order_.PAYMENT),
                orderRoot.get(Order_.SHIPPING),
                orderRoot.get(Order_.STATUS),
                selection));

        if (!status.equals("ALL")) {
            orderCriteriaQuery.where(criteriaBuilder.equal(orderRoot.get(Order_.STATUS), Status.valueOf(status)));
        }

        orderCriteriaQuery.groupBy(orderRoot.get(Order_.CLIENT));

        if (sort.equals("count")) {
            orderCriteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.count(orderRoot.get(Order_.ID))));
        } else {
            orderCriteriaQuery.orderBy(criteriaBuilder.desc(selection));
        }
        Query query = entityManager.createQuery(orderCriteriaQuery);
        if (pageSize != 0) {
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    public List<Order> ordersCompletedByDate(String from, String to) {
        Query query = entityManager.createQuery("select o from Order o where o.status=:status and o.date between :fr and :to order by o.date desc", Order.class);
        query.setParameter("status",Status.COMPLETED)
                .setParameter("fr", from)
                .setParameter("to", to);

        return query.getResultList();
    }

    @Override
    public List<Product> topProduct(String sort, int pageSize) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = productCriteriaQuery.from(Product.class);
        productCriteriaQuery.select(productRoot);

        javax.persistence.criteria.Order order = criteriaBuilder.desc(productRoot.get(Product_.QUANTITY));
        if (sort.equals("asc")) {
            order = criteriaBuilder.asc(productRoot.get(Product_.QUANTITY));
        }
        productCriteriaQuery.orderBy(order);

        Query query = entityManager.createQuery(productCriteriaQuery);
        if (pageSize != 0) {
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }


}

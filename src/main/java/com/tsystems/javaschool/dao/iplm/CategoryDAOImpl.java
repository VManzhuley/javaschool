package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.CategoryDAO;
import com.tsystems.javaschool.entity.product.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final EntityManagerFactory entityManagerFactory;

    public CategoryDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Category> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select c from Category c").getResultList();
    }
}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.CategoryDAO;
import com.tsystems.javaschool.entity.product.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("select c from Category c").getResultList();
    }

    @Override
    public void add(Category category) {
        entityManager.persist(category);
    }

    @Override
    public Category getById(int id) {
        return entityManager.find(Category.class, id);
    }
    @Override
    public List<Category> getAllWithoutChild() {
        return entityManager.createQuery("select c from Category c where c.categoriesChild is empty", Category.class).getResultList();
    }

    @Override
    public List<Category> getAllWithoutParent() {
        return entityManager.createQuery("select c from Category c where c.categoryParent is null",Category.class).getResultList();
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }


}

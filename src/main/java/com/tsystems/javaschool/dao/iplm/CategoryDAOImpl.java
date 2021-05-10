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


}

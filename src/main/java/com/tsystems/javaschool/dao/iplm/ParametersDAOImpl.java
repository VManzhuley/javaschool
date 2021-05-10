package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.entity.product.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ParametersDAOImpl implements ParametersDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Composition> gelAllComposition() {
        return entityManager.createQuery("select c from Composition c",Composition.class).getResultList();
    }

    @Override
    public List<Description> getAllDescription() {
        return entityManager.createQuery("select d from Description d",Description.class).getResultList();
    }

    @Override
    public List<Size> getAllSize() {
        return entityManager.createQuery("select s from Size s", Size.class).getResultList();
    }

    @Override
    public List<Colour> getAllColour() {
        return entityManager.createQuery("select c from Colour c",Colour.class).getResultList();
    }
    @Override
    public List<Category> getAllCategoryWithoutChild() {
         return entityManager.createQuery("select c from Category c where c.categoriesChild is empty", Category.class).getResultList();
    }

    @Override
    public Colour getColourById(int id) {
        return entityManager.find(Colour.class,id);
    }

    @Override
    public Size getSizeById(int id) {
        return entityManager.find(Size.class,id);
    }

}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ColourDAO;
import com.tsystems.javaschool.entity.product.Colour;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public class ColourDAOImpl implements ColourDAO {

    private final EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    EntityManager entityManager;

    public ColourDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Colour getColour(int id) {
        return entityManager.find(Colour.class,id);
    }

}

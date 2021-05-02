package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ColourDAO;
import com.tsystems.javaschool.entity.product.Colour;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class ColourDAOImpl implements ColourDAO {

    private final EntityManagerFactory entityManagerFactory;

    public ColourDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Colour getColour(int id) {
        EntityManager entityManager =entityManagerFactory.createEntityManager();
        return entityManager.find(Colour.class,id);
    }

}

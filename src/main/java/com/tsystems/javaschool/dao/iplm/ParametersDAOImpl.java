package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        return entityManager.createQuery("select s from Size s order by s.id", Size.class).getResultList();
    }

    @Override
    public List<Colour> getAllColour() {
        return entityManager.createQuery("select c from Colour c",Colour.class).getResultList();
    }


    @Override
    public Colour getColourByName(String name) {
        TypedQuery<Colour> query = entityManager.createQuery("select c from Colour c where c.name=:name",Colour.class);

        return query.setParameter("name",name).getSingleResult();
    }

    @Override
    public Size getSizeByName(String name) {
        TypedQuery<Size> query = entityManager.createQuery("select s from Size s where s.name=:name",Size.class);

        return query.setParameter("name",name).getSingleResult();
    }

}

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
        return entityManager.createQuery("select c from Composition c", Composition.class).getResultList();
    }

    @Override
    public List<Description> getAllDescription() {
        return entityManager.createQuery("select d from Description d", Description.class).getResultList();
    }

    @Override
    public List<Size> getAllSize() {
        return entityManager.createQuery("select s from Size s order by s.id", Size.class).getResultList();
    }

    @Override
    public List<Colour> getAllColour() {
        return entityManager.createQuery("select c from Colour c", Colour.class).getResultList();
    }


    @Override
    public Colour getColourByName(String name) {
        TypedQuery<Colour> query = entityManager.createQuery("select c from Colour c where c.name=:name", Colour.class);

        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public Size getSizeByName(String name) {
        TypedQuery<Size> query = entityManager.createQuery("select s from Size s where s.name=:name", Size.class);

        return query.setParameter("name", name).getSingleResult();
    }

    @Override
    public void createComposition(Composition composition) {
        entityManager.persist(composition);
    }

    @Override
    public void createDescription(Description description) {
        entityManager.persist(description);
    }

    @Override
    public List<Composition> getAllCompositionByCategory(long id) {
        TypedQuery<Composition> query = entityManager.createQuery("select p.composition from ProductAbs p " +
                "where p.category.id=:id group by p.composition", Composition.class);
        return query.setParameter("id", id).getResultList();
    }

    @Override
    public List<Description> getAllDescriptionByCategory(long id) {
        TypedQuery<Description> query = entityManager.createQuery("select p.description from ProductAbs p " +
                "where p.category.id=:id group by p.description", Description.class);
        return query.setParameter("id", id).getResultList();
    }

}

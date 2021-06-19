package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.entity.product.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PhotoDAOImpl implements PhotoDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Photo getPhotoLink(long idProduct) {

        TypedQuery<Photo> query = entityManager.createQuery(
                "select p from Photo p join Product pr " +
                        "on p.productAbs=pr.productAbs and (p.colourSec=pr.colourSec or p.colourSec is null) " +
                        "and p.colourMain=pr.colourMain where pr.id=:id", Photo.class);

        return query.setParameter("id", idProduct).getSingleResult();
    }

    @Override
    public void create(Photo photo) {
        entityManager.persist(photo);
    }

    @Override
    public List<Photo> getAllByProductAbs(long idProductAbs) {
        TypedQuery<Photo> query = entityManager.createQuery("select p from Photo p where p.productAbs.id=:id", Photo.class);

        return query.setParameter("id", idProductAbs).getResultList();
    }

    @Override
    public void update(Photo photo) {
        entityManager.merge(photo);
    }

    @Override
    public Photo getById(long id) {
        return entityManager.find(Photo.class, id);
    }
}

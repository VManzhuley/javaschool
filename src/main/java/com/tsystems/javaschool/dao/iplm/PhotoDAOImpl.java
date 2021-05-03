package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.entity.product.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class PhotoDAOImpl implements PhotoDAO {
    private final EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    EntityManager entityManager;

    public PhotoDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public Photo getPhotoLink(int idProduct) {

        TypedQuery<Photo> query = entityManager.createQuery(
                "select p from Photo p join Product pr " +
                        "on p.productAbs=pr.productAbs and (p.colourSec=pr.colourSec or p.colourSec is null) " +
                        "and p.colourMain=pr.colourMain where pr.id=?1", Photo.class);

        return query.setParameter(1, idProduct).getSingleResult();
    }
}

package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.PhotoDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

@Repository
public class PhotoDAOImpl implements PhotoDAO {
    private final EntityManagerFactory entityManagerFactory;

    public PhotoDAOImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public String getPhotoLink(int idProduct) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String photoLink;
        TypedQuery<String> query = entityManager.createQuery(
                "select p.photoLink from Photo p join Product pr " +
                        "on p.productAbs=pr.productAbs and (p.colourSec=pr.colourSec or p.colourSec is null) " +
                        "and p.colourMain=pr.colourMain where pr.id=?1", String.class);
        photoLink = query.setParameter(1, idProduct).getSingleResult();
        return photoLink;
    }
}

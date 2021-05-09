package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.WeightVolumeDAO;
import com.tsystems.javaschool.entity.product.WeightVolume;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class WeightVolumeDAOImpl implements WeightVolumeDAO {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public WeightVolume getWVByIdProduct(int idProduct) {
        TypedQuery<WeightVolume> query = entityManager.createQuery(
                "select w from WeightVolume w join Product p " +
                        "on p.productAbs=w.productAbs and p.size=w.size " +
                        "where p.id=:id", WeightVolume.class);

        return query.setParameter("id", idProduct).getSingleResult();

    }
}

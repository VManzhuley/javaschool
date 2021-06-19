package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.WeightVolumeDAO;
import com.tsystems.javaschool.entity.product.WeightVolume;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WeightVolumeDAOImpl implements WeightVolumeDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public WeightVolume getWVByIdProduct(long idProduct) {
        TypedQuery<WeightVolume> query = entityManager.createQuery(
                "select w from WeightVolume w join Product p " +
                        "on p.productAbs=w.productAbs and p.size=w.size " +
                        "where p.id=:id", WeightVolume.class);

        return query.setParameter("id", idProduct).getSingleResult();
    }

    @Override
    public void create(WeightVolume weightVolume) {
        entityManager.persist(weightVolume);
    }

    @Override
    public List<WeightVolume> getAllByProductAbs(long idProductAbs) {
        TypedQuery<WeightVolume> query = entityManager.createQuery("select w from WeightVolume w where w.productAbs.id=:id", WeightVolume.class);

        return query.setParameter("id", idProductAbs).getResultList();
    }

    @Override
    public void update(WeightVolume weightVolume) {
        entityManager.merge(weightVolume);
    }

    @Override
    public WeightVolume getById(long id) {
        return entityManager.find(WeightVolume.class, id);
    }
}

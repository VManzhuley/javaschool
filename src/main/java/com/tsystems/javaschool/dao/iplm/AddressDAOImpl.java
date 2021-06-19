package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.AddressDAO;
import com.tsystems.javaschool.entity.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressDAOImpl implements AddressDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Address address) {
        entityManager.persist(address);
    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }
}

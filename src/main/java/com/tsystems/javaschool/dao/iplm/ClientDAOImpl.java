package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Client findById(int id) {
        return entityManager.find(Client.class,id);
    }
}

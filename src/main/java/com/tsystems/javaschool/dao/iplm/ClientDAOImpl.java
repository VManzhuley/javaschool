package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Client findById(int id) {
        return entityManager.find(Client.class,id);
    }

    @Override
    public Client findByMail(String email) {
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c where c.email=:mail", Client.class);
        return query.setParameter("mail",email).getSingleResult();
    }

    @Override
    public void add(Client client) {
        entityManager.persist(client);
    }
}

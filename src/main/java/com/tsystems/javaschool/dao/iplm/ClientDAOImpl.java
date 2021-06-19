package com.tsystems.javaschool.dao.iplm;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Client findByUserName(String userName) {
        TypedQuery<Client> query = entityManager.createQuery("select c from Client c where c.userName=:userName", Client.class);

        List<Client> clientList= query.setParameter("userName",userName).getResultList();

        return clientList.isEmpty() ? null : clientList.get(0);

    }

    @Override
    public void create(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }
}

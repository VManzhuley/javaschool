package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Client;


public interface ClientDAO {

    Client findByUserName(String userName);

    void create(Client client);

    void update(Client client);

}

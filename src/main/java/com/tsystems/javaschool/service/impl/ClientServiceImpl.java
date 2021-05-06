package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client findById(int id) {
        return clientDAO.findById(id);
    }
}

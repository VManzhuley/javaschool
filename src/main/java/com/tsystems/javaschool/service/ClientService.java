package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Client;

public interface ClientService {
    ClientDTO findById(int id);

    void add(ClientDTO clientDTO);

    ClientDTO mapToClientDTO(Client client);
}

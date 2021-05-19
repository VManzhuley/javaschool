package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Address;
import com.tsystems.javaschool.entity.Client;

import java.security.Principal;

public interface ClientService {

    ClientDTO findById(int id);

    ClientDTO findByUserName (String userName);

    Client add(ClientDTO clientDTO);

    ClientDTO mapToClientDTO(Client client);

    Client registerNewClient(ClientDTO clientDTO);

    boolean emailExist(String email);

    Address addAddress(ClientDTO clientDTO);

    void update(ClientDTO clientDTO, Principal principal);

    Address mapToAddress(ClientDTO clientDTO);

    Client mapMainFieldsToClient(ClientDTO clientDTO);

}

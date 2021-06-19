package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Address;
import com.tsystems.javaschool.entity.Client;

import java.security.Principal;

public interface ClientService {

    ClientDTO getByUserName(String userName);

    Client createClient(ClientDTO clientDTO);

    ClientDTO mapToClientDTO(Client client);

    void createClientRoleUser(ClientDTO clientDTO);

    boolean emailExist(String email);

    Address createAddress(ClientDTO clientDTO);

    void update(ClientDTO clientDTO, Principal principal);

    Address mapToAddress(ClientDTO clientDTO);

    Client mapMainFieldsToClient(ClientDTO clientDTO);

}

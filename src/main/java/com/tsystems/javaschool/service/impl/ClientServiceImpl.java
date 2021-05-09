package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.AddressDAO;
import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Address;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.entity.Role;
import com.tsystems.javaschool.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;
    private final AddressDAO addressDAO;

    public ClientServiceImpl(ClientDAO clientDAO, AddressDAO addressDAO) {
        this.clientDAO = clientDAO;
        this.addressDAO = addressDAO;
    }

    @Override
    public ClientDTO findById(int id) {
        return mapToClientDTO(clientDAO.findById(id));
    }

    @Override
    public void add(ClientDTO clientDTO) {
        Client client = new Client();

        if (!clientDTO.addressIsEmpty()){
            Address address = new Address();
            address.setZip(clientDTO.getZip());
            address.setCountry(clientDTO.getCountry());
            address.setCity(clientDTO.getCity());
            address.setStreet(clientDTO.getStreet());
            address.setBuilding(clientDTO.getBuilding());
            address.setApartment(clientDTO.getApartment());

            addressDAO.add(address);

            client.setAddress(address);
        }

        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        Role role=new Role();
        role.setId(1);
        client.setRole(role);

        clientDAO.add(client);



    }

    @Override
    public ClientDTO mapToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        if (client.getAddress() != null) {
            clientDTO.setZip(client.getAddress().getZip());
            clientDTO.setCountry(client.getAddress().getCountry());
            clientDTO.setCity(client.getAddress().getCity());
            clientDTO.setStreet(client.getAddress().getStreet());
            clientDTO.setBuilding(client.getAddress().getBuilding());
            clientDTO.setApartment(client.getAddress().getApartment());
        }
        return clientDTO;
    }
}

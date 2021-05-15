package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.AddressDAO;
import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Address;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.entity.Role;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientDAO clientDAO;
    private final AddressDAO addressDAO;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientDAO clientDAO, AddressDAO addressDAO, PasswordEncoder passwordEncoder) {
        this.clientDAO = clientDAO;
        this.addressDAO = addressDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDTO findById(int id) {
        return mapToClientDTO(clientDAO.findById(id));
    }

    @Override
    public ClientDTO findByUserName(String userName) {
        return mapToClientDTO(clientDAO.findByUserName(userName));
    }

    @Override
    public Client add(ClientDTO clientDTO) {

        Client client = new Client();

        if (!clientDTO.addressIsEmpty()) {
            client.setAddress(addAddress(clientDTO));
        }

        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());


        clientDAO.add(client);

        return client;

    }

    @Override
    public ClientDTO mapToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setPassword("");
        clientDTO.setMatchingPassword("");

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

    @Override
    public Client registerNewClient(ClientDTO clientDTO) {
        if (emailExist(clientDTO.getEmail())) {
            throw new WrongParameterException("There is an account with that email address: " + clientDTO.getEmail());
        }

        Client client = new Client();

        client.setAddress(addAddress(clientDTO));

        client.setUserName(clientDTO.getEmail());
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setRole(Role.USER);

        clientDAO.add(client);

        return client;
    }

    @Override
    public boolean emailExist(String email) {
        return clientDAO.findByUserName(email) != null;
    }

    @Override
    public Address addAddress(ClientDTO clientDTO) {

        Address address = new Address();
        address.setZip(clientDTO.getZip());
        address.setCountry(clientDTO.getCountry());
        address.setCity(clientDTO.getCity());
        address.setStreet(clientDTO.getStreet());
        address.setBuilding(clientDTO.getBuilding());
        address.setApartment(clientDTO.getApartment());
        addressDAO.add(address);

        return address;
    }

}

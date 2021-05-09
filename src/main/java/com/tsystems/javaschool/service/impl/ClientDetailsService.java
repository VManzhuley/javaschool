package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {

    private final ClientDAO clientDAO;

    public ClientDetailsService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientDAO.findByMail(email);
        if (client == null) {
            throw new UsernameNotFoundException("Unknown user: " + email);
        }
        UserDetails user = User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .roles(client.getRole().getName())
                .build();

        return user;
    }
}

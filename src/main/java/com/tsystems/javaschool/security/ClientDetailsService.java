package com.tsystems.javaschool.security;

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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Client client = clientDAO.findByUserName(userName);

        if (client == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }

        return User.builder()
                .username(client.getUserName())
                .password(client.getPassword())
                .roles(client.getRole().name())
                .build();
    }
}

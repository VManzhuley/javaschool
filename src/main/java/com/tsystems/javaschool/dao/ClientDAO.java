package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.Client;

public interface ClientDAO {
    Client findById(int id);
}
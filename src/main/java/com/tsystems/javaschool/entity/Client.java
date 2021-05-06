package com.tsystems.javaschool.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@Data
public class Client extends AbstractTable {

    @Column(name = "surname")
    private String surname;
    @Column(name = "password")
    private String password;
    @Column(name ="email")
    private String email;
    @Column(name = "telephone")
    private String phone;
}

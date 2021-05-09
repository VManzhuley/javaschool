package com.tsystems.javaschool.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
public class Client extends AbstractTable {

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name ="email")
    private String email;

    @Column(name = "telephone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;
}

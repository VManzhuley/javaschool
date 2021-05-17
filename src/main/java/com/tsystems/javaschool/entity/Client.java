package com.tsystems.javaschool.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Data
public class Client extends AbstractTable {

    @Column(name = "username")
    private String userName;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String phone;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "client_parent_id")
    private Client clientParent;
}

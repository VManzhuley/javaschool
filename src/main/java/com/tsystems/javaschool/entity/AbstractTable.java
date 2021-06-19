package com.tsystems.javaschool.entity;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class AbstractTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
}

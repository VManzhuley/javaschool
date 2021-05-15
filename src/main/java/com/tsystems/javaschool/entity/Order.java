package com.tsystems.javaschool.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name = "date")
    private String date;

    @Column(name="payment")
    @Enumerated(EnumType.STRING)
    private PaymentType payment;

    @Column(name="shipping")
    @Enumerated(EnumType.STRING)
    private ShippingType shipping;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<ProductOrdered> productOrderedList;
}

package com.tsystems.javaschool.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

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

    public Order(long id, Client client, String date, PaymentType payment, ShippingType shipping, Status status, double amount) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.payment = payment;
        this.shipping = shipping;
        this.status = status;
        this.amount = amount;
    }

    @Column(name="amount")
    private double amount;


}

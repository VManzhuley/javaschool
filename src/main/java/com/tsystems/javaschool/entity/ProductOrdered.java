package com.tsystems.javaschool.entity;

import com.tsystems.javaschool.entity.product.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ordered_product")
@Data
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")

    private int price;

}

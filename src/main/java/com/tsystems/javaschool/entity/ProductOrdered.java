package com.tsystems.javaschool.entity;

import com.tsystems.javaschool.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ordered_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private long quantity;
    @Column(name = "price")

    private double price;

}

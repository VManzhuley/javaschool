package com.tsystems.javaschool.entity.product;


import com.tsystems.javaschool.entity.ProductOrdered;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_abs_id")
    private ProductAbs productAbs;

    @ManyToOne
    @JoinColumn(name = "colour_main_id")
    private Colour colourMain;

    @ManyToOne
    @JoinColumn(name = "colour_sec_id")
    private Colour colourSec;

    @ManyToOne
    @JoinColumn(name = "size")
    private Size size;

    @Column(name = "quantity")
    private long quantity;

    @OneToMany(mappedBy = "product")
    private List<ProductOrdered> productOrderedList;
}

package com.tsystems.javaschool.entity.product;


import com.tsystems.javaschool.entity.AbstractTable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product_abs")
@Data
public class ProductAbs extends AbstractTable {

    @Column(name = "article")
    private String article;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private Description description;

    @Column(name = "photo")
    private String photo;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "composition_id")
    private Composition composition;

    @Column(name = "outdated")
    private boolean outdated;
}

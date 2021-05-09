package com.tsystems.javaschool.entity.product;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "weight_volume")
@Data
public class WeightVolume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_abs_id")
    private ProductAbs productAbs;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Column(name="weight")
    private float weight;

    @Column(name="volume")
    private float volume;

}

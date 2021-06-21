package com.tsystems.javaschool.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "photo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
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

    @Column(name = "photo")
    private String photoLink;
}

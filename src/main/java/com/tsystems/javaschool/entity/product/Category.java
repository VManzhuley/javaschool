package com.tsystems.javaschool.entity.product;

import com.tsystems.javaschool.entity.AbstractTable;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category extends AbstractTable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryParent;

    @OneToMany(mappedBy = "categoryParent", fetch = FetchType.EAGER)
    private List<Category> categoriesChild;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<ProductAbs> productAbsList;
}

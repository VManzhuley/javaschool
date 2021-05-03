package com.tsystems.javaschool.entity.product;

import com.tsystems.javaschool.entity.AbstractTable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "size")
public class Size extends AbstractTable {


    @OneToMany(mappedBy = "size", fetch = FetchType.EAGER)
    private Set<Product> products;
}

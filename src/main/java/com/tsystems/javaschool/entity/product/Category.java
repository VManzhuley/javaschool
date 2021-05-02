package com.tsystems.javaschool.entity.product;

import com.tsystems.javaschool.entity.AbstractTable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Data
public class Category extends AbstractTable {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryParent;

}

package com.tsystems.javaschool.entity.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Colour> colourMain;
	public static volatile SingularAttribute<Product, Integer> quantity;
	public static volatile SingularAttribute<Product, Size> size;
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, ProductAbs> productAbs;
	public static volatile SingularAttribute<Product, Colour> colourSec;

	public static final String COLOUR_MAIN = "colourMain";
	public static final String QUANTITY = "quantity";
	public static final String SIZE = "size";
	public static final String ID = "id";
	public static final String PRODUCT_ABS = "productAbs";
	public static final String COLOUR_SEC = "colourSec";

}


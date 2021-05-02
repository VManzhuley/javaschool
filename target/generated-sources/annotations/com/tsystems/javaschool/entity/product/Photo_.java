package com.tsystems.javaschool.entity.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photo.class)
public abstract class Photo_ {

	public static volatile SingularAttribute<Photo, Colour> colourMain;
	public static volatile SingularAttribute<Photo, Integer> id;
	public static volatile SingularAttribute<Photo, String> photoLink;
	public static volatile SingularAttribute<Photo, ProductAbs> productAbs;
	public static volatile SingularAttribute<Photo, Colour> colourSec;

	public static final String COLOUR_MAIN = "colourMain";
	public static final String ID = "id";
	public static final String PHOTO_LINK = "photoLink";
	public static final String PRODUCT_ABS = "productAbs";
	public static final String COLOUR_SEC = "colourSec";

}


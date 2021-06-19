package com.tsystems.javaschool.entity.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductAbs.class)
public abstract class ProductAbs_ extends com.tsystems.javaschool.entity.AbstractTable_ {

	public static volatile SingularAttribute<ProductAbs, Boolean> outdated;
	public static volatile SingularAttribute<ProductAbs, Composition> composition;
	public static volatile SingularAttribute<ProductAbs, Double> price;
	public static volatile SingularAttribute<ProductAbs, Description> description;
	public static volatile SingularAttribute<ProductAbs, String> photo;
	public static volatile SingularAttribute<ProductAbs, Category> category;
	public static volatile SingularAttribute<ProductAbs, String> article;

	public static final String OUTDATED = "outdated";
	public static final String COMPOSITION = "composition";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String PHOTO = "photo";
	public static final String CATEGORY = "category";
	public static final String ARTICLE = "article";

}


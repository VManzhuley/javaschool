package com.tsystems.javaschool.entity.product;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ extends com.tsystems.javaschool.entity.AbstractTable_ {

	public static volatile SingularAttribute<Category, Category> categoryParent;
	public static volatile ListAttribute<Category, Category> categoriesChild;

	public static final String CATEGORY_PARENT = "categoryParent";
	public static final String CATEGORIES_CHILD = "categoriesChild";

}


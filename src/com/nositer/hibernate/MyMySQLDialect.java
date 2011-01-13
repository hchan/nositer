package com.nositer.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.type.StandardBasicTypes;

public class MyMySQLDialect extends MySQL5InnoDBDialect {
	public MyMySQLDialect() {
		super();
		//registerHibernateType(-1, StandardBasicTypes.STRING.getName());
	}
}

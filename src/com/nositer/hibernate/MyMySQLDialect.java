package com.nositer.hibernate;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MyMySQLDialect extends MySQL5InnoDBDialect {
	public MyMySQLDialect() {
		super();
		//registerHibernateType(-1, StandardBasicTypes.STRING.getName());
	}
}

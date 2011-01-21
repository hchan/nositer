package com.nositer.hibernate;

public enum SqlHelper {
	FINDUSERBYEMAIL ("SELECT * FROM USER WHERE LOGIN = :LOGIN");

	
	
	
	private final String sql;
	SqlHelper(String sql) {
		this.sql = sql;
	}
	public String sql() {
		return sql;
	}
}

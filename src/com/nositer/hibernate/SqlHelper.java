package com.nositer.hibernate;

public enum SqlHelper {
	FINDUSERBYEMAIL ("SELECT * FROM USER WHERE LOGIN = :LOGIN"),
	FINDPOSTALCODEBYCODE ("SELECT * FROM POSTALCODE WHERE CODE LIKE :CODE LIMIT :OFFSET, :LIMIT")
	;
	
	
	
	private final String sql;
	SqlHelper(String sql) {
		this.sql = sql;
	}
	public String sql() {
		return sql;
	}
}

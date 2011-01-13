package com.nositer.hibernate;

import java.util.HashMap;

public class SQLHashMap {
	public static HashMap<String, String> sql = new HashMap<String, String>();
	static {
		sql.put("findUserByEmail", "SELECT * FROM USER WHERE EMAIL = :EMAIL");
	}
	
	public static String get(String key) {
		return sql.get(key);
	}
}

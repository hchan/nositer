package com.nositer.hibernate;

public class CommonSql {
	public static final String LIMIT = "limit";
	public static final String OFFSET = "offset";
	public static final String PARAMETERIZEDLIMITCLAUSE = LIMIT + " :"  + OFFSET + " , :" + LIMIT;
	public static final String NOTDISABLE = "disable = false";
}

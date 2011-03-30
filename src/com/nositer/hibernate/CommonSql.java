package com.nositer.hibernate;


public class CommonSql {
	public static final String LIMIT = "limit";
	public static final String OFFSET = "offset";
	public static final String PARAMETERIZEDLIMITCLAUSE = LIMIT + " :"  + OFFSET + " , :" + LIMIT;
	public static final String NOTDISABLE = "disable = false";
	public static final String NOW = "now()";
	public static final String CREATEDTIMENOW = "createdtime = " + NOW;
	public static final String MODIFIEDTIMENOW = "modifiedtime = " + NOW;
	public static final double EARTHRADIUS = 6378.137;// km
	
}

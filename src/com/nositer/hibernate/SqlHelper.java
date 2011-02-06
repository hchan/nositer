package com.nositer.hibernate;
import com.nositer.client.dto.generated.*;

import static com.nositer.hibernate.CommonSql.*;
public class SqlHelper {
	public static String FINDUSERBYLOGIN = 
		"select * from " + User.TABLENAME + " where " + User.ColumnType.login + " = :" + User.ColumnType.login;
	public static String FINDPOSTALCODEBYCODE =
		"select * from " + Postalcode.TABLENAME + " where " + Postalcode.ColumnType.code + " like " + ":" + Postalcode.ColumnType.code + " " + PARAMETERIZEDLIMITCLAUSE;
	public static String FINDZIPCODEBYCODE =
		"select * from " + Zipcode.TABLENAME + " where " + Zipcode.ColumnType.code + " like " + ":" + Zipcode.ColumnType.code + " " + PARAMETERIZEDLIMITCLAUSE;
	public static String UPDATEBASICPROFILE =
		"update " + User.TABLENAME + " set " +
		User.ColumnType.firstname + "= :" + User.ColumnType.firstname + ", " +
		User.ColumnType.lastname + "= :" + User.ColumnType.lastname + ", " +
		User.ColumnType.countrycode + "= :" + User.ColumnType.countrycode + ", " +
		User.ColumnType.postalcodeid + "= :" + User.ColumnType.postalcodeid + ", " +
		User.ColumnType.zipcodeid + "= :" + User.ColumnType.zipcodeid + ", " +
		User.ColumnType.email + "= :" + User.ColumnType.email + ", " +
		User.ColumnType.gendermale + "= :" + User.ColumnType.gendermale + ", " +
		User.ColumnType.profession + "= :" + User.ColumnType.profession + ", " +
		User.ColumnType.birthdate + "= :" + User.ColumnType.birthdate +
		" where " + User.ColumnType.id + " = :" + User.ColumnType.id;
	public static String CHANGEPASSWORD =
		"update " + User.TABLENAME + " set " + 
		User.ColumnType.password + "= :" + User.ColumnType.password +
		" where " + User.ColumnType.id + " = :" + User.ColumnType.id;
	public static String UPDATEABOUTME =
		"update " + User.TABLENAME + " set " + 
		User.ColumnType.note + "= :" + User.ColumnType.note + ", " +
		User.ColumnType.notemodifedtime + "= :" + User.ColumnType.notemodifedtime + ", " +
		User.ColumnType.description + "= :" + User.ColumnType.description +
		" where " + User.ColumnType.id + " = :" + User.ColumnType.id;
}

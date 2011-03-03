package com.nositer.hibernate;
import static com.nositer.hibernate.CommonSql.*;

import com.nositer.client.dto.DTO;
import com.nositer.client.dto.Lookupcode;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.dto.generated.User;
public class SqlHelper {
	
	public static String FINDUSERBYLOGIN = 
		"select * from " + User.TABLENAME + " where " + User.ColumnType.login + " = :" + User.ColumnType.login + 
		" and " + NOTDISABLE;
	public static String UPDATEBASICPROFILE =
		"update " + User.TABLENAME + " set " +
		User.ColumnType.firstname + "= :" + User.ColumnType.firstname + ", " +
		User.ColumnType.lastname + "= :" + User.ColumnType.lastname + ", " +
		User.ColumnType.countrycode + "= :" + User.ColumnType.countrycode + ", " +
		User.ColumnType.postalcodeid + "= :" + User.ColumnType.postalcodeid + ", " +
		User.ColumnType.zipcodeid + "= :" + User.ColumnType.zipcodeid + ", " +
		User.ColumnType.salutationcodeid + "= :" + User.ColumnType.salutationcodeid + ", " +
		User.ColumnType.relationshipcodeid + "= :" + User.ColumnType.relationshipcodeid + ", " +
		User.ColumnType.avatarlocation + "= :" + User.ColumnType.avatarlocation + ", " +
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
	public static String UPDATEAVATAR =
		"update " + User.TABLENAME + " set " + 
		User.ColumnType.avatarlocation + "= :" + User.ColumnType.avatarlocation  +
		" where " + User.ColumnType.id + " = :" + User.ColumnType.id;
	public static String FINDMYGROUPS =
		"select * from " + Group.TABLENAME + " where " + Group.ColumnType.userid + " = :" + Group.ColumnType.userid + 
		" and " + NOTDISABLE +
		" order by " + Group.ColumnType.name;
	public static String FINDGROUPBYTAGNAME =
		"select * from " + Group.TABLENAME + " where " + Group.ColumnType.tagname + " = :" + Group.ColumnType.tagname +
		" and " + NOTDISABLE;
	public static String FINDMYIWANTTOS =
		"select * from " + Iwantto.TABLENAME + " where " + Iwantto.ColumnType.userid + " = :" + Iwantto.ColumnType.userid +
		" and " + NOTDISABLE +
		" order by " + Iwantto.ColumnType.description;
	public static String DELETEGROUP = 
		"update " + Group.TABLENAME + " set " +
		Group.ColumnType.disable + " = true" +
		" where " + Group.ColumnType.id + "= :" + Group.ColumnType.id;
	
	
	public static String disableSQL(DTO dto) {
		String retval = null;
		retval = "update " + dto.getTablename() + " set " +
		"disable = true" +
		" where id = " + dto.getId();
		return retval;
	}
	
	public static String createLookupSQL (String tablename) {
		String retval = null;
		retval = "select * from " + tablename + " where " + Lookupcode.CODE + " like " + ":" + Lookupcode.CODE + " " + PARAMETERIZEDLIMITCLAUSE;
		return retval;
	}
}

package com.nositer.hibernate;
import static com.nositer.hibernate.CommonSql.*;

import com.nositer.client.dto.DTO;
import com.nositer.client.dto.Lookupcode;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.dto.generated.Zipcode;
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
		"select * from " + UserHasGroupView.TABLENAME + " where " + UserHasGroupView.ColumnType.userid + " = :" + UserHasGroupView.ColumnType.userid + 
		" and " + NOTDISABLE +
		" order by " + Group.ColumnType.name;
	public static String FINDGROUPBYTAGNAME =
		"select * from " + UserHasGroupView.TABLENAME + " where " + UserHasGroupView.ColumnType.tagname + " = :" + UserHasGroupView.ColumnType.tagname +
		" and " + NOTDISABLE +
		" order by " + UserHasGroupView.ColumnType.owner + " desc" +
		" limit 1";
	public static String FINDMYIWANTTOS =
		"select * from " + Iwantto.TABLENAME + " where " + Iwantto.ColumnType.userid + " = :" + Iwantto.ColumnType.userid +
		" and " + NOTDISABLE +
		" order by " + Iwantto.ColumnType.description;
	public static String DELETEGROUP = 
		"update " + Group.TABLENAME + " set " +
		Group.ColumnType.disable + " = true" +
		" where " + Group.ColumnType.id + "= :" + Group.ColumnType.id;
	public static String FINDGROUPS =
		"select * from (" +
		" select " + UserHasGroupView.TABLENAME + "." + "* from " + UserHasGroupView.TABLENAME + 
		" left outer join " + Postalcode.TABLENAME + " on " + 
		UserHasGroupView.TABLENAME + "." + UserHasGroupView.ColumnType.postalcodeid + " = " +  Postalcode.TABLENAME + "." + Postalcode.ColumnType.id +
		" left outer join " + Zipcode.TABLENAME + " on " + 
		UserHasGroupView.TABLENAME + "." + UserHasGroupView.ColumnType.zipcodeid + " = " +  Zipcode.TABLENAME + "." + Zipcode.ColumnType.id +
		" where " + UserHasGroupView.TABLENAME + "." + UserHasGroupView.ColumnType.name + " like :" + UserHasGroupView.ColumnType.name + 
		" and (" + UserHasGroupView.TABLENAME + "." + UserHasGroupView.ColumnType.owner + " = 1" +
		" or " + UserHasGroupView.ColumnType.userid + " = :" + UserHasGroupView.ColumnType.userid + ")" + 
		" and " + NOTDISABLE +
		" and " + 
		EARTHRADIUS + " * ACOS( (SIN(PI()* :latitude /180)*SIN(PI() * " + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.ColumnType.latitude + "," + Zipcode.TABLENAME + "." + Zipcode.ColumnType.latitude + ")" + 
		"/180)) + " +
		"(COS(PI()* :latitude /180)*cos(PI()*" + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.ColumnType.latitude + "," + Zipcode.TABLENAME + "." + Zipcode.ColumnType.latitude + ")" +
		"/180)*COS(PI() * " + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.ColumnType.longitude + "," + Zipcode.TABLENAME + "." + Zipcode.ColumnType.longitude + ")" +
		"/180-PI()* :longitude /180)) " +
		") <= :radius" +
		" order by " + UserHasGroupView.ColumnType.name + " ," + UserHasGroupView.ColumnType.owner + 
		" ) derivedTable group by " + UserHasGroupView.ColumnType.id;
	
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

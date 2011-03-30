package com.nositer.hibernate;
import static com.nositer.hibernate.CommonSql.*;

import com.nositer.client.dto.DTO;
import com.nositer.client.dto.Lookupcode;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.UserHasGroup;
import com.nositer.client.dto.generated.GroupPlusView;
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
		"select * from " + GroupPlusView.TABLENAME + " where " + GroupPlusView.ColumnType.userid + " = :" + GroupPlusView.ColumnType.userid + 
		" and " + NOTDISABLE +
		" order by " + Group.ColumnType.name;
	public static String FINDGROUPBYTAGNAME =
		"select * from " + GroupPlusView.TABLENAME + " where " + GroupPlusView.ColumnType.tagname + " = :" + GroupPlusView.ColumnType.tagname +
		" and (" + GroupPlusView.TABLENAME + "." + GroupPlusView.ColumnType.owner + " = true" +
		" or " + GroupPlusView.ColumnType.userid + " = :" + GroupPlusView.ColumnType.userid + ")" + 
		" and " + NOTDISABLE +
		" order by " + GroupPlusView.ColumnType.owner + 
		" limit 1";
	public static String FINDMYIWANTTOS =
		"select * from " + Iwantto.TABLENAME + " where " + Iwantto.ColumnType.userid + " = :" + Iwantto.ColumnType.userid +
		" and " + NOTDISABLE +
		" order by " + Iwantto.ColumnType.description;

	public static String FINDGROUPS =
		"select * from (" +
		" select " + GroupPlusView.TABLENAME + "." + "* from " + GroupPlusView.TABLENAME + 
		" left outer join " + Postalcode.TABLENAME + " on " + 
		GroupPlusView.TABLENAME + "." + GroupPlusView.ColumnType.postalcodeid + " = " +  Postalcode.TABLENAME + "." + Postalcode.ColumnType.id +
		" left outer join " + Zipcode.TABLENAME + " on " + 
		GroupPlusView.TABLENAME + "." + GroupPlusView.ColumnType.zipcodeid + " = " +  Zipcode.TABLENAME + "." + Zipcode.ColumnType.id +
		" where " + GroupPlusView.TABLENAME + "." + GroupPlusView.ColumnType.name + " like :" + GroupPlusView.ColumnType.name + 
		" and (" + GroupPlusView.TABLENAME + "." + GroupPlusView.ColumnType.owner + " = 1" +
		" or " + GroupPlusView.ColumnType.userid + " = :" + GroupPlusView.ColumnType.userid + ")" + 
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
		" order by " + GroupPlusView.ColumnType.name + " ," + GroupPlusView.ColumnType.owner + 
		" ) derivedTable group by " + GroupPlusView.ColumnType.id;
	public static String UPDATESUBSCRIPTION =
		"update " + UserHasGroup.TABLENAME + " set " +
		UserHasGroup.ColumnType.disable + " = :" + UserHasGroup.ColumnType.disable + ", " +
		UserHasGroup.ColumnType.invisible + " = :" + UserHasGroup.ColumnType.invisible + ", " +
		MODIFIEDTIMENOW + 
		" where " + UserHasGroup.ColumnType.id + "= :" + UserHasGroup.ColumnType.id;
	public static String CREATESUBSCRIPTION =
		"insert into " + UserHasGroup.TABLENAME + "(" +
		UserHasGroup.ColumnType.userid + ", " +
		UserHasGroup.ColumnType.groupid + ", " +
		UserHasGroup.ColumnType.owner + ", " +
		UserHasGroup.ColumnType.invisible + ", " +
		UserHasGroup.ColumnType.disable + ", " +
		UserHasGroup.ColumnType.createdtime +
		")" + " values( " +
		":" + UserHasGroup.ColumnType.userid + ", " +
		":" + UserHasGroup.ColumnType.groupid + ", " +
		"false, " +
		"false, " +
		"false, " +
		NOW +
		")";
	public static String DISABLEGROUP =
		"update " + GroupPlusView.TABLENAME + " set " +
		GroupPlusView.ColumnType.disable + " = true " +
		" where " + GroupPlusView.ColumnType.id + "= :" + GroupPlusView.ColumnType.id + " and " +
		GroupPlusView.ColumnType.owner + " = true " +
		GroupPlusView.ColumnType.userid + " =:" + GroupPlusView.ColumnType.userid;

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

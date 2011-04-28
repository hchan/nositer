package com.nositer.hibernate;
import static com.nositer.hibernate.CommonSql.*;

import com.nositer.client.dto.DTO;
import com.nositer.client.dto.Lookupcode;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.UserHasGroup;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.Zipcode;
public class SqlHelper {

	public static String FINDUSERBYLOGIN = 
		"select * from " + User.TABLENAME + " where " + User.Column.login + " = :" + User.Column.login + 
		" and " + NOTDISABLE;
	public static String UPDATEBASICPROFILE =
		"update " + User.TABLENAME + " set " +
		User.Column.firstname + "= :" + User.Column.firstname + ", " +
		User.Column.lastname + "= :" + User.Column.lastname + ", " +
		User.Column.countrycode + "= :" + User.Column.countrycode + ", " +
		User.Column.postalcodeid + "= :" + User.Column.postalcodeid + ", " +
		User.Column.zipcodeid + "= :" + User.Column.zipcodeid + ", " +
		User.Column.salutationcodeid + "= :" + User.Column.salutationcodeid + ", " +
		User.Column.relationshipcodeid + "= :" + User.Column.relationshipcodeid + ", " +
		User.Column.avatarlocation + "= :" + User.Column.avatarlocation + ", " +
		User.Column.email + "= :" + User.Column.email + ", " +
		User.Column.gendermale + "= :" + User.Column.gendermale + ", " +
		User.Column.profession + "= :" + User.Column.profession + ", " +
		User.Column.birthdate + "= :" + User.Column.birthdate +
		" where " + User.Column.id + " = :" + User.Column.id;
	public static String CHANGEPASSWORD =
		"update " + User.TABLENAME + " set " + 
		User.Column.password + "= :" + User.Column.password +
		" where " + User.Column.id + " = :" + User.Column.id;
	public static String UPDATEABOUTME =
		"update " + User.TABLENAME + " set " + 
		User.Column.note + "= :" + User.Column.note + ", " +
		User.Column.notemodifedtime + "= :" + User.Column.notemodifedtime + ", " +
		User.Column.description + "= :" + User.Column.description +
		" where " + User.Column.id + " = :" + User.Column.id;
	public static String UPDATEAVATAR =
		"update " + User.TABLENAME + " set " + 
		User.Column.avatarlocation + "= :" + User.Column.avatarlocation  +
		" where " + User.Column.id + " = :" + User.Column.id;
	public static String FINDMYGROUPS =
		"select * from " + GroupPlusView.TABLENAME + " where " + GroupPlusView.Column.userid + " = :" + GroupPlusView.Column.userid + 
		" and " + NOTDISABLE +
		" order by " + Group.Column.name;
	public static String FINDGROUPBYTAGNAME =
		"select * from " + GroupPlusView.TABLENAME + " where " + GroupPlusView.Column.tagname + " = :" + GroupPlusView.Column.tagname +
		" and (" + GroupPlusView.TABLENAME + "." + GroupPlusView.Column.owner + " = true" +
		" or " + GroupPlusView.Column.userid + " = :" + GroupPlusView.Column.userid + ")" + 
		" and " + NOTDISABLE +
		" order by " + GroupPlusView.Column.owner + 
		" limit 1";
	public static String FINDMYIWANTTOS =
		"select * from " + Iwantto.TABLENAME + " where " + Iwantto.Column.userid + " = :" + Iwantto.Column.userid +
		" and " + NOTDISABLE +
		" order by " + Iwantto.Column.description;

	public static String FINDGROUPS =
		"select * from (" +
		" select " + GroupPlusView.TABLENAME + "." + "* from " + GroupPlusView.TABLENAME + 
		" left outer join " + Postalcode.TABLENAME + " on " + 
		GroupPlusView.TABLENAME + "." + GroupPlusView.Column.postalcodeid + " = " +  Postalcode.TABLENAME + "." + Postalcode.Column.id +
		" left outer join " + Zipcode.TABLENAME + " on " + 
		GroupPlusView.TABLENAME + "." + GroupPlusView.Column.zipcodeid + " = " +  Zipcode.TABLENAME + "." + Zipcode.Column.id +
		" where " + "lower(" + GroupPlusView.TABLENAME + "." + GroupPlusView.Column.name + ") like lower(:" + GroupPlusView.Column.name + ") " + 
		" and (" + GroupPlusView.TABLENAME + "." + GroupPlusView.Column.owner + " = 1" +
		" or " + GroupPlusView.Column.userid + " = :" + GroupPlusView.Column.userid + ")" + 
		" and " + NOTDISABLE +
		" and " + 
		EARTHRADIUS + " * ACOS( (SIN(PI()* :latitude /180)*SIN(PI() * " + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.Column.latitude + "," + Zipcode.TABLENAME + "." + Zipcode.Column.latitude + ")" + 
		"/180)) + " +
		"(COS(PI()* :latitude /180)*cos(PI()*" + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.Column.latitude + "," + Zipcode.TABLENAME + "." + Zipcode.Column.latitude + ")" +
		"/180)*COS(PI() * " + 
		"coalesce(" + Postalcode.TABLENAME + "." + Postalcode.Column.longitude + "," + Zipcode.TABLENAME + "." + Zipcode.Column.longitude + ")" +
		"/180-PI()* :longitude /180)) " +
		") <= :radius" +
		" order by " + GroupPlusView.Column.name + " ," + GroupPlusView.Column.owner + 
		" ) derivedTable group by " + GroupPlusView.Column.id;
	public static String UPDATESUBSCRIPTION =
		"update " + UserHasGroup.TABLENAME + " set " +
		UserHasGroup.Column.disable + " = :" + UserHasGroup.Column.disable + ", " +
		UserHasGroup.Column.invisible + " = :" + UserHasGroup.Column.invisible + ", " +
		MODIFIEDTIMENOW + 
		" where " + UserHasGroup.Column.id + "= :" + UserHasGroup.Column.id;
	public static String CREATESUBSCRIPTION =
		"insert into " + UserHasGroup.TABLENAME + "(" +
		UserHasGroup.Column.userid + ", " +
		UserHasGroup.Column.groupid + ", " +
		UserHasGroup.Column.owner + ", " +
		UserHasGroup.Column.invisible + ", " +
		UserHasGroup.Column.disable + ", " +
		UserHasGroup.Column.createdtime +
		")" + " values( " +
		":" + UserHasGroup.Column.userid + ", " +
		":" + UserHasGroup.Column.groupid + ", " +
		"false, " +
		"false, " +
		"false, " +
		NOW +
		")";
	public static String DISABLEGROUP =
		"update " + GroupPlusView.TABLENAME + " set " +
		GroupPlusView.Column.disable + " = true " +
		" where " + GroupPlusView.Column.id + "= :" + GroupPlusView.Column.id + " and " +
		GroupPlusView.Column.owner + " = true " +
		GroupPlusView.Column.userid + " =:" + GroupPlusView.Column.userid;
	public static String GETSUBSCRIPTIONS =
		"select * from " + GroupSubscriptionView.TABLENAME + 
		" where " + GroupSubscriptionView.Column.groupid + "= :" + GroupSubscriptionView.Column.groupid + " and " +
		GroupSubscriptionView.Column.user_disable + " = false " +
		" order by " + GroupSubscriptionView.Column.lastname + ", " + GroupSubscriptionView.Column.firstname;
	public static String FINDSUBSCRIPTIONS =
		"select * from " + GroupSubscriptionView.TABLENAME + 
		" where " + GroupSubscriptionView.Column.groupid + "= :" + GroupSubscriptionView.Column.groupid + 
		" and " + GroupSubscriptionView.Column.user_disable + " = false " +
		" and " + "lower(" + GroupSubscriptionView.Column.lastname + ") like lower(:" + GroupSubscriptionView.Column.lastname + ")" + 
		" order by " + GroupSubscriptionView.Column.lastname + ", " + GroupSubscriptionView.Column.firstname;
	public static String FINDSUBSCRIBER =
		"select * from " + GroupSubscriptionView.TABLENAME + 
		" where " + GroupSubscriptionView.Column.groupid + "= :" + GroupSubscriptionView.Column.groupid + 
		" and " + GroupSubscriptionView.Column.user_disable + " = false " +
		" and " + GroupSubscriptionView.Column.userid + " = :" + GroupSubscriptionView.Column.userid;
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

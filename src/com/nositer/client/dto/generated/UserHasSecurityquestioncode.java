package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Mar 23, 2011 4:24:28 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry

//import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.extjs.gxt.ui.client.data.BeanModelTag;
//import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
/**
 * UserHasSecurityquestioncode generated by hbm2java
 */
public class UserHasSecurityquestioncode implements java.io.Serializable,
		IsSerializable, BeanModelTag, DTO {

	private Integer id;
	private Securityquestioncode securityquestioncode;
	private User user;
	private Date createdtime;
	private Date modifiedtime;
	public static final String TABLENAME = "nositer.userhassecurityquestioncode";

	public String getTablename() {
		return TABLENAME;
	}

	public UserHasSecurityquestioncode() {
	}

	public UserHasSecurityquestioncode(
			Securityquestioncode securityquestioncode, User user) {
		this.securityquestioncode = securityquestioncode;
		this.user = user;
	}

	public UserHasSecurityquestioncode(
			Securityquestioncode securityquestioncode, User user,
			Date createdtime, Date modifiedtime) {
		this.securityquestioncode = securityquestioncode;
		this.user = user;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Securityquestioncode getSecurityquestioncode() {
		return this.securityquestioncode;
	}

	public void setSecurityquestioncode(
			Securityquestioncode securityquestioncode) {
		this.securityquestioncode = securityquestioncode;
	}

	private Integer securityquestioncodeid;

	public Integer getSecurityquestioncodeid() {
		return this.securityquestioncodeid;
	}

	public void setSecurityquestioncodeid(Integer securityquestioncodeid) {
		this.securityquestioncodeid = securityquestioncodeid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Integer userid;

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public enum ColumnType {
		id, securityquestioncodeid, userid, createdtime, modifiedtime,
	}
}

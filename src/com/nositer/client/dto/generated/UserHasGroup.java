package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Feb 26, 2011 6:36:23 PM by Hibernate Tools 3.2.4.GA
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
 * UserHasGroup generated by hbm2java
 */
public class UserHasGroup implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO {

	private Integer id;
	private Group group;
	private User user;
	private Date createdtime;
	private Date modifiedtime;
	public static final String TABLENAME = "nositer.userhasgroup";

	public String getTablename() {
		return TABLENAME;
	}

	public UserHasGroup() {
	}

	public UserHasGroup(Group group, User user) {
		this.group = group;
		this.user = user;
	}

	public UserHasGroup(Group group, User user, Date createdtime,
			Date modifiedtime) {
		this.group = group;
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

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private Integer groupid;

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
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
		id, groupid, userid, createdtime, modifiedtime,
	}
}

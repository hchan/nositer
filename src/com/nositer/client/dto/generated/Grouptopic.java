package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated May 10, 2011 3:35:24 PM by Hibernate Tools 3.2.4.GA
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
 * Grouptopic generated by hbm2java
 */
public class Grouptopic implements java.io.Serializable, IsSerializable,
		Cloneable, BeanModelTag, DTO {

	private Integer id;
	private Group group;
	private User user;
	private String name;
	private Date createdtime;
	private Date modifiedtime;
	private Set<Groupmessage> groupmessages = new HashSet<Groupmessage>(0);
	public static final String TABLENAME = "nositer.grouptopic";

	public String getTablename() {
		return TABLENAME;
	}

	public Grouptopic() {
	}

	public Grouptopic(Group group, User user, String name, Date createdtime,
			Date modifiedtime, Set<Groupmessage> groupmessages) {
		this.group = group;
		this.user = user;
		this.name = name;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.groupmessages = groupmessages;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set<Groupmessage> getGroupmessages() {
		return this.groupmessages;
	}

	public void setGroupmessages(Set<Groupmessage> groupmessages) {
		this.groupmessages = groupmessages;
	}

	public Grouptopic clone() {

		Grouptopic retval = new Grouptopic();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (name != null) {
			retval.setName(new String(name));
		}
		if (createdtime != null) {
			retval.setCreatedtime((Date) createdtime.clone());
		}
		if (modifiedtime != null) {
			retval.setModifiedtime((Date) modifiedtime.clone());
		}

		return retval;
	}

	public enum Column {
		id, groupid, userid, name, createdtime, modifiedtime,
	}
}
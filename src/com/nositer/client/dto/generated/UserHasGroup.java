package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated 24-Jan-2011 12:24:12 AM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry

import com.google.gwt.user.client.rpc.IsSerializable; //import java.util.List;
//import java.util.ArrayList;

import java.util.Date;

@SuppressWarnings("serial")
/**
 * UserHasGroup generated by hbm2java
 */
public class UserHasGroup implements java.io.Serializable, IsSerializable, DTO {

	private Integer id;
	private Group group;
	private User user;
	private Date createdtime;
	private Date modifiedtime;

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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

}

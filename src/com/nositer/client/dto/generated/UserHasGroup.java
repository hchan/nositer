package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Aug 9, 2011 3:20:43 PM by Hibernate Tools 3.2.4.GA
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
		Cloneable, BeanModelTag, DTO, Comparable {

	private Integer id;
	private Group group;
	private User user;
	private Boolean owner;
	private Boolean invisible;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;
	public static final String TABLENAME = "nositer.user_has_group";

	public String getTablename() {
		return TABLENAME;
	}

	public UserHasGroup() {
	}

	public UserHasGroup(Group group, User user) {
		this.group = group;
		this.user = user;
	}

	public UserHasGroup(Group group, User user, Boolean owner,
			Boolean invisible, Boolean disable, Date createdtime,
			Date modifiedtime) {
		this.group = group;
		this.user = user;
		this.owner = owner;
		this.invisible = invisible;
		this.disable = disable;
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

	public Boolean getOwner() {
		return this.owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	public Boolean getInvisible() {
		return this.invisible;
	}

	public void setInvisible(Boolean invisible) {
		this.invisible = invisible;
	}

	public Boolean getDisable() {
		return this.disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
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

	public UserHasGroup clone() {

		UserHasGroup retval = new UserHasGroup();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (owner != null) {
			retval.setOwner(new Boolean(owner));
		}
		if (invisible != null) {
			retval.setInvisible(new Boolean(invisible));
		}
		if (disable != null) {
			retval.setDisable(new Boolean(disable));
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
		id, groupid, userid, owner, invisible, disable, createdtime, modifiedtime,
	}

	@Override
	public int compareTo(Object o) {
		DTO otherDTO = (DTO) o;
		return getId().compareTo(otherDTO.getId());
	}
}

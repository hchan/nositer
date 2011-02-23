package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Feb 22, 2011 3:59:50 PM by Hibernate Tools 3.2.4.GA
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
 * Relationshipcode generated by hbm2java
 */
public class Relationshipcode implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO, Lookupcode {

	private Integer id;
	private String code;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	public static final String TABLENAME = "nositer.relationshipcode";

	public String getTablename() {
		return TABLENAME;
	}

	public Relationshipcode() {
	}

	public Relationshipcode(String code) {
		this.code = code;
	}

	public Relationshipcode(String code, String description, Date createdtime,
			Date modifiedtime, Set<User> users) {
		this.code = code;
		this.description = description;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.users = users;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public enum ColumnType {
		id, code, description, createdtime, modifiedtime,
	}
}

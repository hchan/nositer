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
 * Salutationcode generated by hbm2java
 */
public class Salutationcode implements java.io.Serializable, IsSerializable,
		Cloneable, BeanModelTag, DTO, Lookupcode {

	private Integer id;
	private String code;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	public static final String TABLENAME = "nositer.salutationcode";

	public String getTablename() {
		return TABLENAME;
	}

	public Salutationcode() {
	}

	public Salutationcode(String code) {
		this.code = code;
	}

	public Salutationcode(String code, String description, Date createdtime,
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

	public Salutationcode clone() {

		Salutationcode retval = new Salutationcode();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (code != null) {
			retval.setCode(new String(code));
		}
		if (description != null) {
			retval.setDescription(new String(description));
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
		id, code, description, createdtime, modifiedtime,
	}
}

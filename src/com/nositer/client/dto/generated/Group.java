package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Jan 29, 2011 6:18:24 PM by Hibernate Tools 3.2.4.GA
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
 * Group generated by hbm2java
 */
public class Group implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO {

	private Integer id;
	private String name;
	private String description;
	private String shortname;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);

	public Group() {
	}

	public Group(String name, String description, String shortname,
			Date createdtime, Date modifiedtime, Set<UserHasGroup> userHasGroups) {
		this.name = name;
		this.description = description;
		this.shortname = shortname;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.userHasGroups = userHasGroups;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
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

	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

	public ArrayList<String> getColumnNames() {
		ArrayList<String> retval = new ArrayList<String>();
		retval.add("id");
		retval.add("name");
		retval.add("description");
		retval.add("shortname");
		retval.add("createdtime");
		retval.add("modifiedtime");
		retval.add("userHasGroups");
		return retval;
	}
}

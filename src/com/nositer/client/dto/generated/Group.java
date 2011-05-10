package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated May 10, 2011 10:32:55 AM by Hibernate Tools 3.2.4.GA
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
public class Group implements java.io.Serializable, IsSerializable, Cloneable,
		BeanModelTag, DTO {

	private Integer id;
	private Zipcode zipcode;
	private Postalcode postalcode;
	private String tagname;
	private String countrycode;
	private String name;
	private String description;
	private String avatarlocation;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);
	public static final String TABLENAME = "nositer.group";

	public String getTablename() {
		return TABLENAME;
	}

	public Group() {
	}

	public Group(String tagname, String countrycode, String name,
			String description) {
		this.tagname = tagname;
		this.countrycode = countrycode;
		this.name = name;
		this.description = description;
	}

	public Group(Zipcode zipcode, Postalcode postalcode, String tagname,
			String countrycode, String name, String description,
			String avatarlocation, Boolean disable, Date createdtime,
			Date modifiedtime, Set<UserHasGroup> userHasGroups) {
		this.zipcode = zipcode;
		this.postalcode = postalcode;
		this.tagname = tagname;
		this.countrycode = countrycode;
		this.name = name;
		this.description = description;
		this.avatarlocation = avatarlocation;
		this.disable = disable;
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

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	private Integer zipcodeid;

	public Integer getZipcodeid() {
		return this.zipcodeid;
	}

	public void setZipcodeid(Integer zipcodeid) {
		this.zipcodeid = zipcodeid;
	}

	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	private Integer postalcodeid;

	public Integer getPostalcodeid() {
		return this.postalcodeid;
	}

	public void setPostalcodeid(Integer postalcodeid) {
		this.postalcodeid = postalcodeid;
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
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

	public String getAvatarlocation() {
		return this.avatarlocation;
	}

	public void setAvatarlocation(String avatarlocation) {
		this.avatarlocation = avatarlocation;
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

	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

	public Group clone() {

		Group retval = new Group();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (tagname != null) {
			retval.setTagname(new String(tagname));
		}
		if (countrycode != null) {
			retval.setCountrycode(new String(countrycode));
		}
		if (name != null) {
			retval.setName(new String(name));
		}
		if (description != null) {
			retval.setDescription(new String(description));
		}
		if (avatarlocation != null) {
			retval.setAvatarlocation(new String(avatarlocation));
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
		id, zipcodeid, postalcodeid, tagname, countrycode, name, description, avatarlocation, disable, createdtime, modifiedtime,
	}
}

package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Feb 23, 2011 2:14:07 PM by Hibernate Tools 3.2.4.GA
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
	private Zipcode zipcode;
	private User user;
	private Postalcode postalcode;
	private String tagname;
	private String countrycode;
	private String name;
	private String description;
	private String avatarlocation;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);
	public static final String TABLENAME = "nositer.group";

	public String getTablename() {
		return TABLENAME;
	}

	public Group() {
	}

	public Group(User user, String tagname, String countrycode) {
		this.user = user;
		this.tagname = tagname;
		this.countrycode = countrycode;
	}

	public Group(Zipcode zipcode, User user, Postalcode postalcode,
			String tagname, String countrycode, String name,
			String description, String avatarlocation, Date createdtime,
			Date modifiedtime, Set<UserHasGroup> userHasGroups) {
		this.zipcode = zipcode;
		this.user = user;
		this.postalcode = postalcode;
		this.tagname = tagname;
		this.countrycode = countrycode;
		this.name = name;
		this.description = description;
		this.avatarlocation = avatarlocation;
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

	public enum ColumnType {
		id, zipcodeid, userid, postalcodeid, tagname, countrycode, name, description, avatarlocation, createdtime, modifiedtime,
	}
}

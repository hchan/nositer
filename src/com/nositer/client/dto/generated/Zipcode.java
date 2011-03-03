package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Mar 2, 2011 4:31:18 PM by Hibernate Tools 3.2.4.GA
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
 * Zipcode generated by hbm2java
 */
public class Zipcode implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO, Lookupcode {

	private Integer id;
	private String code;
	private String description;
	private String latitude;
	private String longitude;
	private String city;
	private String state;
	private String county;
	private String zipClass;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	private Set<Group> groups = new HashSet<Group>(0);
	public static final String TABLENAME = "nositer.zipcode";

	public String getTablename() {
		return TABLENAME;
	}

	public Zipcode() {
	}

	public Zipcode(String code) {
		this.code = code;
	}

	public Zipcode(String code, String description, String latitude,
			String longitude, String city, String state, String county,
			String zipClass, Date createdtime, Date modifiedtime,
			Set<User> users, Set<Group> groups) {
		this.code = code;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.county = county;
		this.zipClass = zipClass;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.users = users;
		this.groups = groups;
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

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getZipClass() {
		return this.zipClass;
	}

	public void setZipClass(String zipClass) {
		this.zipClass = zipClass;
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

	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public enum ColumnType {
		id, code, description, latitude, longitude, city, state, county, zip_class, createdtime, modifiedtime,
	}
}

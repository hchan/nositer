package com.nositer.client.dto.generated;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.nositer.client.dto.DTO;
import com.nositer.client.dto.Lookupcode;

@SuppressWarnings("serial")
/**
 * Postalcode generated by hbm2java
 */
public class Postalcode implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO, Lookupcode {

	private Integer id;
	private String code;
	private String description;
	private String city;
	private String province;
	private String provincecode;
	private String citytype;
	private String latitude;
	private String longitude;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	private Set<Group> groups = new HashSet<Group>(0);
	public static final String TABLENAME = "nositer.postalcode";

	public String getTablename() {
		return TABLENAME;
	}

	public Postalcode() {
	}

	public Postalcode(String code) {
		this.code = code;
	}

	public Postalcode(String code, String description, String city,
			String province, String provincecode, String citytype,
			String latitude, String longitude, Date createdtime,
			Date modifiedtime, Set<User> users, Set<Group> groups) {
		this.code = code;
		this.description = description;
		this.city = city;
		this.province = province;
		this.provincecode = provincecode;
		this.citytype = citytype;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvincecode() {
		return this.provincecode;
	}

	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}

	public String getCitytype() {
		return this.citytype;
	}

	public void setCitytype(String citytype) {
		this.citytype = citytype;
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
		id, code, description, city, province, provincecode, citytype, latitude, longitude, createdtime, modifiedtime,
	}
}

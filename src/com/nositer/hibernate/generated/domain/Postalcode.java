package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Sep 27, 2011 2:18:59 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
/**
 * Postalcode generated by hbm2java
 */
@Entity
@Table(name = "postalcode", catalog = "nositer", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Postalcode implements java.io.Serializable, Domain {

	private Integer id;
	private String code;
	private String description;
	private String city;
	private String province;
	private String provincecode;
	private String citytype;
	private Float latitude;
	private Float longitude;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	private Set<Group> groups = new HashSet<Group>(0);

	public Postalcode() {
	}

	public Postalcode(String code) {
		this.code = code;
	}

	public Postalcode(String code, String description, String city,
			String province, String provincecode, String citytype,
			Float latitude, Float longitude, Date createdtime,
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", unique = true, nullable = false, length = 128)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "city", length = 128)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", length = 128)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "provincecode", length = 128)
	public String getProvincecode() {
		return this.provincecode;
	}

	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}

	@Column(name = "citytype", length = 128)
	public String getCitytype() {
		return this.citytype;
	}

	public void setCitytype(String citytype) {
		this.citytype = citytype;
	}

	@Column(name = "latitude", precision = 9, scale = 6)
	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 9, scale = 6)
	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdtime", length = 19)
	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedtime", length = 19)
	public Date getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postalcode")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "postalcode")
	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}

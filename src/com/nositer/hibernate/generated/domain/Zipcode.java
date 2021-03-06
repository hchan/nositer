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
 * Zipcode generated by hbm2java
 */
@Entity
@Table(name = "zipcode", catalog = "nositer", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Zipcode implements java.io.Serializable, Domain {

	private Integer id;
	private String code;
	private String description;
	private Float latitude;
	private Float longitude;
	private String city;
	private String state;
	private String county;
	private String zipClass;
	private Date createdtime;
	private Date modifiedtime;
	private Set<User> users = new HashSet<User>(0);
	private Set<Group> groups = new HashSet<Group>(0);

	public Zipcode() {
	}

	public Zipcode(String code) {
		this.code = code;
	}

	public Zipcode(String code, String description, Float latitude,
			Float longitude, String city, String state, String county,
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

	@Column(name = "city", length = 128)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "state", length = 128)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "county", length = 128)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Column(name = "zip_class", length = 128)
	public String getZipClass() {
		return this.zipClass;
	}

	public void setZipClass(String zipClass) {
		this.zipClass = zipClass;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "zipcode")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "zipcode")
	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}

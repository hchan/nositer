package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Aug 9, 2011 3:20:42 PM by Hibernate Tools 3.2.4.GA
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
/**
 * Group generated by hbm2java
 */
@Entity
@Table(name = "group", catalog = "nositer", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "tagname") })
public class Group implements java.io.Serializable, Domain {

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
	private Set<Grouptopic> grouptopics = new HashSet<Grouptopic>(0);
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);

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
			Date modifiedtime, Set<Grouptopic> grouptopics,
			Set<UserHasGroup> userHasGroups) {
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
		this.grouptopics = grouptopics;
		this.userHasGroups = userHasGroups;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zipcodeid")
	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	@Transient
	public Integer getZipcodeid() {
		Integer retval = null;
		try {
			retval = getZipcode().getId();
		} catch (Exception e) {
		}
		return retval;
	}

	/*
	 @Transient
	 public void setZipcodeid (Integer zipcodeid) {
	 if (getZipcode() != null) {
	 getZipcode().setId(zipcodeid);
	 }
	 }
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postalcodeid")
	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	@Transient
	public Integer getPostalcodeid() {
		Integer retval = null;
		try {
			retval = getPostalcode().getId();
		} catch (Exception e) {
		}
		return retval;
	}

	/*
	 @Transient
	 public void setPostalcodeid (Integer postalcodeid) {
	 if (getPostalcode() != null) {
	 getPostalcode().setId(postalcodeid);
	 }
	 }
	 */

	@Column(name = "tagname", unique = true, nullable = false, length = 64)
	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	@Column(name = "countrycode", nullable = false, length = 3)
	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Column(name = "name", unique = true, nullable = false, length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "avatarlocation", length = 128)
	public String getAvatarlocation() {
		return this.avatarlocation;
	}

	public void setAvatarlocation(String avatarlocation) {
		this.avatarlocation = avatarlocation;
	}

	@Column(name = "disable")
	public Boolean getDisable() {
		return this.disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<Grouptopic> getGrouptopics() {
		return this.grouptopics;
	}

	public void setGrouptopics(Set<Grouptopic> grouptopics) {
		this.grouptopics = grouptopics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

}

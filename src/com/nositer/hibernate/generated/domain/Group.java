package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;

// Generated Jan 18, 2011 2:25:17 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;

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

@SuppressWarnings("serial")
/**
 * Group generated by hbm2java
 */
@Entity
@Table(name = "group", catalog = "nositer")
public class Group implements java.io.Serializable, Domain {

	private Integer id;
	private String name;
	private String description;
	private String shortname;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);

	public Group() {
	}

	public Group(String name, String description, String shortname,
			Set<UserHasGroup> userHasGroups) {
		this.name = name;
		this.description = description;
		this.shortname = shortname;
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

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 128)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "shortname", length = 128)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

}

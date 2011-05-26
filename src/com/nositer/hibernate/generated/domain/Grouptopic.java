package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated May 25, 2011 4:44:42 PM by Hibernate Tools 3.2.4.GA
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

@SuppressWarnings("serial")
/**
 * Grouptopic generated by hbm2java
 */
@Entity
@Table(name = "grouptopic", catalog = "nositer")
public class Grouptopic implements java.io.Serializable, Domain {

	private Integer id;
	private Group group;
	private User user;
	private String name;
	private Date createdtime;
	private Date modifiedtime;
	private Set<Groupmessage> groupmessages = new HashSet<Groupmessage>(0);

	public Grouptopic() {
	}

	public Grouptopic(Group group, User user, String name, Date createdtime,
			Date modifiedtime, Set<Groupmessage> groupmessages) {
		this.group = group;
		this.user = user;
		this.name = name;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.groupmessages = groupmessages;
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
	@JoinColumn(name = "groupid")
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Transient
	public Integer getGroupid() {
		Integer retval = null;
		try {
			retval = getGroup().getId();
		} catch (Exception e) {
		}
		return retval;
	}

	/*
	 @Transient
	 public void setGroupid (Integer groupid) {
	 if (getGroup() != null) {
	 getGroup().setId(groupid);
	 }
	 }
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public Integer getUserid() {
		Integer retval = null;
		try {
			retval = getUser().getId();
		} catch (Exception e) {
		}
		return retval;
	}

	/*
	 @Transient
	 public void setUserid (Integer userid) {
	 if (getUser() != null) {
	 getUser().setId(userid);
	 }
	 }
	 */

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grouptopic")
	public Set<Groupmessage> getGroupmessages() {
		return this.groupmessages;
	}

	public void setGroupmessages(Set<Groupmessage> groupmessages) {
		this.groupmessages = groupmessages;
	}

}

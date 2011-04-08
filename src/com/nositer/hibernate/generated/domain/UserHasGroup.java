package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Apr 8, 2011 4:53:21 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
/**
 * UserHasGroup generated by hbm2java
 */
@Entity
@Table(name = "user_has_group", catalog = "nositer", uniqueConstraints = @UniqueConstraint(columnNames = {
		"userid", "groupid" }))
public class UserHasGroup implements java.io.Serializable, Domain {

	private Integer id;
	private Group group;
	private User user;
	private Boolean owner;
	private Boolean invisible;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;

	public UserHasGroup() {
	}

	public UserHasGroup(Group group, User user) {
		this.group = group;
		this.user = user;
	}

	public UserHasGroup(Group group, User user, Boolean owner,
			Boolean invisible, Boolean disable, Date createdtime,
			Date modifiedtime) {
		this.group = group;
		this.user = user;
		this.owner = owner;
		this.invisible = invisible;
		this.disable = disable;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
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
	@JoinColumn(name = "groupid", nullable = false)
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
	@JoinColumn(name = "userid", nullable = false)
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

	@Column(name = "owner")
	public Boolean getOwner() {
		return this.owner;
	}

	public void setOwner(Boolean owner) {
		this.owner = owner;
	}

	@Column(name = "invisible")
	public Boolean getInvisible() {
		return this.invisible;
	}

	public void setInvisible(Boolean invisible) {
		this.invisible = invisible;
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

}

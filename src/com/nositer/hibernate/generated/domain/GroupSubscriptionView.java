package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated May 20, 2011 11:14:59 AM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
/**
 * GroupSubscriptionView generated by hbm2java
 */
@Entity
@Table(name = "group_subscription_view", catalog = "nositer")
public class GroupSubscriptionView implements java.io.Serializable, Domain {

	private Integer id;
	private Integer userid;
	private Integer groupid;
	private Boolean owner;
	private Boolean invisible;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;
	private String firstname;
	private String lastname;
	private Boolean gendermale;
	private String avatarlocation;
	private Boolean userDisable;

	public GroupSubscriptionView() {
	}

	public GroupSubscriptionView(Integer id, Integer userid, Integer groupid) {
		this.id = id;
		this.userid = userid;
		this.groupid = groupid;
	}

	public GroupSubscriptionView(Integer id, Integer userid, Integer groupid,
			Boolean owner, Boolean invisible, Boolean disable,
			Date createdtime, Date modifiedtime, String firstname,
			String lastname, Boolean gendermale, String avatarlocation,
			Boolean userDisable) {
		this.id = id;
		this.userid = userid;
		this.groupid = groupid;
		this.owner = owner;
		this.invisible = invisible;
		this.disable = disable;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gendermale = gendermale;
		this.avatarlocation = avatarlocation;
		this.userDisable = userDisable;
	}

	@Id
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid", nullable = false)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "groupid", nullable = false)
	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

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

	@Column(name = "firstname", length = 128)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 128)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "gendermale")
	public Boolean getGendermale() {
		return this.gendermale;
	}

	public void setGendermale(Boolean gendermale) {
		this.gendermale = gendermale;
	}

	@Column(name = "avatarlocation", length = 128)
	public String getAvatarlocation() {
		return this.avatarlocation;
	}

	public void setAvatarlocation(String avatarlocation) {
		this.avatarlocation = avatarlocation;
	}

	@Column(name = "user_disable")
	public Boolean getUserDisable() {
		return this.userDisable;
	}

	public void setUserDisable(Boolean userDisable) {
		this.userDisable = userDisable;
	}

}

package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Aug 9, 2011 3:20:42 PM by Hibernate Tools 3.2.4.GA
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
 * GroupmessagePlusView generated by hbm2java
 */
@Entity
@Table(name = "groupmessage_plus_view", catalog = "nositer")
public class GroupmessagePlusView implements java.io.Serializable, Domain {

	private Integer id;
	private Integer userid;
	private Integer grouptopicid;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private String name;
	private String firstname;
	private String lastname;
	private String login;

	public GroupmessagePlusView() {
	}

	public GroupmessagePlusView(Integer id) {
		this.id = id;
	}

	public GroupmessagePlusView(Integer id, Integer userid,
			Integer grouptopicid, String description, Date createdtime,
			Date modifiedtime, String name, String firstname, String lastname,
			String login) {
		this.id = id;
		this.userid = userid;
		this.grouptopicid = grouptopicid;
		this.description = description;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.name = name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
	}

	@Id
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "grouptopicid")
	public Integer getGrouptopicid() {
		return this.grouptopicid;
	}

	public void setGrouptopicid(Integer grouptopicid) {
		this.grouptopicid = grouptopicid;
	}

	@Column(name = "description", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "login", length = 128)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}

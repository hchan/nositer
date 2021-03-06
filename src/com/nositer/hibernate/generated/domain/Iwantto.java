package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Sep 27, 2011 2:18:59 PM by Hibernate Tools 3.2.4.GA
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

@SuppressWarnings("serial")
/**
 * Iwantto generated by hbm2java
 */
@Entity
@Table(name = "iwantto", catalog = "nositer")
public class Iwantto implements java.io.Serializable, Domain {

	private Integer id;
	private Blog blog;
	private User user;
	private String description;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;

	public Iwantto() {
	}

	public Iwantto(User user, String description) {
		this.user = user;
		this.description = description;
	}

	public Iwantto(Blog blog, User user, String description, Boolean disable,
			Date createdtime, Date modifiedtime) {
		this.blog = blog;
		this.user = user;
		this.description = description;
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
	@JoinColumn(name = "blogid")
	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Transient
	public Integer getBlogid() {
		Integer retval = null;
		try {
			retval = getBlog().getId();
		} catch (Exception e) {
		}
		return retval;
	}

	/*
	 @Transient
	 public void setBlogid (Integer blogid) {
	 if (getBlog() != null) {
	 getBlog().setId(blogid);
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

	@Column(name = "description", nullable = false, length = 128)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

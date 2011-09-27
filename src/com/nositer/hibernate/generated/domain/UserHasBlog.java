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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
/**
 * UserHasBlog generated by hbm2java
 */
@Entity
@Table(name = "user_has_blog", catalog = "nositer")
public class UserHasBlog implements java.io.Serializable, Domain {

	private Integer id;
	private Blog blog;
	private User user;
	private Date createdtime;
	private Date modifiedtime;

	public UserHasBlog() {
	}

	public UserHasBlog(Integer id, Blog blog, User user) {
		this.id = id;
		this.blog = blog;
		this.user = user;
	}

	public UserHasBlog(Integer id, Blog blog, User user, Date createdtime,
			Date modifiedtime) {
		this.id = id;
		this.blog = blog;
		this.user = user;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blogid", nullable = false)
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

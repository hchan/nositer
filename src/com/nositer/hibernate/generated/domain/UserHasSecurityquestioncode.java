package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;

// Generated Feb 16, 2011 5:09:50 PM by Hibernate Tools 3.2.4.GA
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
 * UserHasSecurityquestioncode generated by hbm2java
 */
@Entity
@Table(name = "user_has_securityquestioncode", catalog = "nositer")
public class UserHasSecurityquestioncode implements java.io.Serializable,
		Domain {

	private Integer id;
	private Securityquestioncode securityquestioncode;
	private User user;
	private Date createdtime;
	private Date modifiedtime;

	public UserHasSecurityquestioncode() {
	}

	public UserHasSecurityquestioncode(
			Securityquestioncode securityquestioncode, User user) {
		this.securityquestioncode = securityquestioncode;
		this.user = user;
	}

	public UserHasSecurityquestioncode(
			Securityquestioncode securityquestioncode, User user,
			Date createdtime, Date modifiedtime) {
		this.securityquestioncode = securityquestioncode;
		this.user = user;
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
	@JoinColumn(name = "securityquestioncodeid", nullable = false)
	public Securityquestioncode getSecurityquestioncode() {
		return this.securityquestioncode;
	}

	public void setSecurityquestioncode(
			Securityquestioncode securityquestioncode) {
		this.securityquestioncode = securityquestioncode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

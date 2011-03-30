package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;
import javax.persistence.Transient;

// Generated Mar 29, 2011 5:10:11 PM by Hibernate Tools 3.2.4.GA
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
 * Securityquestioncode generated by hbm2java
 */
@Entity
@Table(name = "securityquestioncode", catalog = "nositer", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Securityquestioncode implements java.io.Serializable, Domain {

	private Integer id;
	private String code;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes = new HashSet<UserHasSecurityquestioncode>(
			0);

	public Securityquestioncode() {
	}

	public Securityquestioncode(String code) {
		this.code = code;
	}

	public Securityquestioncode(String code, String description,
			Date createdtime, Date modifiedtime,
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.code = code;
		this.description = description;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
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

	@Column(name = "description", length = 10248)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "securityquestioncode")
	public Set<UserHasSecurityquestioncode> getUserHasSecurityquestioncodes() {
		return this.userHasSecurityquestioncodes;
	}

	public void setUserHasSecurityquestioncodes(
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
	}

}

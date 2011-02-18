package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;

// Generated Feb 17, 2011 4:04:50 PM by Hibernate Tools 3.2.4.GA
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
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "nositer", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User implements java.io.Serializable, Domain {

	private Integer id;
	private Salutationcode salutationcode;
	private Zipcode zipcode;
	private Relationshipcode relationshipcode;
	private Postalcode postalcode;
	private String countrycode;
	private String login;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Boolean gendermale;
	private Date birthdate;
	private String profession;
	private String avatarlocation;
	private String note;
	private Date notemodifedtime;
	private String description;
	private Date lastlogin;
	private Date createdtime;
	private Date modifiedtime;
	private Set<Group> groups = new HashSet<Group>(0);
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);
	private Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes = new HashSet<UserHasSecurityquestioncode>(
			0);

	public User() {
	}

	public User(String countrycode, String login, String firstname,
			String lastname, String password) {
		this.countrycode = countrycode;
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public User(Salutationcode salutationcode, Zipcode zipcode,
			Relationshipcode relationshipcode, Postalcode postalcode,
			String countrycode, String login, String firstname,
			String lastname, String password, String email, Boolean gendermale,
			Date birthdate, String profession, String avatarlocation,
			String note, Date notemodifedtime, String description,
			Date lastlogin, Date createdtime, Date modifiedtime,
			Set<Group> groups, Set<UserHasGroup> userHasGroups,
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.salutationcode = salutationcode;
		this.zipcode = zipcode;
		this.relationshipcode = relationshipcode;
		this.postalcode = postalcode;
		this.countrycode = countrycode;
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.gendermale = gendermale;
		this.birthdate = birthdate;
		this.profession = profession;
		this.avatarlocation = avatarlocation;
		this.note = note;
		this.notemodifedtime = notemodifedtime;
		this.description = description;
		this.lastlogin = lastlogin;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.groups = groups;
		this.userHasGroups = userHasGroups;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salutationcodeid")
	public Salutationcode getSalutationcode() {
		return this.salutationcode;
	}

	public void setSalutationcode(Salutationcode salutationcode) {
		this.salutationcode = salutationcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zipcodeid")
	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relationshipcodeid")
	public Relationshipcode getRelationshipcode() {
		return this.relationshipcode;
	}

	public void setRelationshipcode(Relationshipcode relationshipcode) {
		this.relationshipcode = relationshipcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postalcodeid")
	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	@Column(name = "countrycode", nullable = false, length = 3)
	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Column(name = "login", unique = true, nullable = false, length = 128)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "firstname", nullable = false, length = 128)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", nullable = false, length = 128)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "password", nullable = false, length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "gendermale")
	public Boolean getGendermale() {
		return this.gendermale;
	}

	public void setGendermale(Boolean gendermale) {
		this.gendermale = gendermale;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthdate", length = 19)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "profession", length = 128)
	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	@Column(name = "avatarlocation", length = 128)
	public String getAvatarlocation() {
		return this.avatarlocation;
	}

	public void setAvatarlocation(String avatarlocation) {
		this.avatarlocation = avatarlocation;
	}

	@Column(name = "note", length = 128)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "notemodifedtime", length = 19)
	public Date getNotemodifedtime() {
		return this.notemodifedtime;
	}

	public void setNotemodifedtime(Date notemodifedtime) {
		this.notemodifedtime = notemodifedtime;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastlogin", length = 19)
	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserHasSecurityquestioncode> getUserHasSecurityquestioncodes() {
		return this.userHasSecurityquestioncodes;
	}

	public void setUserHasSecurityquestioncodes(
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
	}

}

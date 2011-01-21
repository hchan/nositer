package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Jan 21, 2011 3:35:28 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry

import com.google.gwt.user.client.rpc.IsSerializable;
//import java.util.List;
//import java.util.ArrayList;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable, IsSerializable, DTO {

	private Integer id;
	private Zipcode zipcode;
	private Postalcode postalcode;
	private String countrycode;
	private String login;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private Boolean gender;
	private Date birthdate;
	private String status;
	private String description;
	private String profession;
	private Date lastlogin;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);

	public User() {
	}

	public User(String countrycode, String login, String email,
			String firstname, String lastname, String password) {
		this.countrycode = countrycode;
		this.login = login;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public User(Zipcode zipcode, Postalcode postalcode, String countrycode,
			String login, String email, String firstname, String lastname,
			String password, Boolean gender, Date birthdate, String status,
			String description, String profession, Date lastlogin,
			Date createdtime, Date modifiedtime, Set<UserHasGroup> userHasGroups) {
		this.zipcode = zipcode;
		this.postalcode = postalcode;
		this.countrycode = countrycode;
		this.login = login;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.birthdate = birthdate;
		this.status = status;
		this.description = description;
		this.profession = profession;
		this.lastlogin = lastlogin;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.userHasGroups = userHasGroups;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Date getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Date getModifiedtime() {
		return this.modifiedtime;
	}

	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

}

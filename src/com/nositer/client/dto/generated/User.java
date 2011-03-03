package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Mar 2, 2011 4:31:18 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry

//import com.extjs.gxt.ui.client.data.BaseModelData;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.extjs.gxt.ui.client.data.BeanModelTag;
//import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable, IsSerializable,
		BeanModelTag, DTO {

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
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;
	private Set<Group> groups = new HashSet<Group>(0);
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);
	private Set<Iwantto> iwanttos = new HashSet<Iwantto>(0);
	private Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes = new HashSet<UserHasSecurityquestioncode>(
			0);
	private Set<Blog> blogs = new HashSet<Blog>(0);
	public static final String TABLENAME = "nositer.user";

	public String getTablename() {
		return TABLENAME;
	}

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
			Date lastlogin, Boolean disable, Date createdtime,
			Date modifiedtime, Set<Group> groups,
			Set<UserHasGroup> userHasGroups, Set<Iwantto> iwanttos,
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes,
			Set<Blog> blogs) {
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
		this.disable = disable;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.groups = groups;
		this.userHasGroups = userHasGroups;
		this.iwanttos = iwanttos;
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
		this.blogs = blogs;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Salutationcode getSalutationcode() {
		return this.salutationcode;
	}

	public void setSalutationcode(Salutationcode salutationcode) {
		this.salutationcode = salutationcode;
	}

	private Integer salutationcodeid;

	public Integer getSalutationcodeid() {
		return this.salutationcodeid;
	}

	public void setSalutationcodeid(Integer salutationcodeid) {
		this.salutationcodeid = salutationcodeid;
	}

	public Zipcode getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	private Integer zipcodeid;

	public Integer getZipcodeid() {
		return this.zipcodeid;
	}

	public void setZipcodeid(Integer zipcodeid) {
		this.zipcodeid = zipcodeid;
	}

	public Relationshipcode getRelationshipcode() {
		return this.relationshipcode;
	}

	public void setRelationshipcode(Relationshipcode relationshipcode) {
		this.relationshipcode = relationshipcode;
	}

	private Integer relationshipcodeid;

	public Integer getRelationshipcodeid() {
		return this.relationshipcodeid;
	}

	public void setRelationshipcodeid(Integer relationshipcodeid) {
		this.relationshipcodeid = relationshipcodeid;
	}

	public Postalcode getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(Postalcode postalcode) {
		this.postalcode = postalcode;
	}

	private Integer postalcodeid;

	public Integer getPostalcodeid() {
		return this.postalcodeid;
	}

	public void setPostalcodeid(Integer postalcodeid) {
		this.postalcodeid = postalcodeid;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGendermale() {
		return this.gendermale;
	}

	public void setGendermale(Boolean gendermale) {
		this.gendermale = gendermale;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getProfession() {
		return this.profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAvatarlocation() {
		return this.avatarlocation;
	}

	public void setAvatarlocation(String avatarlocation) {
		this.avatarlocation = avatarlocation;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getNotemodifedtime() {
		return this.notemodifedtime;
	}

	public void setNotemodifedtime(Date notemodifedtime) {
		this.notemodifedtime = notemodifedtime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Boolean getDisable() {
		return this.disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
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

	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

	public Set<Iwantto> getIwanttos() {
		return this.iwanttos;
	}

	public void setIwanttos(Set<Iwantto> iwanttos) {
		this.iwanttos = iwanttos;
	}

	public Set<UserHasSecurityquestioncode> getUserHasSecurityquestioncodes() {
		return this.userHasSecurityquestioncodes;
	}

	public void setUserHasSecurityquestioncodes(
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
	}

	public Set<Blog> getBlogs() {
		return this.blogs;
	}

	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	public enum ColumnType {
		id, salutationcodeid, zipcodeid, relationshipcodeid, postalcodeid, countrycode, login, firstname, lastname, password, email, gendermale, birthdate, profession, avatarlocation, note, notemodifedtime, description, lastlogin, disable, createdtime, modifiedtime,
	}
}

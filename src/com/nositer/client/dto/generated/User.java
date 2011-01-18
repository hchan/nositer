package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Jan 17, 2011 4:14:32 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry

import java.util.List;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable, DTO {

	private Integer id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private Set<UserHasGroup> userHasGroups = new HashSet<UserHasGroup>(0);

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, String firstname, String lastname,
			String password, Set<UserHasGroup> userHasGroups) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.userHasGroups = userHasGroups;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<UserHasGroup> getUserHasGroups() {
		return this.userHasGroups;
	}

	public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
		this.userHasGroups = userHasGroups;
	}

}

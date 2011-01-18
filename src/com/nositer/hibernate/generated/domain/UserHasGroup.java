package com.nositer.hibernate.generated.domain;

import com.nositer.hibernate.*;

// Generated Jan 18, 2011 2:25:17 PM by Hibernate Tools 3.2.4.GA
// Enhanced by Henry
//import java.util.List;
//import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
/**
 * UserHasGroup generated by hbm2java
 */
@Entity
@Table(name = "user_has_group", catalog = "nositer")
public class UserHasGroup implements java.io.Serializable, Domain {

	private Integer id;
	private Group group;
	private User user;

	public UserHasGroup() {
	}

	public UserHasGroup(Group group, User user) {
		this.group = group;
		this.user = user;
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
	@JoinColumn(name = "groupid")
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

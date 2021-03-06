package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Sep 27, 2011 2:19:03 PM by Hibernate Tools 3.2.4.GA
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
 * GroupmessagePlusView generated by hbm2java
 */
public class GroupmessagePlusView implements java.io.Serializable,
		IsSerializable, Cloneable, BeanModelTag, DTO, Comparable {

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
	public static final String TABLENAME = "nositer.groupmessage_plus_view";

	public String getTablename() {
		return TABLENAME;
	}

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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getGrouptopicid() {
		return this.grouptopicid;
	}

	public void setGrouptopicid(Integer grouptopicid) {
		this.grouptopicid = grouptopicid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public GroupmessagePlusView clone() {

		GroupmessagePlusView retval = new GroupmessagePlusView();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (userid != null) {
			retval.setUserid(new Integer(userid));
		}
		if (grouptopicid != null) {
			retval.setGrouptopicid(new Integer(grouptopicid));
		}
		if (description != null) {
			retval.setDescription(new String(description));
		}
		if (createdtime != null) {
			retval.setCreatedtime((Date) createdtime.clone());
		}
		if (modifiedtime != null) {
			retval.setModifiedtime((Date) modifiedtime.clone());
		}
		if (name != null) {
			retval.setName(new String(name));
		}
		if (firstname != null) {
			retval.setFirstname(new String(firstname));
		}
		if (lastname != null) {
			retval.setLastname(new String(lastname));
		}
		if (login != null) {
			retval.setLogin(new String(login));
		}

		return retval;
	}

	public enum Column {
		id, userid, grouptopicid, description, createdtime, modifiedtime, name, firstname, lastname, login,
	}

	@Override
	public int compareTo(Object o) {
		DTO otherDTO = (DTO) o;
		return getId().compareTo(otherDTO.getId());
	}
}

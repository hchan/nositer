package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated May 25, 2011 4:44:43 PM by Hibernate Tools 3.2.4.GA
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
 * Iwantto generated by hbm2java
 */
public class Iwantto implements java.io.Serializable, IsSerializable,
		Cloneable, BeanModelTag, DTO {

	private Integer id;
	private Blog blog;
	private User user;
	private String description;
	private Boolean disable;
	private Date createdtime;
	private Date modifiedtime;
	public static final String TABLENAME = "nositer.iwantto";

	public String getTablename() {
		return TABLENAME;
	}

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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	private Integer blogid;

	public Integer getBlogid() {
		return this.blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Integer userid;

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Iwantto clone() {

		Iwantto retval = new Iwantto();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (description != null) {
			retval.setDescription(new String(description));
		}
		if (disable != null) {
			retval.setDisable(new Boolean(disable));
		}
		if (createdtime != null) {
			retval.setCreatedtime((Date) createdtime.clone());
		}
		if (modifiedtime != null) {
			retval.setModifiedtime((Date) modifiedtime.clone());
		}

		return retval;
	}

	public enum Column {
		id, blogid, userid, description, disable, createdtime, modifiedtime,
	}
}

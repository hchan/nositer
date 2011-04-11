package com.nositer.client.dto.generated;

import com.nositer.client.dto.*;

// Generated Apr 11, 2011 1:28:00 PM by Hibernate Tools 3.2.4.GA
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
 * Securityquestioncode generated by hbm2java
 */
public class Securityquestioncode implements java.io.Serializable,
		IsSerializable, Cloneable, BeanModelTag, DTO, Lookupcode {

	private Integer id;
	private String code;
	private String description;
	private Date createdtime;
	private Date modifiedtime;
	private Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes = new HashSet<UserHasSecurityquestioncode>(
			0);
	public static final String TABLENAME = "nositer.securityquestioncode";

	public String getTablename() {
		return TABLENAME;
	}

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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Set<UserHasSecurityquestioncode> getUserHasSecurityquestioncodes() {
		return this.userHasSecurityquestioncodes;
	}

	public void setUserHasSecurityquestioncodes(
			Set<UserHasSecurityquestioncode> userHasSecurityquestioncodes) {
		this.userHasSecurityquestioncodes = userHasSecurityquestioncodes;
	}

	public Securityquestioncode clone() {

		Securityquestioncode retval = new Securityquestioncode();
		if (id != null) {
			retval.setId(new Integer(id));
		}
		if (code != null) {
			retval.setCode(new String(code));
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

		return retval;
	}

	public enum Column {
		id, code, description, createdtime, modifiedtime,
	}
}

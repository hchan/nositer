package com.nositer.client.dto;

public interface Lookupcode extends DTO {
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	
	public String getCode();

	public void setCode(String code);

	public String getDescription();

	public void setDescription(String description);
}

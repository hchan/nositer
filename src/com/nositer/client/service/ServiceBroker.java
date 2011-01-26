package com.nositer.client.service;

import com.google.gwt.core.client.GWT;

public class ServiceBroker {
	public static final ProfileServiceAsync profileService = GWT.create(ProfileService.class);
	public static final RegisterServiceAsync registerService = GWT.create(RegisterService.class);
	
	public static final String JSONSERVICEDIR = "/jsonservice";
	public static final String POSTALCODEJSONSERVICE = JSONSERVICEDIR + "/postalcode.jsp";
}

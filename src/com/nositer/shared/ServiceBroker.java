package com.nositer.shared;

import com.google.gwt.core.client.GWT;
import com.nositer.client.service.PostalcodeService;
import com.nositer.client.service.PostalcodeServiceAsync;
import com.nositer.client.service.ProfileService;
import com.nositer.client.service.ProfileServiceAsync;
import com.nositer.client.service.RegisterService;
import com.nositer.client.service.RegisterServiceAsync;

public class ServiceBroker {
	public static final ProfileServiceAsync profileService = GWT.create(ProfileService.class);
	public static final RegisterServiceAsync registerService = GWT.create(RegisterService.class);
	public static final PostalcodeServiceAsync postalcodeService = GWT.create(PostalcodeService.class);
	
	
	
	
	// Not used
	public static final String JSONSERVICEDIR = "/jsonservice";
	public static final String POSTALCODEJSONSERVICE = JSONSERVICEDIR + "/postalcode.jsp";
}

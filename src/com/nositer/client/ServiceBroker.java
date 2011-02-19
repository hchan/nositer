package com.nositer.client;

import com.google.gwt.core.client.GWT;
import com.nositer.client.service.FileService;
import com.nositer.client.service.FileServiceAsync;
import com.nositer.client.service.GroupService;
import com.nositer.client.service.GroupServiceAsync;
import com.nositer.client.service.LookupcodeService;
import com.nositer.client.service.LookupcodeServiceAsync;
import com.nositer.client.service.NoopService;
import com.nositer.client.service.NoopServiceAsync;
import com.nositer.client.service.ProfileService;
import com.nositer.client.service.ProfileServiceAsync;
import com.nositer.client.service.RegisterService;
import com.nositer.client.service.RegisterServiceAsync;
import com.nositer.client.service.SecurityService;
import com.nositer.client.service.SecurityServiceAsync;

public class ServiceBroker {
	public static final ProfileServiceAsync profileService = GWT.create(ProfileService.class);
	public static final RegisterServiceAsync registerService = GWT.create(RegisterService.class);
	public static final NoopServiceAsync noopService = GWT.create(NoopService.class);
	public static final SecurityServiceAsync securityService = GWT.create(SecurityService.class);
	public static final FileServiceAsync fileService = GWT.create(FileService.class);
	public static final LookupcodeServiceAsync lookupService = GWT.create(LookupcodeService.class);
	public static final GroupServiceAsync groupService = GWT.create(GroupService.class);
}

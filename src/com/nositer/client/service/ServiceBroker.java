package com.nositer.client.service;

import com.google.gwt.core.client.GWT;

public class ServiceBroker {
	public static final ProfileServiceAsync profileService = GWT.create(ProfileService.class);
}

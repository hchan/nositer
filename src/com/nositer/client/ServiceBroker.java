package com.nositer.client;

import com.google.gwt.core.client.GWT;

public class ServiceBroker {
	public static final ProfileServiceAsync profileService = GWT.create(ProfileService.class);
}

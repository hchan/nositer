package com.nositer.server.service;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.ProfileService;
import com.nositer.webapp.Application;
import com.nositer.webapp.AuthorizationFilter;

@SuppressWarnings("serial")
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

	@Override
	public User getCurrentUser() throws RuntimeException {
		User retval = null;
		retval = (User) Application.getRequest().getSession().getAttribute(AuthorizationFilter.USER_SESSION_KEY);
		return retval;
	}

}
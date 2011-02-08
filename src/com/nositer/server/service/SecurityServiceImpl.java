package com.nositer.server.service;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.service.SecurityService;
import com.nositer.shared.GWTException;
import com.nositer.webapp.Application;

@SuppressWarnings("serial")
public class SecurityServiceImpl extends RemoteServiceServlet implements SecurityService {

	@Override
	public String getSessionId() throws GWTException {		
		return Application.getRequest().getSession().getId();
	}

}

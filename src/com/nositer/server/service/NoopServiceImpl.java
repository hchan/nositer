package com.nositer.server.service;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.service.NoopService;

@SuppressWarnings("serial")
public class NoopServiceImpl extends RemoteServiceServlet implements NoopService {

	@Override
	public void noop() {
		
	}

	
}

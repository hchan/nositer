package com.nositer.server.service;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.RegisterService;

@SuppressWarnings("serial")
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

	@Override
	public boolean register(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	

}

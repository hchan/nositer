package com.nositer.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.ProfileService;
import com.nositer.client.dto.generated.User;
import com.nositer.webapp.AuthorizationFilter;

@SuppressWarnings("serial")
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

	@Override
	public User getCurrentUser() throws RuntimeException {
		User retval = null;
		retval = (User) this.perThreadRequest.get().getSession().getAttribute(AuthorizationFilter.USER_SESSION_KEY);
		return retval;
	}

}

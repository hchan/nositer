package com.nositer.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.User;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface ProfileService extends RemoteService {
	User getCurrentUser() throws GWTException;
	User getCurrentUserForEditBasicProfile() throws GWTException;
	void updateCurrentUserForEditBasicProfile(User user) throws GWTException;
	void updatePasswordOfCurrentUser(String oldPassword, String newPassword) throws GWTException;
	void logout() throws GWTException;
}

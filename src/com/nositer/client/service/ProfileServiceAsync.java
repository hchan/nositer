package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.User;

public interface ProfileServiceAsync {

	void getCurrentUser(AsyncCallback<User> callback);

	void logout(AsyncCallback<Void> callback);


	void updateCurrentUserForEditBasicProfile(User user,
			AsyncCallback<Void> callback);

	void getCurrentUserForEditBasicProfile(AsyncCallback<User> callback);

	void updatePasswordOfCurrentUser(String oldPassword, String newPassword,
			AsyncCallback<Void> callback);
}

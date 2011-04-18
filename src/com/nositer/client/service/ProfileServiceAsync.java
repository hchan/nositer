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

	void updateAboutMeOfCurrentUser(String note, String description,
			AsyncCallback<Void> callback);

	void updateAvatarOfCurrentUser(String avatarlocation,
			AsyncCallback<Void> callback);
	
	void getUser(Integer id, AsyncCallback<User> callback);
}

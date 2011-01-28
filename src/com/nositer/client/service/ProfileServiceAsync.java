package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.User;

public interface ProfileServiceAsync {

	void getCurrentUser(AsyncCallback<User> callback);

	void logout(AsyncCallback<Void> callback);

}

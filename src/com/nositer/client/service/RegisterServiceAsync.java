package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.User;

public interface RegisterServiceAsync {

	void register(User user, AsyncCallback<Boolean> callback);

}

package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecurityServiceAsync {

	void getSessionId(AsyncCallback<String> callback);

}

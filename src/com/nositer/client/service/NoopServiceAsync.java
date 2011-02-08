package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NoopServiceAsync {

	void noop(int sleep, AsyncCallback<Void> callback);


}

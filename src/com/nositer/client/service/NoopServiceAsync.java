package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NoopServiceAsync {

	void noop(AsyncCallback<Void> callback);

}

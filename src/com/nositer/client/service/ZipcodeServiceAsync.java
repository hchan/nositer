package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Zipcode;

public interface ZipcodeServiceAsync {

	void getZipcodes(int offset, int limit, String query,
			AsyncCallback<ArrayList<Zipcode>> callback);

}

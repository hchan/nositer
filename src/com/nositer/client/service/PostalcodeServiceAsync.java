package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Postalcode;

public interface PostalcodeServiceAsync {

	void getPostalcodes(int offset, int limit, String query,
			AsyncCallback<ArrayList<Postalcode>> callback);

}

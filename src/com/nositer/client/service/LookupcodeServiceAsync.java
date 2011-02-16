package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.Lookupcode;

public interface LookupcodeServiceAsync {

	void getCodes(String clazzName, int offset, int limit, String query,
			AsyncCallback<ArrayList<? extends Lookupcode>> callback);



}

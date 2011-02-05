package com.nositer.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class GWTException extends RuntimeException implements IsSerializable{

	public GWTException() {
		super();
	}
	
	public GWTException(String str) {
		super(str);
	}
	
	public GWTException(Exception e) {
		super(e);
	}

}

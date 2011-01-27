package com.nositer.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GWTException extends RuntimeException implements IsSerializable{

	public GWTException() {
		super();
	}
	
	public GWTException(Exception e) {
		super(e);
	}

}

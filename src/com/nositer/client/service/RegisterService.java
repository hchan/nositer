package com.nositer.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.User;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {
	boolean register(User user) throws RuntimeException;
}

package com.nositer.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface GroupService extends RemoteService {
	Group createGroup(Group group) throws GWTException;
	
}
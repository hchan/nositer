package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface GroupService extends RemoteService {
	Group createGroup(Group group) throws GWTException;
	ArrayList<UserHasGroupView> getMyGroups() throws GWTException;
	UserHasGroupView getGroupByTagname(String tagname) throws GWTException;
	void disableGroup(UserHasGroupView userHasGroupView) throws GWTException;
	ArrayList<UserHasGroupView> search(String name, Float latitude, Float longitude, Number radius) throws GWTException;
	Group updateGroup(Group group) throws GWTException;
	void createOrUpdateSubscription(UserHasGroupView userHasGroupView) throws GWTException;
}

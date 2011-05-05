package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface GroupService extends RemoteService {
	Group createGroup(Group group) throws GWTException;
	ArrayList<GroupPlusView> getMyGroups() throws GWTException;
	GroupPlusView getGroupByTagname(String tagname) throws GWTException;
	void disableGroup(GroupPlusView groupPlusView) throws GWTException;
	ArrayList<GroupPlusView> search(String name, Float latitude, Float longitude, Number radius) throws GWTException;
	Group updateGroup(Group group) throws GWTException;
	void createOrUpdateSubscription(GroupPlusView groupPlusView) throws GWTException;
	ArrayList<GroupSubscriptionView> getSubscriptions(GroupPlusView groupPlusView) throws GWTException;
	ArrayList<GroupSubscriptionView> findSubscriptions(GroupPlusView groupPlusView, String lastname) throws GWTException;
}

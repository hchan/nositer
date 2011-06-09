package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.GroupmessagePlusView;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.dto.generated.UserHasGroup;
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
	void createOrUpdateSubscription(UserHasGroup userHasGroup) throws GWTException;
	ArrayList<GroupSubscriptionView> getSubscriptions(GroupPlusView groupPlusView) throws GWTException;
	ArrayList<GroupSubscriptionView> findSubscriptions(GroupPlusView groupPlusView, String lastname) throws GWTException;
	Grouptopic createGrouptopic(GroupPlusView groupPlusView, Grouptopic grouptopic) throws GWTException;
	ArrayList<GroupmessagePlusView> getGroupmessages(GroupPlusView groupPlusView) throws GWTException;
	Groupmessage getGroupmessage(Integer groupmessageid) throws GWTException;
	Groupmessage addGroupmessage(GroupPlusView groupPlusView, Groupmessage groupmessage) throws GWTException;
	Groupmessage getGroupmessage(Integer grouptopicid,
			Integer indexOfGroupmessage) throws GWTException;
}

package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.GroupmessagePlusView;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.dto.generated.UserHasGroup;

public interface GroupServiceAsync {

	void createGroup(Group group, AsyncCallback<Group> callback);

	void disableGroup(GroupPlusView groupPlusView,
			AsyncCallback<Void> callback);

	void getGroupByTagname(String tagname, AsyncCallback<GroupPlusView> callback);

	void getMyGroups(AsyncCallback<ArrayList<GroupPlusView>> callback);

	// TODO add pagination
	void search(String name, Float latitude, Float longitude, Number radius,
			AsyncCallback<ArrayList<GroupPlusView>> callback);

	void updateGroup(Group group, AsyncCallback<Group> callback);

	void createOrUpdateSubscription(UserHasGroup userHasGroup,
			AsyncCallback<Void> callback);

	// TODO add pagination
	// TODO add user_has_group.invisible
	void getSubscriptions(GroupPlusView groupPlusView,
			AsyncCallback<ArrayList<GroupSubscriptionView>> callback);
	
	// TODO add pagination
	// TODO add user_has_group.invisible
	void findSubscriptions(GroupPlusView groupPlusView,
			String lastname,
			AsyncCallback<ArrayList<GroupSubscriptionView>> callback);
	
	void createGrouptopic(GroupPlusView groupPlusView, Grouptopic grouptopic, AsyncCallback<Grouptopic> callback);

	void getGroupmessages(GroupPlusView groupPlusView, AsyncCallback<ArrayList<GroupmessagePlusView>> callback);

	void getGroupmessage(Integer groupmessageid,
			AsyncCallback<Groupmessage> callback);

	void getGroupmessage(Integer grouptopicid, Integer indexOfGroupmessage,
			AsyncCallback<Groupmessage> callback);
	
	void addGroupmessage(GroupPlusView groupPlusView,
			Groupmessage groupmessage, AsyncCallback<Groupmessage> callback);

	void editGroupmessage(GroupPlusView groupPlusView,
			Groupmessage modifiedGroupmessage,
			AsyncCallback<Groupmessage> callback);
}

package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.UserHasGroup;

public interface GroupServiceAsync {

	void createGroup(Group group, AsyncCallback<Group> callback);

	void disableGroup(GroupPlusView groupPlusView,
			AsyncCallback<Void> callback);

	void getGroupByTagname(String tagname, AsyncCallback<GroupPlusView> callback);

	void getMyGroups(AsyncCallback<ArrayList<GroupPlusView>> callback);

	void search(String name, Float latitude, Float longitude, Number radius,
			AsyncCallback<ArrayList<GroupPlusView>> callback);

	void updateGroup(Group group, AsyncCallback<Group> callback);

	void createOrUpdateSubscription(GroupPlusView groupPlusView,
			AsyncCallback<Void> callback);

	void getSubscriptions(GroupPlusView groupPlusView,
			AsyncCallback<ArrayList<GroupSubscriptionView>> callback);

}

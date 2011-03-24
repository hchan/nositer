package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.UserHasGroupView;

public interface GroupServiceAsync {

	void createGroup(Group group, AsyncCallback<Group> callback);

	void deleteGroup(UserHasGroupView userHasGroupView,
			AsyncCallback<Void> callback);

	void getGroupByTagname(String tagname, AsyncCallback<UserHasGroupView> callback);

	void getMyGroups(AsyncCallback<ArrayList<UserHasGroupView>> callback);

	void search(String name, Float latitude, Float longitude, Number radius,
			AsyncCallback<ArrayList<UserHasGroupView>> callback);

	void updateGroup(Group group, AsyncCallback<Group> callback);

	

}

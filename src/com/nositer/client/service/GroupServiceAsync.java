package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;

public interface GroupServiceAsync {

	void createGroup(Group group, AsyncCallback<Group> callback);

	void getMyGroups(AsyncCallback<ArrayList<Group>> callback);

}

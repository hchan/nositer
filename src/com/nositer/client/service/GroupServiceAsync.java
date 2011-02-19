package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Group;

public interface GroupServiceAsync {

	void createGroup(Group group, AsyncCallback<Group> callback);

}

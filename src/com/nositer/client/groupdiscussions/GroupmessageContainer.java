package com.nositer.client.groupdiscussions;

import com.nositer.client.dto.generated.Groupmessage;

public interface GroupmessageContainer {
	public Groupmessage getGroupmessage();
	
	public void populateInsideMainPanel();
}

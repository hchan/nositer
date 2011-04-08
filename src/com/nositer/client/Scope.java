package com.nositer.client;

import com.nositer.client.dto.generated.GroupPlusView;

public class Scope {
	private Type type;
	private GroupPlusView groupPlusView;
	
	
	
	public GroupPlusView getGroupPlusView() {
		return groupPlusView;
	}

	public void setGroupPlusView(GroupPlusView groupPlusView) {
		this.groupPlusView = groupPlusView;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public enum Type {
		user, group
	}

	public Scope(Type type) {
		this.type = type;
	}

	
}

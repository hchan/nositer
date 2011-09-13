package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.Groupmessage;

public interface BlogServiceAsync {

	void createBlog(Blog blog, AsyncCallback<Blog> callback);
	
	void getBlog(Integer blogid, AsyncCallback<Blog> callback);

}

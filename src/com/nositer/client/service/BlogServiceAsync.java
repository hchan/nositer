package com.nositer.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.Group;

public interface BlogServiceAsync {

	void createBlog(Blog blog, AsyncCallback<Blog> callback);

}

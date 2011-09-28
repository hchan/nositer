package com.nositer.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.shared.GWTException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ServiceResolver.gwtrpc")
public interface BlogService extends RemoteService {
	Blog createBlog(Blog blog) throws GWTException;

	Blog getBlog(Integer blogid) throws GWTException;
	
	ArrayList<Blog> getMyBlogs() throws GWTException;

	Blog updateBlog(Blog blog) throws GWTException;

	void disableBlog(Blog blog) throws GWTException;

}

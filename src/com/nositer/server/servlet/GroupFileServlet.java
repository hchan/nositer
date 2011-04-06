package com.nositer.server.servlet;

import com.nositer.shared.Global;

@SuppressWarnings({"serial"})
public class GroupFileServlet extends AbstractFileServlet {
	/**
	 * an example URL request would look like: where 1 is the userid
	 * http://localhost:8888/groupfile/1/public/adapter.jpg
	 */


	@Override
	public String getRootDir() {		
		return Global.GROUPROOTDIR;
	}

	@Override
	public String getPublicDir() {
		return Global.GROUPPUBLICDIR;
	}
	

}
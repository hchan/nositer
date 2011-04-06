package com.nositer.server.servlet;

import com.nositer.shared.Global;

@SuppressWarnings({"serial"})
public class UserFileServlet extends AbstractFileServlet {
	/**
	 * an example URL request would look like: where 1 is the userid
	 * http://localhost:8888/userfile/1/public/adapter.jpg
	 */
	
	@Override
	public String getRootDir() {		
		return Global.USERROOTDIR;
	}

	@Override
	public String getPublicDir() {		
		return Global.USERPUBLICDIR;
	}
	

}
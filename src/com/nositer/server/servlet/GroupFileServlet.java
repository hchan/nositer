package com.nositer.server.servlet;

import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.User;
import com.nositer.server.service.GroupServiceImpl;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;

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

	@Override
	public String getPrivateDir() {
		return Global.GROUPPRIVATEDIR;
	}

	@Override
	public boolean isPrivateFileIHaveAccessTo(String userOrGroupid,
			String accessPath) {
		boolean retval = false;
		int groupid = Integer.parseInt(userOrGroupid);
		User currentUser = Application.getCurrentUser();
		if (currentUser != null) {
			int userid = currentUser.getId();
			GroupServiceImpl groupServiceImpl = new GroupServiceImpl();		
			GroupSubscriptionView groupSubscriptionView = groupServiceImpl.getGroupSubscriptionByGroupAndUser(groupid, userid);
			if (groupSubscriptionView != null) {
				retval = true;
			}
		}
		return retval;
	}

}
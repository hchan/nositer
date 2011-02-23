package com.nositer.client.util;

import com.nositer.client.dto.generated.User;
import com.nositer.client.top.TopPanel;
import com.nositer.shared.Global;


public class ImageHelper {
	
	public static String getUserImagePathURL (String relativePath) {
		String retval = "";
		//retval = Global.USER_URL_PREFIX + "/" + TopPanel.getInstance().getUser().getId() + "/image" + relativePath;
		retval = getUserImagePathURL(relativePath, TopPanel.getInstance().getUser());
		return retval;
	}
	
	public static String getUserImagePathURL (String relativePath, User user) {
		String retval = "";
		retval = Global.USER_URL_PREFIX + "/" + user.getId() + "/image" + relativePath;
		return retval;
	}
}

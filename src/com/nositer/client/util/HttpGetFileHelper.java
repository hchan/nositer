package com.nositer.client.util;

import java.text.MessageFormat;

import com.nositer.client.Nositer;
import com.nositer.shared.Global;


public class HttpGetFileHelper {
	
	public static String getUserPathURL (String relativePath) {
		String retval = "";
		//retval = Global.USER_URL_PREFIX + "/" + TopPanel.getInstance().getUser().getId() + "/image" + relativePath;
		retval = getUserPathURL(
				relativePath, 
				Nositer.getInstance().
				getUser().
				getId());
		return retval;
	}
	
	public static String getUserPathURL (String relativePath, Integer userid) {
		String retval = null;
		if (relativePath != null && userid != null) {									
			retval = Global.USER_URL_PREFIX + "/" + userid + relativePath;
		}
		return retval;
	}
	
	public static String getGroupPathURL(String relativePath, Integer groupid) {
		String retval = null;
		if (relativePath != null && groupid != null) {									
			retval = Global.GROUP_URL_PREFIX + "/" + groupid + relativePath;
		}
		return retval;
	}
}

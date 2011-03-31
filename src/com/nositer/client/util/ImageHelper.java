package com.nositer.client.util;

import java.text.MessageFormat;

import com.nositer.client.Nositer;
import com.nositer.shared.Global;


public class ImageHelper {
	
	public static String getUserImagePathURL (String relativePath) {
		String retval = "";
		//retval = Global.USER_URL_PREFIX + "/" + TopPanel.getInstance().getUser().getId() + "/image" + relativePath;
		retval = getUserImagePathURL(
				relativePath, 
				Nositer.getInstance().
				getUser().
				getId());
		return retval;
	}
	
	public static String getUserImagePathURL (String relativePath, Integer userid) {
		String retval = null;
		if (relativePath != null && userid != null) {									
			retval = Global.USER_URL_PREFIX + "/" + userid + relativePath;
		}
		return retval;
	}
}

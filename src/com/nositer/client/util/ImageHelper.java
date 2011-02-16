package com.nositer.client.util;

import com.nositer.client.top.TopPanel;
import com.nositer.shared.Global;


public class ImageHelper {
	
	public static String getUserImagePathURL (String fileModelPath) {
		String retval = "";
		retval = Global.USER_URL_PREFIX + "/" + TopPanel.getInstance().getUser().getId() + "/image" + fileModelPath;
		return retval;
	}
}

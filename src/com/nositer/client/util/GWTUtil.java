package com.nositer.client.util;

import com.google.gwt.core.client.GWT;

public class GWTUtil {
	public static void log(String str) {
		GWT.log(str);
		logFF(str);
	}

	public static void log(String str, Throwable throwable) {
		GWT.log(str, throwable);
		logFF(str + ":" + throwable);
	}

	public native static void logFF(String str) /*-{
		if (typeof($wnd['console']) != "undefined") {
			console.log(str);
		} 		
	}-*/;

}

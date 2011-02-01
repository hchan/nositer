package com.nositer.client.util;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;

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

	public static String getFormattedDate(Date date) {
		String retval = null;
		if (date != null) {
			retval = DateTimeFormat.getFormat("MMM dd yyyy").format(date);
		}
		return retval;
	}
}

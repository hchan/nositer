package com.nositer.client.util;

import java.util.ArrayList;
import java.util.Date;

import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
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

	public native static void hide(String elementId) /*-{
		$doc.getElementById(elementId).style.display = 'none';
	}-*/;

	public static String getRequiredFieldStyle() {
		return "color: red; font-weight: bold";
	}

	public static void addRequiredErrorIfNecessary(TextField<String> textField,
			ArrayList<String> retval) {
		if (textField.getValue() == null) {
			retval.add(textField.getFieldLabel().replace("* ", "") + " is required");
		}
	}
	public static void addRequiredErrorIfNecessary(HtmlEditor htmlEditor,
			ArrayList<String> retval) {
		if (htmlEditor.getValue()  == null) {
			retval.add(htmlEditor.getFieldLabel().replace("* ", "") + " is required");
		}
	}

	public native static void loadjscssfile(String filename, String filetype) /*-{
		 if (filetype=="js"){
		  var fileref=$doc.createElement('script')
		  fileref.setAttribute("type","text/javascript")
		  fileref.setAttribute("src", filename)
		 }
		 else if (filetype=="css"){
		  var fileref=$doc.createElement("link")
		  fileref.setAttribute("rel", "stylesheet")
		  fileref.setAttribute("type", "text/css")
		  fileref.setAttribute("href", filename)
		 }
		 if (typeof fileref!="undefined") {
		  $doc.getElementsByTagName("head")[0].appendChild(fileref)
		}
	}-*/;
	
	public native static int getFlashVersion()  /*-{
		return $wnd.getFlashVersion();
	}-*/;
}

package com.nositer.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileNameVerifier {

	public static boolean isValidFileName(String fileName) {
		boolean retval = false;
		String validCharsPattern = "^([A-Za-z0-9_\\.])+$";
		retval = fileName.matches(validCharsPattern);
		if (retval) {
			retval = !fileName.matches(".*\\.\\..*");
		}
		return retval;
	}
	
	
	public static ArrayList<String> getImageExtensions() {
		ArrayList<String> retval = new ArrayList<String>();
		retval.add("png");
		retval.add("jpg");
		retval.add("gif");
		return retval;
	}

	public static ArrayList<String> getUploadableExtensions() {
		ArrayList<String> retval = new ArrayList<String>();
		retval.addAll(getImageExtensions());
		retval.add("txt");
		retval.add("doc");		
		retval.add("xls");
		return retval;
	}
	
}

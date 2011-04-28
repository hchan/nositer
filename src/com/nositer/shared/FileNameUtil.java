package com.nositer.shared;

import java.util.ArrayList;

public class FileNameUtil {

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
	
	public static ArrayList<String> getTextExtensions() {
		ArrayList<String> retval = new ArrayList<String>();
		retval.add("txt");
		retval.add("xml");
		return retval;
	}

	public static ArrayList<String> getUploadableExtensions() {
		ArrayList<String> retval = new ArrayList<String>();
		retval.addAll(getImageExtensions());
		retval.addAll(getTextExtensions());
		retval.add("doc");		
		retval.add("xls");
		return retval;
	}
	
	private static boolean doesFileNameEndWithExtensions(String fileName, ArrayList<String> extensions) {
		boolean retval = false;
		for (String extension : extensions) {
			if (fileName.toLowerCase().endsWith("." + extension)) {
				retval = true;
				break;
			}
		}
		return retval;
	}
	
	public static boolean isImageFile(String fileName) {
		boolean retval = false;
		retval = doesFileNameEndWithExtensions(fileName, getImageExtensions());
		return retval;
	}
	
	public static boolean isTextFile(String fileName) {
		boolean retval = false;
		retval = doesFileNameEndWithExtensions(fileName, getTextExtensions());
		return retval;
	}
}

package com.nositer.sandbox;


public class IllegalChars {

	
	public static void main(String[] args) {
		
		
	
		System.out.println(isValidFileName("aasdfdsfds"));
	}
	
	public static boolean isValidFileName(String fileName) {
		boolean retval = false;
		String validCharsPattern = "^([A-Za-z0-9_\\.])+$";
		retval = fileName.matches(validCharsPattern);
		if (retval) {
			retval = !fileName.matches(".*\\.\\..*");
		}
		return retval;
	}

}

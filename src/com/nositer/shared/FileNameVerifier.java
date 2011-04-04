package com.nositer.shared;

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

}

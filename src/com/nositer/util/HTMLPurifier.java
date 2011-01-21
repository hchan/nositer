package com.nositer.util;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

public class HTMLPurifier {
	public static String POLICY_FILE_LOCATION = "antisamy-myspace-1.4.3.xml";
	public static String getCleanHTML(String dirty) throws Exception {
		String retval = null;
		Policy policy = Policy.getInstance(ClassLoader.getSystemResource(POLICY_FILE_LOCATION));
		AntiSamy as = new AntiSamy();
		CleanResults cr = as.scan("<HTML>" + dirty + "</HTML>", policy);
		retval = cr.getCleanHTML();
		return retval;
	}
}

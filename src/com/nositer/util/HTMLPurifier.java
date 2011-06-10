package com.nositer.util;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

import com.nositer.webapp.Application;

public class HTMLPurifier {
	public static String POLICY_FILE_LOCATION = "antisamy-myspace-1.4.3.xml";
	public static String getCleanHTML(String dirty) {
		String retval = null;
		try {
			Policy policy = Policy.getInstance(ClassLoader.getSystemResource(POLICY_FILE_LOCATION));
			AntiSamy as = new AntiSamy();
			CleanResults cr = as.scan("<HTML>" + dirty + "</HTML>", policy);
			retval = cr.getCleanHTML();
		} catch (Exception e) {
			Application.log.error("", e);
		}
		return retval;
	}
}

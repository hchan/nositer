package com.nositer.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nositer.util.NositerConfiguration;
import com.thoughtworks.xstream.XStream;

public class Application {
	public static Logger log = Logger.getLogger("nositer");
	public static NositerConfiguration cfg = new NositerConfiguration();
	
	public static HttpServletRequest getRequest() {
		return AuthorizationFilter.getThreadLocalRequest();
	}
	
	public static HttpServletResponse getResponse() {
		return AuthorizationFilter.getThreadLocalResponse();
	}
	
	public static void debug(Object obj) {
		XStream xstream = new XStream();
		log.debug(xstream.toXML(obj));
	}
	
	public static String dump(Object obj) {
		String retval = null;
		XStream xstream = new XStream();
		retval = xstream.toXML(obj);
		return retval;
	}
}

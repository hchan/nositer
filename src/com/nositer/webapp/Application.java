package com.nositer.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nositer.client.dto.generated.User;
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

	public static User getCurrentUser() {
		return (User) getRequest().getSession().getAttribute(AuthorizationFilter.USER_SESSION_KEY);
	}
	
	public static void setCurrentUser(User user) {
		getRequest().getSession().setAttribute(AuthorizationFilter.USER_SESSION_KEY, user);
	}

	public static String dump(Object obj) {
		String retval = null;
		XStream xstream = new XStream();
		retval = xstream.toXML(obj);
		return retval;
	}
}

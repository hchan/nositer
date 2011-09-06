package com.nositer.webapp;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.nositer.client.dto.generated.User;
import com.nositer.server.service.FileServiceImpl;
import com.nositer.server.util.FileUtil;
import com.nositer.shared.Global;
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
		User retval = null;
		if (Global.DEBUG) {
			retval = new User();
			retval.setId(1);
		} else {
			retval = (User) getRequest().getSession().getAttribute(AuthorizationFilter.USER_SESSION_KEY);
		}
		return retval;
	}

	public static void setCurrentUser(User user) {
		getRequest().getSession().setAttribute(AuthorizationFilter.USER_SESSION_KEY, user);
	}

	public static String getRealPath(String path) {
		String retval = null;
		if (getRequest() != null) {
			retval = getRequest().getSession().getServletContext().getRealPath(path);
		} else {
			String userDir = System.getProperty("user.dir");
			retval = userDir + "/war" + path;
		}
		return retval;
	}



	public static String dump(Object obj) {
		String retval = null;
		XStream xstream = new XStream();
		retval = xstream.toXML(obj);
		return retval;
	}
}

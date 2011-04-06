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
		return (User) getRequest().getSession().getAttribute(AuthorizationFilter.USER_SESSION_KEY);
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

	public static void createBasicFilesStructure(User user) throws IOException {
		FileServiceImpl fileServiceImpl = new FileServiceImpl(user);
		fileServiceImpl.createDirsIfNecessary();
		File defaultAvatar = new File(Application.getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTUSERAVATAR));		
		File publicDir = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()));
		FileUtils.copyFileToDirectory(defaultAvatar, publicDir);
		File publicREADME = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.  Your userid is: " + user.getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of " + Global.USER_URL_PREFIX + "/" + user.getId() +
				"\nFor example, your default avatar is viewable at this location: " +
				Global.USER_URL_PREFIX + "/" + user.getId() + "/" + Global.DEFAULTUSERAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for you to upload files to private (not be viewable to anyone else");
	}

	public static String dump(Object obj) {
		String retval = null;
		XStream xstream = new XStream();
		retval = xstream.toXML(obj);
		return retval;
	}
}

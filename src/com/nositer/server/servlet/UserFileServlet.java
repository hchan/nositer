package com.nositer.server.servlet;

import java.io.File;
import java.io.FileReader;
import java.text.MessageFormat;

import com.nositer.client.dto.generated.User;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;
import com.thoughtworks.xstream.XStream;

@SuppressWarnings({"serial"})
public class UserFileServlet extends AbstractFileServlet {
	/**
	 * an example URL request would look like: where 1 is the userid
	 * http://localhost:8888/userfile/1/public/adapter.jpg
	 */

	@Override
	public String getRootDir() {		
		return Global.USERROOTDIR;
	}

	@Override
	public String getPublicDir() {		
		return Global.USERPUBLICDIR;
	}

	@Override
	public String getPrivateDir() {
		return Global.USERPRIVATEDIR;
	}

	@Override
	public boolean isPrivateFileIHaveAccessTo(String userOrGroupid, 
			String accessPath) {
		boolean retval = false;
		User currentUser = Application.getCurrentUser();
		if (currentUser.getId().equals(Integer.parseInt(userOrGroupid))) {
			retval = true;
		} else {
			File permissionsXML = new File(MessageFormat.format(Global.USERDIRTEMPLATE, userOrGroupid) + "/" + accessPath + "/" + Global.PERMISSIONSXML);
			if (permissionsXML.exists()) {
				try {
					XStream xstream = new XStream();
					FileReader fileReader = new FileReader(permissionsXML);
					// TODO
					Object a = xstream.fromXML(fileReader);
				} catch (Exception e) {
					Application.log.error("", e);
				}
			}
		}
		return retval;
	}


}
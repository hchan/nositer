package com.nositer.server.servlet;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.nositer.client.dto.generated.User;
import com.nositer.server.util.Permissions;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;
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
		if (currentUser != null) {
			if (currentUser.getId().equals(Integer.parseInt(userOrGroupid))) {
				retval = true;
			} else {
				File permissionsXML = new File(MessageFormat.format(Global.USERDIRTEMPLATE, userOrGroupid) + "/" + accessPath + "/" + Global.PERMISSIONSXML);
				if (permissionsXML.exists()) {
					try {
						Permissions permissions = new Permissions();
						Document document = null;
						SAXReader reader = new SAXReader();					 
						document = reader.read(permissionsXML);
						String xPath = "//permissions/login";
						List<Node> nodes = document.selectNodes( xPath );
						for (Node node : nodes) {
							String login = node.getStringValue();
							permissions.getLogin().add(login);
							if (currentUser.getLogin().equals(login)) {
								retval = true;
								break;
							}
						}
					} catch (Exception e) {
						Application.log.error("", e);
					}
				}
			}
		}
		return retval;
	}


}
package com.nositer.server.servlet;

import com.nositer.shared.FileNameUtil;
import com.nositer.shared.Global;

@SuppressWarnings({"serial"})
public class UserFileServlet extends AbstractFileServlet {
	/**
	 * an example URL request would look like: where 1 is the userid
	 * http://localhost:8888/userfile/1/public/adapter.jpg
	 */
	

	@Override
	public boolean isValidRequestedFile(String str) {
		boolean retval = true;
		try {
			String[] dirPaths = str.split("/");
			String userid = dirPaths[1];
			String accessPath = dirPaths[2];
			if (!userid.matches("^\\d+$")) {
				retval = false;
			} else {
				if (!("/" + accessPath).equals(Global.USERPUBLICDIR)) {		
					retval = false;
				} else {
					for (int i = 1; i < dirPaths.length; i++) {
						String dirPath = dirPaths[i];
						if (!FileNameUtil.isValidFileName(dirPath)) {
							retval = false;
							break;
						}
					}			
				}
			}		
		} catch (Exception e) {
			retval = false;
		}
		return retval;
	}

	@Override
	public String getRootDir() {		
		return Global.USERROOTDIR;
	}
	

}
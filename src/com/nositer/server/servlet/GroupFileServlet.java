package com.nositer.server.servlet;

import com.nositer.shared.FileNameUtil;
import com.nositer.shared.Global;

@SuppressWarnings({"serial"})
public class GroupFileServlet extends AbstractFileServlet {


	@Override
	public boolean isValidRequestedFile(String str) {
		boolean retval = true;
		try {
			String[] dirPaths = str.split("/");
			String groupid = dirPaths[1];
			String accessPath = dirPaths[2];
			if (!groupid.matches("^\\d+$")) {
				retval = false;
			} else {
				if (!("/" + accessPath).equals(Global.GROUPPUBLICDIR)) {		
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
		return Global.GROUPROOTDIR;
	}
	

}
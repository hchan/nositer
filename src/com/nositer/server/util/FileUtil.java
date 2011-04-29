package com.nositer.server.util;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import org.apache.commons.io.FileUtils;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.User;
import com.nositer.shared.Global;
import com.nositer.webapp.Application;

public class FileUtil {

	public static void createDirsIfNecessary(User user) throws IOException {	
		File privateDir = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()));
		if (!privateDir.exists()) {
			FileUtils.forceMkdir(privateDir);
		}
		File publicDir = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()));
		if (!publicDir.exists()) {
			FileUtils.forceMkdir(publicDir);
		}
	}

	public static void createDirsIfNecessary(Group group) throws IOException {	
		File privateDir = new File(MessageFormat.format(Global.GROUPPRIVATEDIRTEMPLATE, group.getId()));
		if (!privateDir.exists()) {
			FileUtils.forceMkdir(privateDir);
		}
		File publicDir = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()));
		if (!publicDir.exists()) {
			FileUtils.forceMkdir(publicDir);
		}
	}
	
	public static void createDirsIfNecessary(GroupPlusView groupPlusView) throws IOException {	
		File privateDir = new File(MessageFormat.format(Global.GROUPPRIVATEDIRTEMPLATE, groupPlusView.getId()));
		if (!privateDir.exists()) {
			FileUtils.forceMkdir(privateDir);
		}
		File publicDir = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, groupPlusView.getId()));
		if (!publicDir.exists()) {
			FileUtils.forceMkdir(publicDir);
		}
	}
	

	public static void createBasicFilesStructure(User user) throws IOException {
		FileUtil.createDirsIfNecessary(user);
		File defaultAvatar = new File(Application.getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTUSERAVATAR));		
		File publicDir = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()));
		FileUtils.copyFileToDirectory(defaultAvatar, publicDir);
		File publicREADME = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, user.getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.  Your userid is: " + user.getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of \n" + 
				Global.USER_URL_PREFIX + "/" + user.getId() + Global.USERPUBLICDIR + 
				"\nFor example, your default avatar is viewable at this location: \n" +
				Global.USER_URL_PREFIX + "/" + user.getId() + Global.USERPUBLICDIR + "/" + Global.DEFAULTUSERAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "Any files in this directory are only viewable by yourself.  " +
				"If you wish to share this folder or a subfolder, upload a permissions.xml to that folder.\n" +
				"Your userid is: " + user.getId() + "\n" +
				"The root of this folder is: \n" + 
				Global.USER_URL_PREFIX + "/" + user.getId() + Global.PRIVATEFOLDER
				);
		File permissionsXML = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()) + "/" + Global.PERMISSIONSXML);
		FileUtils.writeStringToFile(permissionsXML, "<permissions>\n" +
				"\t<!--\n" +
				"\tAdd user login names to whom you like to share this folder with\n" +
				"\ti.e.\n" +
				"\t<login>hchan</login>\n" +
				"\t<login>hsimpson</login>\n" +
				"\t<login>mburns</login>\n" +
				"\t-->\n" +
				"</permissions>"
				);
	}
	
	public static void createBasicFilesStructure(Group group) throws IOException {		
		createDirsIfNecessary(group);
		File defaultAvatar = new File(Application.getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTGROUPAVATAR));		
		File publicDir = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()));
		FileUtils.copyFileToDirectory(defaultAvatar, publicDir);
		File publicREADME = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.\n" +
				"Your groupid is: " + group.getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of \n" + 
				Global.GROUP_URL_PREFIX + "/" + group.getId() + Global.GROUPPUBLICDIR + 
				"\nFor example, your default avatar is viewable at this location: \n" +
				Global.GROUP_URL_PREFIX + "/" + group.getId() + Global.GROUPPUBLICDIR + "/" + Global.DEFAULTGROUPAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.GROUPPRIVATEDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for the owner and subscribers of this group.\n" +
				"Your groupid is: " + group.getId() + 
				"\nThe root of this folder is:\n" + 
				Global.GROUP_URL_PREFIX + "/" + group.getId() + Global.GROUPPUBLICDIR
				);
	}
	
}

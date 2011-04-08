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
				"\nAny files in your public directory can be accessed with a relative URL of " + Global.USER_URL_PREFIX + "/" + user.getId() +
				"\nFor example, your default avatar is viewable at this location: " +
				Global.USER_URL_PREFIX + "/" + user.getId() + "/" + Global.DEFAULTUSERAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, user.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for you to upload files to private (not be viewable to anyone else");
	}
	
	public static void createBasicFilesStructure(Group group) throws IOException {		
		createDirsIfNecessary(group);
		File defaultAvatar = new File(Application.getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTGROUPAVATAR));		
		File publicDir = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()));
		FileUtils.copyFileToDirectory(defaultAvatar, publicDir);
		File publicREADME = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.  Your groupid is: " + group.getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of " + Global.GROUP_URL_PREFIX + "/" + group.getId() +
				"\nFor example, your default avatar is viewable at this location: " +
				Global.GROUP_URL_PREFIX + "/" + group.getId() + "/" + Global.DEFAULTGROUPAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.GROUPPRIVATEDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for you to upload files to private (not be viewable to anyone else");
	}
	
}

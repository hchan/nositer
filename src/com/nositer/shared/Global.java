package com.nositer.shared;

public class Global {

	public static final String UPLOADURL = "/upload";
	public static final String CURRENTUSERASJSON = "/currentuserasjson";
	public static final String UPLOADCREDENTIALKEY = "myid";
	
	
	public static final String PUBLICFOLDER = "/public";
	public static final String PRIVATEFOLDER = "/private";
	
	
	public static final String USERROOTDIR = "/mnt/nositer/user";
	public static final String USERDIRTEMPLATE = USERROOTDIR + "/{0}";
	public static final String USERPUBLICDIR = PUBLICFOLDER;
	public static final String USERPRIVATEDIR = PRIVATEFOLDER;
	public static final String USERPUBLICDIRTEMPLATE = USERDIRTEMPLATE + USERPUBLICDIR;
	public static final String USERPRIVATEDIRTEMPLATE = USERDIRTEMPLATE + USERPRIVATEDIR;
	
	public static final String GROUPROOTDIR = "/mnt/nositer/group";
	public static final String GROUPDIRTEMPLATE = GROUPROOTDIR + "/{0}";
	public static final String GROUPPUBLICDIR = PUBLICFOLDER;
	public static final String GROUPPRIVATEDIR = PRIVATEFOLDER;
	public static final String GROUPPUBLICDIRTEMPLATE = GROUPDIRTEMPLATE + GROUPPUBLICDIR;
	public static final String GROUPPRIVATEDIRTEMPLATE = GROUPDIRTEMPLATE + GROUPPRIVATEDIR;
	
	
	public static final String SWFLOADDIRFLASHPLAYER10 = "/public/swfupload/swfupload.swf";
	public static final String SWFLOADDIRFLASHPLAYER9 = "/public/swfupload/swfupload_fp9.swf";
	public static final String IMAGENOTFOUND = "/public/image/imageNotFound.gif";
	public static final String USER_URL_PREFIX = "/userfile";
	public static final String GROUP_URL_PREFIX = "/groupfile";
	public static final String AVATAR_WIDTHHEIGHT = "200"; // in px
	public static final String PUBLICIMAGEDIR = "/public/image";
	public static final String DEFAULTAVATAR = "unknownavatar.jpg";
	public static final String DEFAULTUSERAVATAR = "defaultUserAvatar.png";
	public static final String DEFAULTGROUPAVATAR = "defaultGroupAvatar.jpg";
	

}

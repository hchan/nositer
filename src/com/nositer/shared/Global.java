package com.nositer.shared;

public class Global {

	public static final String UPLOADURL = "/upload";
	public static final String CURRENTUSERASJSON = "/currentuserasjson";
	public static final String UPLOADCREDENTIALKEY = "myid";
	public static final String USERROOTDIR = "/mnt/nositer/user";
	public static final String USERDIRTEMPLATE = USERROOTDIR + "/{0}";
	public static final String USERPUBLICDIRTEMPLATE = USERDIRTEMPLATE + "/public";
	public static final String USERPRIVATEDIRTEMPLATE = USERDIRTEMPLATE + "/private";
	
	public static final String SWFLOADDIRFLASHPLAYER10 = "/public/swfupload/swfupload.swf";
	public static final String SWFLOADDIRFLASHPLAYER9 = "/public/swfupload/swfupload_fp9.swf";
	public static final String USER_URL_PREFIX = "/userfile";
	public static final String AVATAR_WIDTHHEIGHT = "200"; // in px
	public static final String PUBLICBASEDIR = "/public/image";
	public static final String DEFAULTAVATAR = PUBLICBASEDIR + "/unknownavatar.jpg";
	public static final String DEFAULTUSERAVATAR = "/public/defaultAvatar.png";
	
	
	public enum AccessType {
		PUBLIC, PRIVATE;
	}
}

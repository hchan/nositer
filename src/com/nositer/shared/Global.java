package com.nositer.shared;

public class Global {

	public static final String UPLOADURL = "/upload";
	public static final String CURRENTUSERASJSON = "/currentuserasjson";
	public static final String UPLOADCREDENTIALKEY = "myid";
	public static final String USERROOTDIR = "/mnt/nositer/user";
	public static final String USERIMAGEDIRTEMPLATE = USERROOTDIR + "/{0}/images";
	public static final String USERPUBLICIMAGEDIRTEMPLATE = USERROOTDIR + "/{0,number,integer}/images/public";
	public static final String USERPRIVATEIMAGEDIRTEMPLATE = USERROOTDIR + "/{0,number,integer}/images/private";
	
	public static final String SWFLOADDIRFLASHPLAYER10 = "/public/swfupload/swfupload.swf";
	public static final String SWFLOADDIRFLASHPLAYER9 = "/public/swfupload/swfupload_fp9.swf";
}

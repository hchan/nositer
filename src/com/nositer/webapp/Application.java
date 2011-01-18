package com.nositer.webapp;

import org.apache.log4j.Logger;

import com.nositer.util.NositerConfiguration;
import com.thoughtworks.xstream.XStream;

public class Application {
	public static Logger log = Logger.getLogger("nositer");
	public static NositerConfiguration cfg = new NositerConfiguration();
	
	public static void debug(Object obj) {
		XStream xstream = new XStream();
		log.debug(xstream.toXML(obj));
	}
}

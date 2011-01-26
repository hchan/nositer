package com.nositer.util.json;


public abstract class Transformer {

	public NameValuePair transform(String key, String value) {
		NameValuePair retval = new NameValuePair();
		retval.setName(key);
		retval.setValue(value);
		return retval;
	}
}

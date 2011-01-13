package com.nositer.util;

import java.util.HashMap;
import java.util.PropertyResourceBundle;

public abstract class PropertyConfiguration {
	protected static HashMap<String, PropertyResourceBundle> prbHM = new HashMap<String, PropertyResourceBundle>();
	
	public String get(final String key) {
        String messageString = null;
        try {
            PropertyResourceBundle prb = getPrb(getBundleName());
            messageString = prb.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageString;
	}
	
	public static PropertyResourceBundle getPrb(String bundleName) {		
		PropertyResourceBundle retval = null;
		if (prbHM.get(bundleName) == null) {
			retval = (PropertyResourceBundle) PropertyResourceBundle.getBundle(bundleName);
			prbHM.put(bundleName, retval);
		} else {
			retval = prbHM.get(bundleName);
		}
		return retval;
	}

	 protected  abstract String getBundleName();
	
}

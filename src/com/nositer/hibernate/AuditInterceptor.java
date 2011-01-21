package com.nositer.hibernate;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

@SuppressWarnings("serial")
public class AuditInterceptor extends EmptyInterceptor {
	
	public AuditInterceptor() {		
	}

	@Override 
	public boolean onFlushDirty(Object entity,
			Serializable id, Object[] currentState,
			Object[] previousState, String[] propertyNames,
			Type[] types) {
		boolean changed = false;
		changed = updateAuditable(currentState, propertyNames);
		return changed;
	}

	@Override 
	public boolean onSave(Object entity,
			Serializable id, Object[] currentState,
			String[] propertyNames, Type[] types) {
		boolean changed = false;
		changed = updateAuditable(currentState, propertyNames);
		return changed;
	}

	private boolean updateAuditable(Object[] currentState,
			String[] propertyNames) {
		boolean changed = false;
		for (int i = 0; i < propertyNames.length; i++) {
			
			if ("createdtime".equals(propertyNames[i])) {
				if (currentState[i] == null) {
					currentState[i] = new Date();
					changed = true;
				}
			} else if ("modifiedtime".equals(propertyNames[i])) {				
				currentState[i] = new Date();
				changed = true;				
			}
		}
		return changed;
	}

}


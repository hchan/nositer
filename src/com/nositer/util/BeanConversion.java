package com.nositer.util;

import com.nositer.client.dto.DTO;
import com.nositer.hibernate.Domain;

import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;

public class BeanConversion {
	static {
		net.sf.beanlib.hibernate.UnEnhancer.setDefaultCheckCGLib(false);
	}
	public static <T> T copyDomain2DTO (Domain domain, Class<T> toClass) {
		Hibernate3BeanReplicator r = new Hibernate3BeanReplicator();
		return r.shallowCopy(domain, toClass);
		
	}
	
	public static <T> T copyDTO2Domain (DTO dto, Class<T> toClass) {
		Hibernate3BeanReplicator r = new Hibernate3BeanReplicator();
		return r.shallowCopy(dto, toClass);
		
	}
}

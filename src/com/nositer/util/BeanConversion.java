package com.nositer.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;
import net.sf.beanlib.hibernate3.Hibernate3DtoCopier;

import com.nositer.client.dto.DTO;
import com.nositer.hibernate.Domain;

public class BeanConversion {
	static {
		net.sf.beanlib.hibernate.UnEnhancer.setDefaultCheckCGLib(false);
	}
	
	public static <T> T copyDomain2DTO (Domain domain, Class<T> toClass) {		
		Hibernate3BeanReplicator r = new Hibernate3BeanReplicator();
		return r.shallowCopy(domain, toClass);		
	}
	
	public static <T> T copyDTO2Domain (DTO dto, Class<T> toClass) {
		Hibernate3DtoCopier dtoCopier = new Hibernate3DtoCopier();
		return dtoCopier.hibernate2dto(toClass, dto, null, null);
	}
	
	public static <T> ArrayList<T> copyDomain2DTO (List<? extends Domain> domainList, Class<T> toClass) {
		ArrayList<T> retval = new ArrayList<T>();
		Hibernate3BeanReplicator r = new Hibernate3BeanReplicator();
		for (Domain domain : domainList) {
			retval.add(r.shallowCopy(domain, toClass));		
		}
		return retval;
	}
	
	public static <T> ArrayList<T> copyDTO2Domain (List<? extends DTO> dtoList, Class<T> toClass) {
		ArrayList<T> retval = new ArrayList<T>();		
		for (DTO dto : dtoList) {
			retval.add(copyDTO2Domain(dto, toClass));		
		}
		return retval;	
	}
}

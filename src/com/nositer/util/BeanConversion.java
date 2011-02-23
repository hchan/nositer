package com.nositer.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.beanlib.hibernate3.Hibernate3BeanReplicator;
import net.sf.beanlib.hibernate3.Hibernate3DtoCopier;
import net.sf.beanlib.provider.BeanTransformer;
import net.sf.beanlib.provider.replicator.BeanReplicator;
import net.sf.beanlib.spi.DetailedPropertyFilter;

import com.nositer.client.dto.DTO;
import com.nositer.hibernate.Domain;

@SuppressWarnings("unchecked")
public class BeanConversion {
	static {
		net.sf.beanlib.hibernate.UnEnhancer.setDefaultCheckCGLib(false);
	}
	
	public static <T> T copyDomain2DTO (Domain domain, Class<T> toClass) {	
		T retval = null;
		Hibernate3BeanReplicator r = new Hibernate3BeanReplicator();
		retval = r.shallowCopy(domain, toClass);
		if (!retval.getClass().equals(toClass)) {
			retval = r.shallowCopy(retval, toClass);
		}
		return  retval;
	}
	
	public static <T> T copyDTO2Domain (DTO dto, Class<T> toClass) {
		Hibernate3DtoCopier dtoCopier = new Hibernate3DtoCopier();
		return dtoCopier.hibernate2dto(toClass, dto, null, null);
	}
	
	public static void copyDTO2Domain(DTO dto, Domain domain, final ArrayList<String> columnNames) {
		
		BeanTransformer beanTransformer = new BeanTransformer();
		beanTransformer = beanTransformer.initDetailedPropertyFilter(new DetailedPropertyFilter() {
			
			@Override
			public boolean propagate(String propertyName, Object fromBean,
					Method readerMethod, Object toBean, Method setterMethod) {
				boolean retval = false;
				if (columnNames.contains(propertyName)) {
					retval = true;
				}
				return retval;
			}
		});
		BeanReplicator beanReplicator = new BeanReplicator(beanTransformer);
		beanReplicator.populate(dto, domain);
		
	}
	
	public static <T> ArrayList<T> copyDomain2DTO (List<? extends Domain> domainList, Class<T> toClass) {
		ArrayList<T> retval = new ArrayList<T>();		
		for (Domain domain : domainList) {
			retval.add(copyDomain2DTO(domain, toClass));		
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
	

	public static Class<? extends Domain> getDomainClass(Class<? extends DTO> dtoClass) throws ClassNotFoundException {
		Class<? extends Domain> retval = null;
		// Domain: com.nositer.hibernate.generated.domain
		// DTO: com.nositer.client.dto.generated;
		retval = (Class<? extends Domain>) Class.forName(dtoClass.getName().replace("client.dto.generated", "hibernate.generated.domain"));
		return retval;
	}
	
	public static Class<? extends DTO> getDTOClass(Class<? extends Domain> domainClass) throws ClassNotFoundException {
		Class<? extends DTO> retval = null;
		// Domain: com.nositer.hibernate.generated.domain
		// DTO: com.nositer.client.dto.generated;
		retval = (Class<? extends DTO>) Class.forName(domainClass.getName().replace("hibernate.generated.domain", "client.dto.generated"));
		return retval;
	}
}

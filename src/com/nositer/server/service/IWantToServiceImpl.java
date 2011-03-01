package com.nositer.server.service;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Iwantto;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.IWantToService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial"})
public class IWantToServiceImpl extends RemoteServiceServlet implements IWantToService {


	@Override
	public Iwantto createIWantTo(Iwantto iwantto) throws GWTException {
		Iwantto retval = null;
		com.nositer.hibernate.generated.domain.Iwantto iwanttoDomain = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			User user = Application.getCurrentUser();
			iwantto.setUser(user);
			iwanttoDomain = BeanConversion.copyDTO2Domain(iwantto, com.nositer.hibernate.generated.domain.Iwantto.class);
			sess.save(iwanttoDomain);
			trx.commit();
			retval = BeanConversion.copyDomain2DTO(iwanttoDomain, Iwantto.class);
			
		}
		catch (GWTException e) {
			throw e;
		}
		catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);		
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}
		return retval;
	}
	
}

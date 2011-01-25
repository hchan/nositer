package com.nositer.server.service;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.GWTException;
import com.nositer.client.service.RegisterService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;
import com.nositer.webapp.AuthorizationFilter;

@SuppressWarnings("serial")
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

	@Override
	public boolean register(User user) throws GWTException {
		boolean retval = false;
		 Session sess = HibernateUtil.getSession();
		 Transaction trx = null;
		 try {
		     trx = sess.beginTransaction();		   
		     com.nositer.hibernate.generated.domain.User userDomain = BeanConversion.copyDTO2Domain(user, com.nositer.hibernate.generated.domain.User.class);
		     sess.save(userDomain);
		     trx.commit();
		     user = BeanConversion.copyDomain2DTO(userDomain, User.class);
		     Application.setCurrentUser(user);
		     retval = true;
		 }
		 catch (Exception e) {
			 HibernateUtil.rollbackTransaction(trx);
			 
		     throw new GWTException(e);
		 }
		 finally {
		     sess.close();
		 }
		return retval;
	}
}

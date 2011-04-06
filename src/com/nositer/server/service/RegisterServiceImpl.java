package com.nositer.server.service;


import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.RegisterService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.server.util.FileUtil;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;
import com.nositer.webapp.Application;

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
			userDomain.setAvatarlocation(Global.USERPUBLICDIR + "/" + Global.DEFAULTUSERAVATAR);
			userDomain.setPassword(Encrypt.cryptPassword(userDomain.getPassword()));
			userDomain.setLastlogin(new Date());
			sess.save(userDomain);
			trx.commit();
			user = BeanConversion.copyDomain2DTO(userDomain, User.class);
			Application.setCurrentUser(user);
			FileUtil.createBasicFilesStructure(user);
			retval = true;
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

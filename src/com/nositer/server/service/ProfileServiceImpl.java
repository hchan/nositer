package com.nositer.server.service;


import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.ProfileService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;
import com.nositer.webapp.Application;
import com.nositer.webapp.AuthorizationFilter;

@SuppressWarnings("serial")
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

	@Override
	public User getCurrentUser() throws GWTException {
		User retval = null;
		retval = Application.getCurrentUser();
		return retval;
	}

	@Override
	public void logout() throws GWTException {
		Application.getRequest().getSession().invalidate();
	}

	@Override
	public User getCurrentUserForEditBasicProfile() throws GWTException {
		return getCurrentUser();
	}

	@Override
	public void updateCurrentUserForEditBasicProfile(User user) throws GWTException {

		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			com.nositer.hibernate.generated.domain.User userDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.User.class, getCurrentUser().getId(), sess);
			ArrayList<String> propertiesToCopy = new ArrayList<String>();
			propertiesToCopy.add(User.ColumnType.firstname.toString());
			propertiesToCopy.add(User.ColumnType.lastname.toString());
			propertiesToCopy.add(User.ColumnType.countrycode.toString());
			//propertiesToCopy.add(User.ColumnType.postalcode.toString());
			propertiesToCopy.add(User.ColumnType.zipcode.toString());
			propertiesToCopy.add(User.ColumnType.email.toString());
			propertiesToCopy.add(User.ColumnType.gendermale.toString());
			propertiesToCopy.add(User.ColumnType.profession.toString());
			propertiesToCopy.add(User.ColumnType.birthdate.toString());
			BeanConversion.copyDTO2Domain(user, userDomain, propertiesToCopy);
			sess.saveOrUpdate(userDomain);
			trx.commit();
			user = BeanConversion.copyDomain2DTO(userDomain, User.class);
			Application.setCurrentUser(user);
		}
		catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);		
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}

	}

}

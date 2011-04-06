package com.nositer.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nositer.hibernate.generated.domain.User;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings("unchecked")
public class ResetUserDirectoryStructures {

	public static void main(String[] args) {
		Session sess = HibernateUtil.getSession();

		Transaction trx = null;
		try {
			trx = sess.beginTransaction();
			List<User> domainUsers = sess.createSQLQuery("SELECT * FROM USER").addEntity(User.class).list();
			List<com.nositer.client.dto.generated.User> dtoUsers = BeanConversion.copyDomain2DTO(domainUsers, com.nositer.client.dto.generated.User.class);
			
			for (com.nositer.client.dto.generated.User dtoUser : dtoUsers) {
				Application.createBasicFilesStructure(dtoUser);
			}
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

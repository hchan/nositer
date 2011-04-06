package com.nositer.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nositer.hibernate.generated.domain.Group;
import com.nositer.hibernate.generated.domain.User;
import com.nositer.server.util.FileUtil;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings("unchecked")
public class ResetDirectoryStructures {

	public static void main(String[] args) {
		try {
			//resetUserDirectoryStructures();
			resetGroupDirectoryStructures();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resetGroupDirectoryStructures() {
		Session sess = HibernateUtil.getSession();

		Transaction trx = null;
		try {
			trx = sess.beginTransaction();
			List<Group> domainObjs = sess.createSQLQuery("SELECT * FROM NOSITER.GROUP").addEntity(Group.class).list();
			List<com.nositer.client.dto.generated.Group> dtoObjs = BeanConversion.copyDomain2DTO(domainObjs, com.nositer.client.dto.generated.Group.class);

			for (com.nositer.client.dto.generated.Group dtoGroup : dtoObjs) {
				FileUtil.createBasicFilesStructure(dtoGroup);
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

	public static void resetUserDirectoryStructures() {
		Session sess = HibernateUtil.getSession();

		Transaction trx = null;
		try {
			trx = sess.beginTransaction();
			List<User> domainObjs = sess.createSQLQuery("SELECT * FROM USER").addEntity(User.class).list();
			List<com.nositer.client.dto.generated.User> dtoObjs = BeanConversion.copyDomain2DTO(domainObjs, com.nositer.client.dto.generated.User.class);

			for (com.nositer.client.dto.generated.User dtoUser : dtoObjs) {
				FileUtil.createBasicFilesStructure(dtoUser);
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

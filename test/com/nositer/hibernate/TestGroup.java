package com.nositer.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nositer.client.dto.generated.User;
import com.nositer.hibernate.generated.domain.Group;
import com.nositer.server.service.GroupServiceImpl;
import com.nositer.shared.GWTException;
import com.nositer.webapp.Application;

public class TestGroup {
	public static void main(String[] args) {
		GroupServiceImpl groupServiceImpl = new GroupServiceImpl();
		
		groupServiceImpl.getMyGroups();
	}
	
	
	public static void main2(String[] args) {
		Session sess = HibernateUtil.getSession();
		
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();
			Group group = HibernateUtil.findByPrimaryKey(Group.class, 1, sess);
			System.out.println(group.getName());
			System.out.println(group.getUser().getFirstname());
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

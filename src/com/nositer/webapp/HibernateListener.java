package com.nositer.webapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.nositer.hibernate.HibernateUtil;


	public class HibernateListener implements ServletContextListener {

	 

		@Override
		public void contextDestroyed(ServletContextEvent arg0) {
			HibernateUtil.getSessionFactory().close();
			//HibernateUtil.getEMF().close();
		}

		@Override
		public void contextInitialized(ServletContextEvent arg0) {
			HibernateUtil.getSessionFactory();
			//HibernateUtil.getEMF();
		}
	}




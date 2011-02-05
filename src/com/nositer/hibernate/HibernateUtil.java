package com.nositer.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("unchecked")
public class HibernateUtil{

	private static SessionFactory sessionFactory;
	//private static EntityManagerFactory emf;
	
	public static Configuration cfg = null;
	static {
		initSessionFactory();
	}
	public static void initSessionFactory (){
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			cfg = new Configuration().configure();
			
			//cfg.addPackage(QueryHelper.class.getPackage().getName() + ".generated");
			//cfg.setProperty("hibernate.jdbc.batch_size", "20");
			//cfg = cfg.setProperty("connection.autocommit", "true");
			
			sessionFactory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	
	public static <T extends Domain>T findByPrimaryKey (Class<T> clazz, int pk, Session session) {
		return (T)session.get(clazz, new Integer(pk));
	}
	

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		return sessionFactory.openSession(new AuditInterceptor());
	}
	
	public static void closeSession(Session session) {
		session.close();
	}
	
	public static void rollbackTransaction(Transaction transaction) {
		transaction.rollback();
	}
}


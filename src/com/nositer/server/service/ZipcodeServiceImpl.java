package com.nositer.server.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Zipcode;
import com.nositer.client.service.ZipcodeService;
import com.nositer.hibernate.CommonSql;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial", "unchecked"})
public class ZipcodeServiceImpl extends RemoteServiceServlet implements ZipcodeService {

	@Override
	public ArrayList<Zipcode> getZipcodes(int offset, int limit, String query) throws GWTException {
		ArrayList<Zipcode> retval = new ArrayList<Zipcode>();
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		   
			List<com.nositer.hibernate.generated.domain.Zipcode> results = sess.createSQLQuery(SqlHelper.FINDZIPCODEBYCODE).addEntity(com.nositer.hibernate.generated.domain.Zipcode.class).
			setInteger(CommonSql.OFFSET, offset).setInteger(CommonSql.LIMIT, limit).setString(Zipcode.ColumnType.code.toString(), query.toUpperCase().replace(" ", "") + "%").list();
			retval = BeanConversion.copyDomain2DTO(results, Zipcode.class);
			
			trx.commit();
		}
		catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);			 
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			sess.close();
		}
		return retval;
	}

	

}

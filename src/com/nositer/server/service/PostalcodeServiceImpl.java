package com.nositer.server.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.service.PostalcodeService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings("serial")
public class PostalcodeServiceImpl extends RemoteServiceServlet implements PostalcodeService {

	@Override
	public ArrayList<Postalcode> getPostalcodes(int offset, int limit, String query) throws GWTException {
		ArrayList<Postalcode> retval = new ArrayList<Postalcode>();
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		   
			List<com.nositer.hibernate.generated.domain.Postalcode> results = sess.createSQLQuery(SqlHelper.FINDPOSTALCODEBYCODE.sql()).addEntity(com.nositer.hibernate.generated.domain.Postalcode.class).
			setInteger("OFFSET", offset).setInteger("LIMIT", limit).setString("CODE", query.toUpperCase().replace(" ", "") + "%").list();
			retval = BeanConversion.copyDomain2DTO(results, Postalcode.class);
			
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

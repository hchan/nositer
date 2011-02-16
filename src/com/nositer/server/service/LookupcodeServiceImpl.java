package com.nositer.server.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.Lookupcode;
import com.nositer.client.dto.generated.Postalcode;
import com.nositer.client.service.LookupcodeService;
import com.nositer.hibernate.CommonSql;
import com.nositer.hibernate.Domain;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial", "unchecked"})
public class LookupcodeServiceImpl extends RemoteServiceServlet implements LookupcodeService {

	
	public ArrayList<Postalcode> getPostalcodes(int offset, int limit, String query) throws GWTException {
		ArrayList<Postalcode> retval = new ArrayList<Postalcode>();
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		   			
			List<com.nositer.hibernate.generated.domain.Postalcode> results = sess.createSQLQuery(SqlHelper.FINDPOSTALCODEBYCODE).addEntity(com.nositer.hibernate.generated.domain.Postalcode.class).
			setInteger(CommonSql.OFFSET, offset).setInteger(CommonSql.LIMIT, limit).setString(Postalcode.ColumnType.code.toString(), query.toUpperCase().replace(" ", "") + "%").list();
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

	@Override
	public ArrayList<? extends Lookupcode> getCodes(String clazzName,
			int offset, int limit, String query) throws GWTException {
		ArrayList<? extends Lookupcode> retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			Class<? extends Lookupcode> clazzDTO = (Class<Lookupcode>) Class.forName(clazzName);
			Class<? extends Domain> clazzDomain = BeanConversion.getDomainClass(clazzDTO);
			Lookupcode instance = clazzDTO.newInstance();
			trx = sess.beginTransaction();		   			
			List<com.nositer.hibernate.generated.domain.Postalcode> results = sess.createSQLQuery(
					SqlHelper.createLookupSQL(instance.getTablename())).				
					addEntity(clazzDomain).
			setInteger(CommonSql.OFFSET, offset).setInteger(CommonSql.LIMIT, limit).setString(Postalcode.ColumnType.code.toString(), query.toUpperCase().replace(" ", "") + "%").list();
			retval = BeanConversion.copyDomain2DTO(results, clazzDTO);
			
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

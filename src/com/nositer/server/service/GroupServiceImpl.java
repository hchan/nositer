package com.nositer.server.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.UserHasGroupView;
import com.nositer.client.service.GroupService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.UserHasGroup;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.util.HTMLPurifier;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial", "unchecked"})
public class GroupServiceImpl extends RemoteServiceServlet implements GroupService {


	@Override
	public Group createGroup(Group group) throws GWTException {
		Group retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			String description = group.getDescription();
			group.setDescription(HTMLPurifier.getCleanHTML(description));
			User user = Application.getCurrentUser();
			String tagname = group.getTagname();
			if (!isValidTagname(tagname)) {
				throw new GWTException("Tagname contains must be consist of alpha-numeric characters or _");
			}
			group.setTagname(tagname);		
			
			group.setPostalcode(user.getPostalcode());
			group.setZipcode(user.getZipcode());
			group.setCountrycode(user.getCountrycode());
			group.setDisable(false);
			com.nositer.hibernate.generated.domain.Group groupDomain = BeanConversion.copyDTO2Domain(group, com.nositer.hibernate.generated.domain.Group.class);

			sess.save(groupDomain);
			
			UserHasGroup userHasGroupDomain = new UserHasGroup();
			userHasGroupDomain.setGroup(groupDomain);
			userHasGroupDomain.setOwner(true);
			userHasGroupDomain.setUser(BeanConversion.copyDTO2Domain(user, com.nositer.hibernate.generated.domain.User.class));
			sess.save(userHasGroupDomain);
			
			
			trx.commit();
			retval = BeanConversion.copyDomain2DTO(groupDomain, Group.class);

		}
		catch (GWTException e) {
			throw e;
		}
		catch (ConstraintViolationException e) {
			HibernateUtil.rollbackTransaction(trx);		
			throw new GWTException("Tagname: " + group.getTagname() + " is already taken");
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
	
	@Override
	public Group updateGroup(Group group) throws GWTException {
		Group retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			String description = group.getDescription();
			group.setDescription(HTMLPurifier.getCleanHTML(description));
			User user = Application.getCurrentUser();
			String tagname = group.getTagname();
			if (!isValidTagname(tagname)) {
				throw new GWTException("Tagname contains must be consist of alpha-numeric characters or _");
			}
			group.setTagname(tagname);				
			group.setPostalcode(user.getPostalcode());
			group.setZipcode(user.getZipcode());
			group.setCountrycode(user.getCountrycode());
			group.setDisable(false);
			com.nositer.hibernate.generated.domain.Group groupDomain = BeanConversion.copyDTO2Domain(group, com.nositer.hibernate.generated.domain.Group.class);

			sess.update(groupDomain);
			trx.commit();
			retval = BeanConversion.copyDomain2DTO(groupDomain, Group.class);

		}
		catch (GWTException e) {
			throw e;
		}
		catch (ConstraintViolationException e) {
			HibernateUtil.rollbackTransaction(trx);		
			throw new GWTException("Tagname or Groupname is already taken");
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


	public static boolean isValidTagname(String tagname) {
		boolean retval = false;
		String validCharsPattern = "^([A-Za-z0-9_\\.])+$";
		retval = tagname.matches(validCharsPattern);		
		return retval;
	}


	@Override
	public ArrayList<UserHasGroupView> getMyGroups() throws GWTException {
		ArrayList<UserHasGroupView> retval = new ArrayList<UserHasGroupView>();
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.UserHasGroupView> results = sess.createSQLQuery(
					SqlHelper.FINDMYGROUPS).		
					addEntity(com.nositer.hibernate.generated.domain.UserHasGroupView.class).
					setInteger(UserHasGroupView.ColumnType.userid.toString(), 
							user.getId()
					).
					list();

			retval = BeanConversion.copyDomain2DTO(results, UserHasGroupView.class);					
		}
		catch (GWTException e) {
			throw e;
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


	@Override
	public UserHasGroupView getGroupByTagname(String tagname) throws GWTException {
		UserHasGroupView retval = null;
		Session sess = HibernateUtil.getSession();

		Transaction trx = null;
		try {

			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.UserHasGroupView> results = sess.createSQLQuery(SqlHelper.FINDGROUPBYTAGNAME).addEntity(com.nositer.hibernate.generated.domain.UserHasGroupView.class).
			setString(UserHasGroupView.ColumnType.tagname.toString(), tagname).list();
			if (results.size() == 0) {				
				retval = null;
			} else {
				com.nositer.hibernate.generated.domain.UserHasGroupView userHasGroupViewDomain = results.get(0);
				retval = BeanConversion.copyDomain2DTO(userHasGroupViewDomain, UserHasGroupView.class);
			}
		}
		catch (GWTException e) {
			throw e;
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


	@Override
	public void deleteGroup(UserHasGroupView userHasGroupView) throws GWTException {		
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			if (user.getId().equals(userHasGroupView.getUserid())) {				
				sess.createSQLQuery(
						SqlHelper.disableSQL(userHasGroupView)).executeUpdate();
			} else {
				throw new GWTException("You are not the owner of this group");
			}			
			trx.commit();			
		}
		catch (GWTException e) {
			throw e;
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


	@Override
	public ArrayList<UserHasGroupView> search(String name, Float latitude, Float longitude, Number radius) throws GWTException {
		ArrayList<UserHasGroupView> retval = null;
		Session sess = HibernateUtil.getSession();

		Transaction trx = null;
		try {

			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.Group> results = sess.createSQLQuery(SqlHelper.FINDGROUPS).
			addEntity(com.nositer.hibernate.generated.domain.Group.class).
			setString(Group.ColumnType.name.toString(), "%" + name + "%").
			setParameter("latitude", latitude).		
			setParameter("longitude", longitude).
			setParameter("radius", radius).	
			list();
			if (results.size() == 0) {				
				retval = new ArrayList<UserHasGroupView>();
			} else {
				retval = BeanConversion.copyDomain2DTO(results, UserHasGroupView.class);									
			}
		}
		catch (GWTException e) {
			throw e;
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

package com.nositer.server.service;


import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.service.GroupService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.UserHasGroup;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
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
			groupDomain.setAvatarlocation(Global.GROUPPUBLICDIR + "/" + Global.DEFAULTGROUPAVATAR);			
			sess.save(groupDomain);
			UserHasGroup userHasGroupDomain = new UserHasGroup();
			userHasGroupDomain.setGroup(groupDomain);
			userHasGroupDomain.setOwner(true);
			userHasGroupDomain.setUser(BeanConversion.copyDTO2Domain(user, com.nositer.hibernate.generated.domain.User.class));
			sess.save(userHasGroupDomain);
			trx.commit();
			retval = BeanConversion.copyDomain2DTO(groupDomain, Group.class);
			createBasicFilesStructure(retval);
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
	
	private void createBasicFilesStructure(Group group) throws IOException {
		FileServiceImpl fileServiceImpl = new FileServiceImpl();
		fileServiceImpl.createDirsIfNecessary();
		File defaultAvatar = new File(Application.getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTGROUPAVATAR));		
		File publicDir = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()));
		FileUtils.copyFileToDirectory(defaultAvatar, publicDir);
		File publicREADME = new File(MessageFormat.format(Global.GROUPPUBLICDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.  Your groupid is: " + group.getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of " + Global.GROUP_URL_PREFIX + "/" + group.getId() +
				"\nFor example, your default avatar is viewable at this location: " +
				Global.GROUP_URL_PREFIX + "/" + group.getId() + "/" + Global.DEFAULTGROUPAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.GROUPPRIVATEDIRTEMPLATE, group.getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for you to upload files to private (not be viewable to anyone else");
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
	public ArrayList<GroupPlusView> getMyGroups() throws GWTException {
		ArrayList<GroupPlusView> retval = new ArrayList<GroupPlusView>();
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupPlusView> results = sess.createSQLQuery(
					SqlHelper.FINDMYGROUPS).		
					addEntity(com.nositer.hibernate.generated.domain.GroupPlusView.class).
					setInteger(GroupPlusView.ColumnType.userid.toString(), 
							user.getId()
					).
					list();

			retval = BeanConversion.copyDomain2DTO(results, GroupPlusView.class);					
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
	public GroupPlusView getGroupByTagname(String tagname) throws GWTException {
		GroupPlusView retval = null;
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupPlusView> results = sess.createSQLQuery(SqlHelper.FINDGROUPBYTAGNAME).addEntity(com.nositer.hibernate.generated.domain.GroupPlusView.class).
			setString(GroupPlusView.ColumnType.tagname.toString(), tagname).
			setInteger(GroupPlusView.ColumnType.userid.toString(), user.getId()).
			list();
			if (results.size() == 0) {				
				retval = null;
			} else {
				com.nositer.hibernate.generated.domain.GroupPlusView groupPlusViewDomain = results.get(0);
				retval = BeanConversion.copyDomain2DTO(groupPlusViewDomain, GroupPlusView.class);
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
	public void disableGroup(GroupPlusView groupPlusView) throws GWTException {		
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();				
			sess.createSQLQuery(SqlHelper.DISABLEGROUP).
			setInteger(GroupPlusView.ColumnType.userid.toString(), user.getId())
			.executeUpdate();			
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
	public ArrayList<GroupPlusView> search(String name, Float latitude, Float longitude, Number radius) throws GWTException {
		ArrayList<GroupPlusView> retval = null;
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupPlusView> results = sess.createSQLQuery(SqlHelper.FINDGROUPS).
			addEntity(com.nositer.hibernate.generated.domain.GroupPlusView.class).
			setString(Group.ColumnType.name.toString(), "%" + name + "%").			
			setInteger(GroupPlusView.ColumnType.userid.toString(), user.getId()).
			setParameter("latitude", latitude).		
			setParameter("longitude", longitude).
			setParameter("radius", radius).	
			list();
			if (results.size() == 0) {				
				retval = new ArrayList<GroupPlusView>();
			} else {
				retval = BeanConversion.copyDomain2DTO(results, GroupPlusView.class);									
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
	public void createOrUpdateSubscription(GroupPlusView groupPlusView)
	throws GWTException {
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			if (user.getId().equals(groupPlusView.getUserid())) {	
				sess.createSQLQuery(SqlHelper.UPDATESUBSCRIPTION).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.ColumnType.disable.toString(), groupPlusView.getUserHasGroupDisable()).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.ColumnType.invisible.toString(), groupPlusView.getInvisible()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.ColumnType.id.toString(), groupPlusView.getUserHasGroupId()).
				executeUpdate();				
			} else {
				sess.createSQLQuery(SqlHelper.CREATESUBSCRIPTION).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.ColumnType.userid.toString(), user.getId()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.ColumnType.groupid.toString(), groupPlusView.getId()).
				executeUpdate();
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

}

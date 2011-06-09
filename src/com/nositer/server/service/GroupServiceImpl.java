package com.nositer.server.service;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.query.NamedParameterDescriptor;
import org.hibernate.engine.query.ParamLocationRecognizer.NamedParameterDescription;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.jdbc.Work;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mysql.jdbc.Statement;
import com.nositer.client.dto.generated.Group;
import com.nositer.client.dto.generated.GroupSubscriptionView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.GroupmessagePlusView;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.dto.generated.User;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.service.GroupService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.UserHasGroup;
import com.nositer.server.util.FileUtil;
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
			FileUtil.createBasicFilesStructure(retval);
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
					setInteger(GroupPlusView.Column.userid.toString(), 
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
			setString(GroupPlusView.Column.tagname.toString(), tagname).
			setInteger(GroupPlusView.Column.userid.toString(), user.getId()).
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
			setInteger(GroupPlusView.Column.userid.toString(), user.getId())
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
			setString(Group.Column.name.toString(), "%" + name + "%").			
			setInteger(GroupPlusView.Column.userid.toString(), user.getId()).
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
	public void createOrUpdateSubscription(com.nositer.client.dto.generated.UserHasGroup userHasGroup)
	throws GWTException {
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			/*
			if (user.getId().equals(groupPlusView.getUserid())) {	
				sess.createSQLQuery(SqlHelper.UPDATESUBSCRIPTION).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.Column.disable.toString(), groupPlusView.getUserHasGroupDisable()).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.Column.invisible.toString(), groupPlusView.getInvisible()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.Column.id.toString(), groupPlusView.getUserHasGroupId()).
				executeUpdate();				
			} else {
			 */
			try {
				sess.createSQLQuery(SqlHelper.CREATESUBSCRIPTION).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.Column.userid.toString(), user.getId()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.Column.groupid.toString(), userHasGroup.getGroupid()).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.Column.invisible.toString(), userHasGroup.getInvisible()).
				executeUpdate();
				trx.commit();			
			} catch (ConstraintViolationException e) {
				trx.rollback();
				trx = sess.beginTransaction();		
				sess.createSQLQuery(SqlHelper.UPDATESUBSCRIPTION).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.Column.disable.toString(), userHasGroup.getDisable()).
				setBoolean(com.nositer.client.dto.generated.UserHasGroup.Column.invisible.toString(), userHasGroup.getInvisible()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.Column.userid.toString(), user.getId()).
				setInteger(com.nositer.client.dto.generated.UserHasGroup.Column.groupid.toString(), userHasGroup.getGroupid()).
				executeUpdate();	
				trx.commit();
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
	}

	@Override
	public ArrayList<GroupSubscriptionView> getSubscriptions(
			GroupPlusView groupPlusView) throws GWTException {
		ArrayList<GroupSubscriptionView> retval = null;
		Session sess = HibernateUtil.getSession();
		User user = null;
		int invisible = 0;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			if (isOwner(groupPlusView, user)) {
				invisible = 1;
			}
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupSubscriptionView> results = sess.createSQLQuery(SqlHelper.GETSUBSCRIPTIONS).
			addEntity(com.nositer.hibernate.generated.domain.GroupSubscriptionView.class).

			setInteger(GroupSubscriptionView.Column.groupid.toString(), groupPlusView.getId()).
			setInteger(GroupSubscriptionView.Column.invisible.toString(), invisible).
			list();
			if (results.size() == 0) {				
				retval = new ArrayList<GroupSubscriptionView>();
			} else {
				retval = BeanConversion.copyDomain2DTO(results, GroupSubscriptionView.class);									
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

	private boolean isOwner(GroupPlusView groupPlusView, User user) {
		boolean retval = false;
		if (groupPlusView.getOwner() && groupPlusView.getUserid().equals(user.getId())) {
			retval = true;
		}
		return retval;
	}

	private boolean isGroupIBelongTo(GroupPlusView groupPlusView, User user) {
		boolean retval = false;
		if (groupPlusView.getUserid().equals(user.getId()) && !groupPlusView.getUserHasGroupDisable()) {
			retval = true;
		}
		return retval;
	}

	@Override
	public ArrayList<GroupSubscriptionView> findSubscriptions(
			GroupPlusView groupPlusView, String lastname) throws GWTException {
		ArrayList<GroupSubscriptionView> retval = null;
		Session sess = HibernateUtil.getSession();
		User user = null;
		int invisible = 0;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			if (isOwner(groupPlusView, user)) {
				invisible = 1;
			}
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupSubscriptionView> results = sess.createSQLQuery(SqlHelper.FINDSUBSCRIPTIONS).
			addEntity(com.nositer.hibernate.generated.domain.GroupSubscriptionView.class).
			setString(GroupSubscriptionView.Column.lastname.toString(), lastname + "%").			
			setInteger(GroupSubscriptionView.Column.groupid.toString(), groupPlusView.getId()).
			setInteger(GroupSubscriptionView.Column.invisible.toString(), invisible).
			list();
			if (results.size() == 0) {				
				retval = new ArrayList<GroupSubscriptionView>();
			} else {
				retval = BeanConversion.copyDomain2DTO(results, GroupSubscriptionView.class);									
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

	public GroupSubscriptionView getGroupSubscriptionByGroupAndUser(int groupid, int userid) {
		GroupSubscriptionView retval = null;
		Session sess = HibernateUtil.getSession();
		//User user = null;
		Transaction trx = null;
		try {
			//user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.GroupSubscriptionView> results = sess.createSQLQuery(SqlHelper.FINDSUBSCRIBER).
			addEntity(com.nositer.hibernate.generated.domain.GroupSubscriptionView.class).		
			setInteger(GroupSubscriptionView.Column.groupid.toString(), groupid).
			setInteger(GroupSubscriptionView.Column.userid.toString(), userid).		
			list();
			if (results.size() != 0) {				
				retval = BeanConversion.copyDomain2DTO(results.get(0), GroupSubscriptionView.class);
			} else {
				retval = null;							
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


	public GroupPlusView getGroupPlusView (Integer groupid) {
		GroupPlusView retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();								
			com.nositer.hibernate.generated.domain.GroupPlusView groupPlusViewDomain =
				(com.nositer.hibernate.generated.domain.GroupPlusView) sess.createSQLQuery(SqlHelper.GETGROUPPLUSVIEW).addEntity(com.nositer.hibernate.generated.domain.GroupPlusView.class).
				setInteger(GroupPlusView.Column.id.toString(), groupid).
				uniqueResult();

			//	com.nositer.hibernate.generated.domain.GroupPlusView groupPlusViewDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.GroupPlusView.class, groupid, sess);
			retval = BeanConversion.copyDomain2DTO(groupPlusViewDomain, GroupPlusView.class);
		} catch (Exception e) {
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
	public Grouptopic createGrouptopic(GroupPlusView groupPlusView, final Grouptopic grouptopic) throws GWTException {
		Grouptopic retval = null;
		final Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		User user = null;
		try {
			trx = sess.beginTransaction();	
			user = Application.getCurrentUser();
			if (!isGroupIBelongTo(groupPlusView, user)) {
				throw new GWTException("You do not have permissions to create a group topic");
			}
			final HashSet<Integer> grouptopicidHM = new HashSet<Integer>();
			/*
			sess.createSQLQuery(SqlHelper.CREATEGROUPTOPIC).
			setInteger(Grouptopic.Column.userid.toString(), grouptopic.getUserid()).
			setInteger(Grouptopic.Column.groupid.toString(), grouptopic.getGroupid()).		
			setString(Grouptopic.Column.name.toString(), grouptopic.getName()).
			executeUpdate();
			 */

			sess.doWork(new Work() {

				@Override
				public void execute(Connection con) throws SQLException {


					PreparedStatement pstmt = con.prepareStatement(SqlHelper.CREATEGROUPTOPIC, Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, grouptopic.getUserid());
					pstmt.setInt(2, grouptopic.getGroupid());
					pstmt.setString(3, grouptopic.getName());
					pstmt.execute();

					ResultSet resultSet = pstmt.getGeneratedKeys();

					if (resultSet != null && resultSet.next()) { 
						grouptopicidHM.add(resultSet.getInt(1));
					}
				}
			});

			final int grouptopicid = grouptopicidHM.toArray(new Integer[]{})[0];
			final Groupmessage groupmessage = grouptopic.getGroupmessages().toArray(new Groupmessage[]{})[0];
			final HashSet<Integer> groupmessageidHM = new HashSet<Integer>();

			sess.doWork(new Work() {
				@Override
				public void execute(Connection con) throws SQLException {


					PreparedStatement pstmt = con.prepareStatement(SqlHelper.CREATEGROUPMESSAGE, Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, groupmessage.getUserid());
					pstmt.setInt(2, grouptopicid);
					pstmt.setString(3, groupmessage.getDescription());
					pstmt.execute();

					ResultSet resultSet = pstmt.getGeneratedKeys();

					if (resultSet != null && resultSet.next()) { 
						groupmessageidHM.add(resultSet.getInt(1));
					}
				}
			});
			/*
			int groupmessageid = sess.createSQLQuery(SqlHelper.CREATEGROUPMESSAGE).
			setInteger(Groupmessage.Column.userid.toString(), groupmessage.getUserid()).
			setInteger(Groupmessage.Column.grouptopicid.toString(), grouptopicid).		
			setString(Groupmessage.Column.description.toString(), groupmessage.getDescription()).
			executeUpdate();
			 */

			trx.commit();
			retval = grouptopic;
			int groupmessageid = groupmessageidHM.toArray(new Integer[]{})[0];
			groupmessage.setId(groupmessageid);
			groupmessage.setUser(Application.getCurrentUser());
			groupmessage.setCreatedtime(new Date());

			retval.setId(grouptopicid);

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
	public ArrayList<GroupmessagePlusView> getGroupmessages(GroupPlusView groupPlusView) throws GWTException {
		ArrayList<GroupmessagePlusView> retval = null;
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			if (!isGroupIBelongTo(groupPlusView, user)) {
				throw new GWTException("You do not have permissions to retrieve messages in this group");
			}
			trx = sess.beginTransaction();		

			List<com.nositer.hibernate.generated.domain.GroupmessagePlusView> results =
				sess.createSQLQuery(SqlHelper.FINDGROUPMESSAGES).addEntity(com.nositer.hibernate.generated.domain.GroupmessagePlusView.class).
				list();

			if (results.size() == 0) {				
				retval = new ArrayList<GroupmessagePlusView>();
			} else {
				retval = BeanConversion.copyDomain2DTO(results, GroupmessagePlusView.class);									
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
	public Groupmessage getGroupmessage(Integer groupmessageid) throws GWTException {
		Groupmessage retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			com.nositer.hibernate.generated.domain.Groupmessage groupmessageDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.Groupmessage.class, groupmessageid, sess);
			retval = BeanConversion.copyDomain2DTO(groupmessageDomain, Groupmessage.class);
			com.nositer.hibernate.generated.domain.Grouptopic grouptopicDomain = groupmessageDomain.getGrouptopic();
			Grouptopic grouptopic = BeanConversion.copyDomain2DTO(grouptopicDomain, Grouptopic.class);		
			retval.setGrouptopic(grouptopic);

			BigInteger bigInteger = (BigInteger) sess.createSQLQuery(SqlHelper.GETNUMGROUPMESSAGESBYGROUPTOPIC).
			setInteger(Groupmessage.Column.grouptopicid.toString(), retval.getGrouptopicid()).uniqueResult();
			Integer numGroupmessages = bigInteger.intValue(); 


			bigInteger = (BigInteger) sess.createSQLQuery(SqlHelper.GETINDEXOFGROUPMESSAGEINGROUPTOPIC).
			setInteger(Groupmessage.Column.grouptopicid.toString(), retval.getGrouptopicid()).
			setInteger(Groupmessage.Column.id.toString(), retval.getId()).
			uniqueResult();
			Integer indexOfGroupmessageInGrouptopic = bigInteger.intValue();


			HashSet<Groupmessage> bogusGroupmessages = new HashSet<Groupmessage>(numGroupmessages);
		
			// add a whole bunch of bogus groupmessage
			// however ONE of them is really the one we are returning
			for (int i = 0; i < numGroupmessages; i++) {			
				Groupmessage bogusGroupmessage = new Groupmessage();
				if (i == indexOfGroupmessageInGrouptopic) {
					bogusGroupmessages.add(retval);
				} else {
					int bogusId = (i+1) * -1;
					bogusGroupmessage.setId(bogusId);
					bogusGroupmessages.add(bogusGroupmessage);
				}
			}
			grouptopic.setGroupmessages(bogusGroupmessages);

			com.nositer.hibernate.generated.domain.User userDomain = groupmessageDomain.getUser();
			User user = BeanConversion.copyDomain2DTO(userDomain, User.class);
			retval.setUser(user);
		} catch (Exception e) {
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
	public Groupmessage addGroupmessage(GroupPlusView groupPlusView, final Groupmessage groupmessage) throws GWTException {
		Groupmessage retval = null;
		final Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		User user = null;
		try {
			trx = sess.beginTransaction();	
			user = Application.getCurrentUser();
			if (!isGroupIBelongTo(groupPlusView, user)) {
				throw new GWTException("You do not have permissions to create a group message");
			}


			final int grouptopicid = groupmessage.getGrouptopicid();
			final HashSet<Integer> groupmessageidHM = new HashSet<Integer>();

			sess.doWork(new Work() {
				@Override
				public void execute(Connection con) throws SQLException {


					PreparedStatement pstmt = con.prepareStatement(SqlHelper.CREATEGROUPMESSAGE, Statement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, groupmessage.getUserid());
					pstmt.setInt(2, grouptopicid);
					pstmt.setString(3, groupmessage.getDescription());
					pstmt.execute();

					ResultSet resultSet = pstmt.getGeneratedKeys();

					if (resultSet != null && resultSet.next()) { 
						groupmessageidHM.add(resultSet.getInt(1));
					}
				}
			});
		

			trx.commit();
			int groupmessageid = groupmessageidHM.toArray(new Integer[]{})[0];
			retval = getGroupmessage(groupmessageid);
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

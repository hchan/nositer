package com.nositer.server.service;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Relationshipcode;
import com.nositer.client.dto.generated.Salutationcode;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.ProfileService;
import com.nositer.client.widget.Location;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.Postalcode;
import com.nositer.hibernate.generated.domain.Zipcode;
import com.nositer.shared.GWTException;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;
import com.nositer.util.HTMLPurifier;
import com.nositer.webapp.Application;

@SuppressWarnings("serial")
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

	@Override
	public User getCurrentUser() throws GWTException {
		User retval = null;
		Session sess = HibernateUtil.getSession();
		try {
			com.nositer.hibernate.generated.domain.User userDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.User.class, Application.getCurrentUser().getId(), sess);
			retval = getUserByDomain(userDomain);
			Application.setCurrentUser(retval);
		} 
		catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}		
		return retval;
	}

	@Override
	public void logout() throws GWTException {
		Application.getRequest().getSession().invalidate();
	}

	@Override
	public User getCurrentUserForEditBasicProfile() throws GWTException {
		return getCurrentUser();
	}

	@Override
	public void updateCurrentUserForEditBasicProfile(User user) throws GWTException {
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			Integer postalcodeid = null;
			Integer zipcodeid = null;
			Integer salutationcodeid = null;
			Integer relationshipcodeid = null;
			if (user.getPostalcode() != null) {
				postalcodeid = user.getPostalcode().getId();
			} else {
				zipcodeid = user.getZipcode().getId();				
			}
			if (user.getSalutationcode() != null) {
				salutationcodeid = user.getSalutationcode().getId();
			}
			if (user.getRelationshipcode() != null) {
				relationshipcodeid = user.getRelationshipcode().getId();
			}
			sess.createSQLQuery(SqlHelper.UPDATEBASICPROFILE).
			setString(User.Column.firstname.toString(), user.getFirstname()).
			setString(User.Column.lastname.toString(), user.getLastname()).
			setParameter(User.Column.postalcodeid.toString(), postalcodeid, new IntegerType()).		
			setParameter(User.Column.zipcodeid.toString(), zipcodeid, new IntegerType()).
			setParameter(User.Column.salutationcodeid.toString(), salutationcodeid, new IntegerType()).
			setParameter(User.Column.relationshipcodeid.toString(), relationshipcodeid, new IntegerType()).
			setString(User.Column.countrycode.toString(), user.getCountrycode()).
			setString(User.Column.avatarlocation.toString(), user.getAvatarlocation()).
			setString(User.Column.email.toString(), user.getEmail()).
			setBoolean(User.Column.gendermale.toString(), user.getGendermale()).
			setString(User.Column.profession.toString(), user.getProfession()).
			setDate(User.Column.birthdate.toString(), user.getBirthdate()).
			setInteger(User.Column.id.toString(), getCurrentUser().getId()).
			executeUpdate();
			//com.nositer.hibernate.generated.domain.User userDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.User.class, getCurrentUser().getId(), sess);
			trx.commit();
			//user = getCurrentUser(userDomain);
			//Application.setCurrentUser(user);
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

	public User getUserByDomain(com.nositer.hibernate.generated.domain.User userDomain) {
		User retval = null;
		retval = BeanConversion.copyDomain2DTO(userDomain, com.nositer.client.dto.generated.User.class);

		if (userDomain.getCountrycode().equals(Location.COUNTRYCODE_CAN)) {
			Postalcode postalcodeDomain = userDomain.getPostalcode();
			com.nositer.client.dto.generated.Postalcode postalcodeDTO = 
				BeanConversion.copyDomain2DTO(postalcodeDomain, com.nositer.client.dto.generated.Postalcode.class);
			retval.setPostalcode(postalcodeDTO);
		} else {
			Zipcode zipcodeDomain = userDomain.getZipcode();
			com.nositer.client.dto.generated.Zipcode zipcodeDTO = BeanConversion.copyDomain2DTO(zipcodeDomain, com.nositer.client.dto.generated.Zipcode.class);
			retval.setZipcode(zipcodeDTO);
		}

		com.nositer.hibernate.generated.domain.Salutationcode salutationcodeDomain = userDomain.getSalutationcode();
		if (salutationcodeDomain != null) {
			Salutationcode salutationcodeDTO = BeanConversion.copyDomain2DTO(salutationcodeDomain, com.nositer.client.dto.generated.Salutationcode.class);
			retval.setSalutationcode(salutationcodeDTO);
		}

		com.nositer.hibernate.generated.domain.Relationshipcode relationshipcodeDomain = userDomain.getRelationshipcode();
		if (relationshipcodeDomain != null) {
			Relationshipcode relationshipcodeDTO = BeanConversion.copyDomain2DTO(relationshipcodeDomain, com.nositer.client.dto.generated.Relationshipcode.class);
			retval.setRelationshipcode(relationshipcodeDTO);
		}
		return retval;
	}

	@Override
	public void updatePasswordOfCurrentUser(String oldPassword,
			String newPassword) throws GWTException {
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			String currentPassword = getCurrentUser().getPassword();
			String oldPasswordEncrypted = Encrypt.cryptPassword(oldPassword);
			if (currentPassword.equals(oldPasswordEncrypted)) {
				String newPasswordEncrypted = Encrypt.cryptPassword(newPassword);
				sess.createSQLQuery(SqlHelper.CHANGEPASSWORD).
				setString(User.Column.password.toString(), newPasswordEncrypted).			
				setInteger(User.Column.id.toString(), getCurrentUser().getId()).
				executeUpdate();
				trx.commit();
				//getCurrentUser().setPassword(newPasswordEncrypted);
			} else {
				throw new GWTException("Old Password entered is not correct");
			}
		}
		catch (GWTException e) {
			throw e;
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);		
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}

	}

	@Override
	public void updateAboutMeOfCurrentUser(String note, String description)
	throws GWTException {
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			String cleanNote = HTMLPurifier.getCleanHTML(note);
			String cleanDescription = HTMLPurifier.getCleanHTML(description);
			sess.createSQLQuery(SqlHelper.UPDATEABOUTME).
			setString(User.Column.note.toString(), cleanNote).
			setDate(User.Column.notemodifedtime.toString(), new Date()).
			setString(User.Column.description.toString(), cleanDescription).		
			setInteger(User.Column.id.toString(), getCurrentUser().getId()).
			executeUpdate();
			trx.commit();
		}
		catch (GWTException e) {
			throw e;
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);		
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}
	}

	@Override
	public void updateAvatarOfCurrentUser(String avatarlocation)
	throws GWTException {
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();				
			sess.createSQLQuery(SqlHelper.UPDATEAVATAR).
			setString(User.Column.avatarlocation.toString(), avatarlocation).				
			setInteger(User.Column.id.toString(), getCurrentUser().getId()).
			executeUpdate();
			trx.commit();
		}
		catch (GWTException e) {
			throw e;
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction(trx);		
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}
	}

	@Override
	public User getUser(Integer id) {
		User retval = null;
		Session sess = HibernateUtil.getSession();
		try {
			com.nositer.hibernate.generated.domain.User userDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.User.class, id, sess);
			retval = getUserByDomain(userDomain);
		} 
		catch (Exception e) {
			Application.log.error("", e);
			throw new GWTException(e);
		}
		finally {
			HibernateUtil.closeSession(sess);
		}		
		return retval;
	}

}

package com.nositer.server.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
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
import com.nositer.webapp.Application;

@SuppressWarnings("serial")
public class ProfileServiceImpl extends RemoteServiceServlet implements ProfileService {

	@Override
	public User getCurrentUser() throws GWTException {
		User retval = null;
		retval = Application.getCurrentUser();
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
			if (user.getPostalcode() != null) {
				postalcodeid = user.getPostalcode().getId();
			} else {
				zipcodeid = user.getZipcode().getId();				
			}
			sess.createSQLQuery(SqlHelper.UPDATEBASICPROFILE).
			setString(User.ColumnType.firstname.toString(), user.getFirstname()).
			setString(User.ColumnType.lastname.toString(), user.getLastname()).
			setParameter(User.ColumnType.postalcodeid.toString(), postalcodeid, new IntegerType()).		
			setParameter(User.ColumnType.zipcodeid.toString(), zipcodeid, new IntegerType()).
			setString(User.ColumnType.countrycode.toString(), user.getCountrycode()).
			setString(User.ColumnType.email.toString(), user.getEmail()).
			setBoolean(User.ColumnType.gendermale.toString(), user.getGendermale()).
			setString(User.ColumnType.profession.toString(), user.getProfession()).
			setDate(User.ColumnType.birthdate.toString(), user.getBirthdate()).
			setInteger(User.ColumnType.id.toString(), getCurrentUser().getId()).
			executeUpdate();
			com.nositer.hibernate.generated.domain.User userDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.User.class, getCurrentUser().getId(), sess);
			trx.commit();
			user = getCurrentUser(userDomain);
			Application.setCurrentUser(user);
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

	public User getCurrentUser(com.nositer.hibernate.generated.domain.User userDomain) {
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
				setString(User.ColumnType.password.toString(), newPasswordEncrypted).			
				setInteger(User.ColumnType.id.toString(), getCurrentUser().getId()).
				executeUpdate();
				trx.commit();
				getCurrentUser().setPassword(newPasswordEncrypted);
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

}

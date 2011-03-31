package com.nositer.server.service;


import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.RegisterService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;
import com.nositer.webapp.Application;

@SuppressWarnings("serial")
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

	@Override
	public boolean register(User user) throws GWTException {
		boolean retval = false;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {		
			trx = sess.beginTransaction();		   
			com.nositer.hibernate.generated.domain.User userDomain = BeanConversion.copyDTO2Domain(user, com.nositer.hibernate.generated.domain.User.class);
			userDomain.setAvatarlocation(Global.USERPUBLICDIR + "/" + Global.DEFAULTUSERAVATAR);
			userDomain.setPassword(Encrypt.cryptPassword(userDomain.getPassword()));
			userDomain.setLastlogin(new Date());
			sess.save(userDomain);
			trx.commit();
			user = BeanConversion.copyDomain2DTO(userDomain, User.class);
			Application.setCurrentUser(user);
			createBasicFilesStructure();
			retval = true;
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

	private void createBasicFilesStructure() throws IOException {
		FileServiceImpl fileServiceImpl = new FileServiceImpl();
		fileServiceImpl.createDirsIfNecessary();
		File defaultUserAvatar = new File(getThreadLocalRequest().getSession().getServletContext().getRealPath(Global.PUBLICIMAGEDIR + "/" + Global.DEFAULTUSERAVATAR));		
		File publicImageDir = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, Application.getCurrentUser().getId()));
		FileUtils.copyFileToDirectory(defaultUserAvatar, publicImageDir);
		File publicREADME = new File(MessageFormat.format(Global.USERPUBLICDIRTEMPLATE, Application.getCurrentUser().getId()) + "/README.txt");
		FileUtils.writeStringToFile(publicREADME, "The public folder is viewable by the general public.  Your userid is: " + Application.getCurrentUser().getId() + 
				"\nAny files in your public directory can be accessed with a relative URL of " + Global.USER_URL_PREFIX + "/" + Application.getCurrentUser().getId() +
				"\nFor example, your default avatar is viewable at this location: " +
				Global.USER_URL_PREFIX + "/" + Application.getCurrentUser().getId() + Global.DEFAULTUSERAVATAR
		);		
		File privateREADME = new File(MessageFormat.format(Global.USERPRIVATEDIRTEMPLATE, Application.getCurrentUser().getId()) + "/README.txt");
		FileUtils.writeStringToFile(privateREADME, "The private folder is intended for you to upload files to private (not be viewable to anyone else");
	}
}

package com.nositer.server.service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.nositer.client.dto.generated.Blog;
import com.nositer.client.dto.generated.GroupPlusView;
import com.nositer.client.dto.generated.Groupmessage;
import com.nositer.client.dto.generated.Grouptopic;
import com.nositer.client.dto.generated.User;
import com.nositer.client.service.BlogService;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.shared.GWTException;
import com.nositer.shared.Global;
import com.nositer.util.BeanConversion;
import com.nositer.util.HTMLPurifier;
import com.nositer.webapp.Application;

@SuppressWarnings({"serial", "unchecked"})
public class BlogServiceImpl extends RemoteServiceServlet implements BlogService {


	public static void main(String[] args) {
		Global.DEBUG = true;
		BlogServiceImpl blogServiceImpl = new BlogServiceImpl();
		Blog blog = new Blog();
		blog.setDescription("This is a description");
		blog.setName("Hello World");
		blogServiceImpl.createBlog(blog);
		
	}
	
	
	@Override
	public Blog createBlog(Blog blog) throws GWTException {
		Blog retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			String description = blog.getDescription();
			blog.setDescription(HTMLPurifier.getCleanHTML(description));
			User user = Application.getCurrentUser();
			
			com.nositer.hibernate.generated.domain.Blog blogDomain = BeanConversion.copyDTO2Domain(blog, com.nositer.hibernate.generated.domain.Blog.class);
			blogDomain.setDisable(false);
			com.nositer.hibernate.generated.domain.User userDomain = BeanConversion.copyDTO2Domain(user, com.nositer.hibernate.generated.domain.User.class);

			blogDomain.setUser(userDomain);
			
			sess.save(blogDomain);
			
			trx.commit();
			retval = BeanConversion.copyDomain2DTO(blogDomain, Blog.class);
			
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
	public Blog getBlog(Integer blogid) throws GWTException {
		Blog retval = null;
		Session sess = HibernateUtil.getSession();
		Transaction trx = null;
		try {
			trx = sess.beginTransaction();		
			com.nositer.hibernate.generated.domain.Blog blogDomain = HibernateUtil.findByPrimaryKey(com.nositer.hibernate.generated.domain.Blog.class, blogid, sess);
			if (blogDomain == null) {
				throw new GWTException("No such blog id");
			}
			retval = BeanConversion.copyDomain2DTO(blogDomain, Blog.class);
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
	public ArrayList<Blog> getMyBlogs() throws GWTException {
		ArrayList<Blog> retval = new ArrayList<Blog>();
		Session sess = HibernateUtil.getSession();
		User user = null;
		Transaction trx = null;
		try {
			user = Application.getCurrentUser();
			trx = sess.beginTransaction();		
			List<com.nositer.hibernate.generated.domain.Blog> results = sess.createSQLQuery(
					SqlHelper.FINDMYBLOGS).		
					addEntity(com.nositer.hibernate.generated.domain.Blog.class).
					setInteger(Blog.Column.userid.toString(), 
							user.getId()
					).
					list();

			retval = BeanConversion.copyDomain2DTO(results, Blog.class);					
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

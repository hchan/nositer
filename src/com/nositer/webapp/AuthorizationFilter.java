package com.nositer.webapp;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nositer.client.widget.Location;
import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.Postalcode;
import com.nositer.hibernate.generated.domain.User;
import com.nositer.hibernate.generated.domain.Zipcode;
import com.nositer.server.service.ProfileServiceImpl;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;

@SuppressWarnings("unchecked")
public class AuthorizationFilter implements Filter {
	public static final String LOGIN_URL = "/login";
	public static final String PUBLIC_URL = "/public";
	public static final String REGISTER_URL = "/Register.html";
	public static final String REGISTER_PREFIX = "/register";
	public static final String USER_SESSION_KEY = "user";
	private static final ThreadLocal<HttpServletRequest> perThreadRequest = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<HttpServletResponse> perThreadResponse = new ThreadLocal<HttpServletResponse>();

	
	
	public static HttpServletRequest getThreadLocalRequest() {
		return perThreadRequest.get();
	}

	public static HttpServletResponse getThreadLocalResponse() {
		return perThreadResponse.get();
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		perThreadRequest.set(request);
		perThreadResponse.set(response);
		try {
			String urlStr = request.getRequestURI();
			if (!(urlStr.startsWith(PUBLIC_URL))) {		
				if (urlStr.equals(LOGIN_URL)) {
					doLoginRequest(request, response, chain);
				} else if (urlStr.startsWith(REGISTER_PREFIX) || urlStr.equals(REGISTER_URL)) {
					doRegisterRequest(request, response, chain);
				} else {
					doSessionCheck(request, response, chain);
				}
			} else {
				chain.doFilter(request, response);
			}
		} finally {
			perThreadRequest.set(null);
			perThreadResponse.set(null);
		}
	}

	private void doRegisterRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		chain.doFilter(request, response);
	}


	private void doSessionCheck(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		
		com.nositer.client.dto.generated.User userDTO = null;
		try {
			userDTO = Application.getCurrentUser();
		} catch (Exception e) {}
		if (userDTO == null) {
			forwardToLoginPage(request, response, chain);
		}
		else {
			chain.doFilter(request, response);
		}
	}

	private void doLoginRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login != null) {
			Session session = HibernateUtil.getSession();
			Transaction trx = null;
			try {
				trx = session.beginTransaction();				
				List<User> results = session.createSQLQuery(SqlHelper.FINDUSERBYLOGIN).addEntity(User.class).setString(com.nositer.client.dto.generated.User.ColumnType.login.toString(), login).list();
				if (results.size() == 0) {
					doInvalidLoginPassword(request, response, chain);						
				} else {
					User userDomain = results.get(0);
					if (userDomain.getPassword().equals(Encrypt.cryptPassword(password))) {
						ProfileServiceImpl profileServiceImpl = new ProfileServiceImpl();
						com.nositer.client.dto.generated.User userDTO = profileServiceImpl.getCurrentUser(userDomain);						
						Application.setCurrentUser(userDTO);
						userDomain.setLastlogin(new Date());
						session.update(userDomain);
						doSuccessfulLogin(request, response, chain);							
					} else {
						doInvalidLoginPassword(request, response, chain);			
					}						
				}
				trx.commit();
			} catch (Exception e) {
				HibernateUtil.rollbackTransaction(trx);
				Application.log.error("", e);
			} finally {
				HibernateUtil.closeSession(session);
			}
		} else {
			forwardToLoginPage(request, response, chain);		
		}
	}

	private void forwardToLoginPage (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	private void doSuccessfulLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		response.getWriter().print("<SUCCESS>Login successful</SUCCESS>");
	}
	private void doInvalidLoginPassword (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {		
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"utf-8\" ?><RESULTS><ERRORS>Invalid login/password</ERRORS></RESULTS>\n");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

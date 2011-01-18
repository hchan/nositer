package com.nositer.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SqlHelper;
import com.nositer.hibernate.generated.domain.User;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;

public class AuthorizationFilter implements Filter {
	public static final String LOGIN_URL = "/login";
	public static final String PUBLIC_URL = "/public";
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		perThreadRequest.set(req);
		perThreadResponse.set(resp);
		try {
			String urlStr = req.getRequestURI();
			if (!(urlStr.startsWith(PUBLIC_URL))) {		
				if (urlStr.equals(LOGIN_URL)) {
					doLoginRequest(req, resp, chain);
				} else {
					doSessionCheck(req, resp, chain);
				}
			} else {
				chain.doFilter(request, response);
			}
		} finally {
			perThreadRequest.set(null);
			perThreadResponse.set(null);
		}
	}

	private void doSessionCheck(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		com.nositer.client.dto.generated.User userDTO = null;
		try {
			userDTO = (com.nositer.client.dto.generated.User)session.getAttribute(USER_SESSION_KEY);
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
			try {
				List<User> results = session.createSQLQuery(SqlHelper.FINDUSERBYEMAIL.sql()).addEntity(User.class).setString("EMAIL", login).list();
				if (results.size() == 0) {
					doInvalidLoginPassword(request, response, chain);						
				} else {
					User userDomain = results.get(0);
					if (userDomain.getPassword().equals(Encrypt.cryptPassword(password))) {
						com.nositer.client.dto.generated.User userDTO = BeanConversion.copyDomain2DTO(userDomain, com.nositer.client.dto.generated.User.class);
						request.getSession().setAttribute(USER_SESSION_KEY, userDTO);
						//chain.doFilter(request, response);
						//request.getRequestDispatcher("/Nositer.html").forward(request, response);
						//resp.sendRedirect("/Nositer.html");
						response.getWriter().print("<SUCCESS>Login successful</SUCCESS>");
					} else {
						doInvalidLoginPassword(request, response, chain);			
					}						
				}
			} catch (Exception e) {
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
	
	private void doInvalidLoginPassword (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		//ArrayList<String> errors = new ArrayList<String>();
		//errors.add("Invalid login/password");
		//request.setAttribute("errors", errors);
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"utf-8\" ?><RESULTS><ERRORS>Invalid login/password</ERRORS></RESULTS>\n");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

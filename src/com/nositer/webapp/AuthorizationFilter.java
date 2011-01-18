package com.nositer.webapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import org.hibernate.SessionFactory;

import com.nositer.hibernate.HibernateUtil;
import com.nositer.hibernate.SQLHashMap;
import com.nositer.hibernate.generated.domain.User;
import com.nositer.util.BeanConversion;
import com.nositer.util.Encrypt;

public class AuthorizationFilter implements Filter {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
	throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;

		perThreadRequest.set(req);
		perThreadResponse.set(resp);

		try {

			String urlStr = req.getRequestURI();
			//URL url = new URL(urlStr);
			//String urlPath = url.getPath();
			if (!(urlStr.startsWith("/public"))) {		
				if (urlStr.equals("/login")) {
					String login = request.getParameter("login");
					String password = request.getParameter("password");
					if (login != null) {
						Session session = HibernateUtil.getSession();
						try {
							List<User> results = session.createSQLQuery(SQLHashMap.get("findUserByEmail")).addEntity(User.class).setString("EMAIL", login).list();
							if (results.size() == 0) {
								doInvalidLoginPassword(req, (HttpServletResponse) response, chain);						
							} else {
								User userDomain = results.get(0);
								if (userDomain.getPassword().equals(Encrypt.cryptPassword(password))) {
									com.nositer.client.dto.generated.User userDTO = BeanConversion.copyDomain2DTO(userDomain, com.nositer.client.dto.generated.User.class);
									req.getSession().setAttribute("user", userDTO);
									//chain.doFilter(request, response);
									//request.getRequestDispatcher("/Nositer.html").forward(request, response);
									//resp.sendRedirect("/Nositer.html");
									response.getWriter().print("<SUCCESS>Login successful</SUCCESS>");
								} else {
									doInvalidLoginPassword(req, (HttpServletResponse) response, chain);			
								}						
							}
						} catch (Exception e) {
							Application.log.error("", e);
						} finally {
							HibernateUtil.closeSession(session);
						}
					} else {
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				} else {

					HttpSession session = req.getSession(false);
					com.nositer.client.dto.generated.User userDTO = null;
					try {
						userDTO = (com.nositer.client.dto.generated.User)session.getAttribute("user");
					} catch (Exception e) {}

					if (userDTO == null) {
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
					else {
						chain.doFilter(request, response);
					}
				}
			} else {
				chain.doFilter(request, response);
			}
		} finally {
			perThreadRequest.set(null);
			perThreadResponse.set(null);
		}
	}

	public void doInvalidLoginPassword (HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
		//ArrayList<String> errors = new ArrayList<String>();
		//errors.add("Invalid login/password");
		//request.setAttribute("errors", errors);
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
		response.getWriter().print("<?xml version=\"1.0\" encoding=\"utf-8\" ?><RESULTS><ERRORS>Invalid login/password</ERRORS></RESULTS>\n");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

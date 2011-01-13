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
import com.nositer.util.Encrypt;

public class AuthorizationFilter implements Filter{

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
							User user = results.get(0);
							if (user.getPassword().equals(Encrypt.cryptPassword(password))) {
								req.getSession().setAttribute("user", user);
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
				User user = null;
				try {
					user = (User)session.getAttribute("user");
				} catch (Exception e) {}

				if (user == null) {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
				else {
					chain.doFilter(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void doInvalidLoginPassword (HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
		//ArrayList<String> errors = new ArrayList<String>();
		//errors.add("Invalid login/password");
		//request.setAttribute("errors", errors);
		//request.getRequestDispatcher("/login.jsp").forward(request, response);
		response.getWriter().print("<RESULT><ERRORS>Invalid login/password</ERRORS></RESULT>");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

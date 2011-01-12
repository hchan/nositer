package com.nositer.webapp;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

		String urlStr = req.getRequestURI();
		//URL url = new URL(urlStr);
		//String urlPath = url.getPath();
		if (!(urlStr.startsWith("/public"))) {		
			if (urlStr.equals("/login")) {

			} else {

				HttpSession session = req.getSession(false);
				String currentUser = null;
				try {
					currentUser = (String)session.getAttribute("user");
				} catch (Exception e) {}

				if (currentUser == null) {
					request.getRequestDispatcher("/login.html").forward(request, response);
				}
				else {
					System.out.println("You are logged in");
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

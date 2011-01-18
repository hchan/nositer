package com.nositer.webapp;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nositer.client.dto.generated.User;


public class AccessLogFilter implements Filter {
	private static final ThreadLocal<HttpServletRequest> perThreadRequest = new ThreadLocal<HttpServletRequest>();
	private static final ThreadLocal<HttpServletResponse> perThreadResponse = new ThreadLocal<HttpServletResponse>();

	public static HttpServletRequest getThreadLocalRequest() {
		return perThreadRequest.get();
	}

	public static HttpServletResponse getThreadLocalResponse() {
		return perThreadResponse.get();
	}

	public static Logger log = Logger.getLogger("nositer_access");

	public void destroy() {
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
	throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;

		perThreadRequest.set(httpServletRequest);
		perThreadResponse.set(httpServletResponse);

		try {
			String nvp = "";
			for (Enumeration<String> e = httpServletRequest.getParameterNames(); e.hasMoreElements();) {				
				String name = e.nextElement();
				String value = request.getParameter(name);
				// don't log passwords
				if (name.equals("password")) {
					value = "******";
				}
				nvp += name + "=" + value + "\n\t";
			}
			String userID = "";
			try {
				User user = (User) httpServletRequest.getSession().getAttribute("user");
				userID = user.getEmail();
			} catch (Exception e) {			
			}
			AccessLogFilter.log.info(request.getRemoteAddr() + " userID:" + userID +
					"\n\t" + httpServletRequest.getRequestURL() + 
					"\n\t" + httpServletRequest.getMethod() + " Querystring: " + httpServletRequest.getQueryString() + 
					"\n\t" + nvp				
			);
			chain.doFilter(request, response);
		} finally {
			perThreadRequest.set(null);
			perThreadResponse.set(null);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}



}



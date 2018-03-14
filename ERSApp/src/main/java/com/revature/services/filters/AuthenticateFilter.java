package com.revature.services.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class AuthenticateFilter
 */
public class AuthenticateFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(AuthenticateFilter.class);

	/**
	 * Default constructor.
	 */
	public AuthenticateFilter() {
	}

	/**
	 */
	public void destroy() {
	}

	/**
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("user") == null) { // checking whether the session exists
			logger.info("Unauthorized access request");
			res.sendRedirect(req.getContextPath() + "/login");
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.debug("Initialized Authentication Filter");

	}

}

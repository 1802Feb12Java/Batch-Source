package com.revature.servlets.filters;

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

import com.revature.beans.User;

/**
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(AdminFilter.class);

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		User u = (User) session.getAttribute("user");

		if (u.getUserRole().toLowerCase().equals("manager") || u.getUserRole().toLowerCase().equals("admin")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			logger.debug("Unauthorized access request for admin pages");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

package com.revature.servlets.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
 * Class to filter logged in users and logged out
 */
public class SessionFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(SessionFilter.class);

	private ArrayList<String> urlList;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		logger.info("doFilter called");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		boolean allowedRequest = false;

		if (urlList.contains(url)) {
			allowedRequest = true;
		}

		if (!allowedRequest) {
			HttpSession session = request.getSession(false);
			if (null == session) {
				response.sendRedirect("login.html");
			}
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {

		logger.info("filter inited");

		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());

		}
	}
}

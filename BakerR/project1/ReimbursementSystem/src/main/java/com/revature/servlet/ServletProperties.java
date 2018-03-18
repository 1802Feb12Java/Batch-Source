package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ServletProperties {
	private static ServletProperties instance = null;
	private Properties props;
	
	private ServletProperties(String propFile) throws IOException {
		Properties p = new Properties();
		
		try(InputStream is = getClass().getClassLoader().getResourceAsStream(propFile)) {
			p.load(is);
			props = p;
		}
	}
	
	/**
	 * Gets servlet properties from configuration properties file "servlet.properties".
	 * @return Properties from the servlet properties file. Otherwise, null is returned.
	 */
	public static Properties getProperties() {
		if(instance == null) {
			try {
				instance = new ServletProperties("servlet.properties");
			} catch(IOException ex) {}
		}
		
		return instance == null ? null : instance.props;
	}
}

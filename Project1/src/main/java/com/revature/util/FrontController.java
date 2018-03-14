package com.revature.util;

import javax.servlet.http.HttpServletResponse;

public class FrontController {
	public static void addHeader(HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers",
                "Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
	}
}

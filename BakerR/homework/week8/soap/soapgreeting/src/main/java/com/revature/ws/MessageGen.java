package com.revature.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MessageGen {
	
	@WebMethod
	public String getMessage();
}

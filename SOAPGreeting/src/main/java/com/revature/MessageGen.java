package com.revature;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MessageGen {
	
	@WebMethod
	String getMessage();

}

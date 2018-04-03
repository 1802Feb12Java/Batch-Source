package com.revature;
import javax.jws.WebService;

@WebService(endpointInterface = "com.revature.MessageGen",
serviceName="messageGenService")
public class MessageGenImpl implements MessageGen {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "test";
	}

}

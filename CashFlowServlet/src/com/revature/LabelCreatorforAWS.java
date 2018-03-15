package com.revature;

import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.concurrent.ThreadLocalRandom;


public class LabelCreatorforAWS extends Operation {
	
	//TODO: refactor to read and write from a file
	static int x=ThreadLocalRandom.current().nextInt(0, 200 + 1);
	@Override
	String handle(JsonObject request, Connection connection) 
	{
		Bean bean=new Bean();
		bean.label=++x;
		String json=new Gson().toJson(bean);
		return json;
	}
	
	class Bean
	{
		int label;
	}

}

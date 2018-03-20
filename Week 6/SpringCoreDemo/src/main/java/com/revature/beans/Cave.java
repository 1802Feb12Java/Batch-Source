package com.revature.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Cave implements InitializingBean, DisposableBean {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Cave(){
		super();
	}
	public void defaultInit(){
		System.out.println("(custom) default init from cave");
	}
	public void defaultDestroy(){
		System.out.println("(custom) default destroy from cave");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties set from cave");
	}
	
	@Override 
	public void destroy(){
		System.out.println("destroy from cave");
	}
	

}

package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BearWithAutomagic extends Bear {
	
	@Autowired //@Inject is pretty much synonymous
	private Cave cave;
	
	@Override
	public Cave getCave(){
		return this.cave;
	}
	
	@Override
	public void methodInBear(){
		System.out.println("method in BearWithAutomagic. this bear is: "+this.toString());
		System.out.println("cave: "+cave.getName());
	}
	
	public void defaultInit(){
		System.out.println("default init from bearWithAutomagic");
	}
	public void defaultDestroy(){
		System.out.println("default destroy from bearWithAutomagic");
	}

}

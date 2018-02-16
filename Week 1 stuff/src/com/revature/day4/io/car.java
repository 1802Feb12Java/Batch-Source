package com.revature.day4.io;

import java.io.Serializable;

public class car implements Serializable {

	
	private static long getSerialversionid = 1L;
	
	
	
	private String make;
	private String model;
	private String color;
	public car() {
		super();
		// TODO Auto-generated constructor stub
	}
	public car(String make, String model, String color) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
	
	
	
}

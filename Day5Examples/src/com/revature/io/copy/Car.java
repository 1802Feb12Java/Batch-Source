package com.revature.io.copy;

import java.io.Serializable;

public class Car implements Serializable {


	private static final long serialVersionUID = 1L;
	private String make;
	private String model;
	private String color;
	
	
	public Car() {
		super();
	}

	public Car(String make, String model, String color) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", color=" + color + "]";
	}
	
	
}

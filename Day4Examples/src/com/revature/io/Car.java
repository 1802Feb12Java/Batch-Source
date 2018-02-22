package com.revature.io;

public class Car implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String model;
	private String make;
	private String color;

	public Car() {

	}

	public Car(String model, String make, String color) {
		super();
		this.model = model;
		this.make = make;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", make=" + make + ", color=" + color + "]";
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

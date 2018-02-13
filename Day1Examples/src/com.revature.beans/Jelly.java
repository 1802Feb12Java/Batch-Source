package com.revature.beans;

//POJO - Plain Ol' Java Object
//private variables
//c'tor
//getters & setters
//toString()

public class Jelly {
	private String color;
	private String shape;
	private String size;
	private String flavor;
	private int id;

	public Jelly() {}
	public String getColor() {
		return color;
	}
	public String getShape() {
		return shape;
	}
	public String getSize() {
		return size;
	}
	public String getFlavor() {
		return flavor;
	}
	public int getId() {
		return id;
	}

	public void setColor(String c) {
		this.color = c;
	}
	public void setShape(String s) {
		this.shape = s;
	}
	public String setSize(String s) {
		this.size = s;
	}
	public String setFlavor(String f) {
		this.flavor = f;
	}
	public int setId(int i) {
		this.id = i;
	}

	public String toString() {
		
	}
}
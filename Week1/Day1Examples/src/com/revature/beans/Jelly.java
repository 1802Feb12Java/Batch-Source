package com.revature.beans;

//POJO-Plain Ol' Java Object:
//private variables
//constructors
//getters and setters
// toString()

public class Jelly {

	private String color;
	private String shape;
	private String size;
	private String flavor;
	private int id;
	
	public Jelly() {	
	
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Jelly [color=" + color + ", shape=" + shape + ", size=" + size + ", flavor=" + flavor + ", id=" + id
				+ "]";
	}
	
}
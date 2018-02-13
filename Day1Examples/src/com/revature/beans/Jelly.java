package com.revature.beans;

//This is a bean
//Plain old Java Object-POJO:
//private variables
//constructors
//getters and setters
//toString()

public class Jelly {

	private String color;
	private String shape;
	private String texture;
	private String flavor;
	private int id;
	
	public Jelly(){
		
		
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

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
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
		return "Jelly [color=" + color + ", shape=" + shape + ", texture="
				+ texture + ", flavor=" + flavor + ", id=" + id + "]";
	}
	
	
	
}

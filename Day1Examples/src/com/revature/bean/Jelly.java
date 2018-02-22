package com.revature.bean;

//POJO - plain old java obj: private vars, constructors, getters and setters, toString()
public class Jelly {

	private String color;
	private String shape;
	private String size;
	private String flavor;
	private int id;
	
	public Jelly()
	{
		
	}

	public Jelly(String color, String shape, String size, String flavor, int id) {
		super();
		this.color = color;
		this.shape = shape;
		this.size = size;
		this.flavor = flavor;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Jelly [color=" + color + ", shape=" + shape + ", size=" + size + ", flavor=" + flavor + ", id=" + id
				+ "]";
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
		return size;
	}

	public void setTexture(String texture) {
		this.size = texture;
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
	
}

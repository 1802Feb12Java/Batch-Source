package com.revature.beans;



/*POJO-Plain Ol' Java Object
*private variables
*constructors
*getters and setters
*toString()
*/
public class Jelly {
	private String color;
	private String shape;
	private String Size;
	private String Flavor;
	private int id;
	
	public Jelly ( ) {
		
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
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getFlavor() {
		return Flavor;
	}

	public void setFlavor(String flavor) {
		Flavor = flavor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Jelly [color=" + color + ", shape=" + shape + ", Size=" + Size + ", Flavor=" + Flavor + ", id=" + id
				+ "]";
	}
	
	
	
}

package com.revature.beans;

import java.io.Serializable;

public class Guitar implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String color;
	private double cost;
	public Guitar() {
		super();
	}
	public Guitar(int id, String name, String color, double cost) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.cost = cost;
	}
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Guitar [id=" + id + ", name=" + name + ", color=" + color + ", cost=" + cost + "]";
	}

	

}

	

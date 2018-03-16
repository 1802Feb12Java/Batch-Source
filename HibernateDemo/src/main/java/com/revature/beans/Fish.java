package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // @DynamicUpdate
// @Table(appliesTo = "FISHES")
public class Fish implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// @GeneratedValue(generator = "increment")
	// @GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "F_ID")
	private int id;

	@Column(name = "NAME")
	private String name;
	@Column(name = "COLOR")
	private String color;
	@Column(name = "AGE")
	private int age;

	public Fish() {
	}

	// NO ID IN CONSTRUCTOR
	public Fish(String name, String color, int age) {
		super();
		this.name = name;
		this.color = color;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Fish [id=" + id + ", name=" + name + ", color=" + color + ", age=" + age + "]";
	}

}

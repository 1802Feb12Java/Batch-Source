package com.revature.servlet;

public class Person {

	private String name;
	private Integer id;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}
		
}

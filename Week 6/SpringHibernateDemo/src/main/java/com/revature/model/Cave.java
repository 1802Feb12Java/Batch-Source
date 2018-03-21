package com.revature.model;

import javax.persistence.*;

@Entity
@Table(name = "CAVE")
public class Cave {
	private Integer id;
	private String name;

	@Id
	@Column(name="CAVE_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="CAVE_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cave(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cave [id=" + id + ", name=" + name + "]";
	}

}

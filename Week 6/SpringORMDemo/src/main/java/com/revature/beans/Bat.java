package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@DiscriminatorValue("bat")
public class Bat extends Animal implements Serializable {
	
	public Bat(){
		super();
	}
	
	public Bat(String name, Cave cave) {
		super(name,cave);
	}
/*
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="batSequence")
	@SequenceGenerator(allocationSize=1,name="batSequence",sequenceName="SQ_BAT_PK")
	@Column(name="BAT_ID")
	private int id;
	
	@Column(name="BAT_NAME")
	private String name;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="CAVE_ID")
	private Cave cave;

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

	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}
	*/
	@Override
	public String toString() {
		return "Bat [id=" + this.getId() + ", name=" + this.getName() + ", cave=" + this.getCave() + "]";
	}
}

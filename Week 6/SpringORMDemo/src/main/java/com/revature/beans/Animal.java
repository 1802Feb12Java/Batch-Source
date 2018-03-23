package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@Table(name="ANIMAL")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="SPECIES",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="animal")
public class Animal implements Serializable {
	
	public Animal(){
		super();
	}
	
	public Animal(String name, Cave cave) {
		this.name = name;
		this.cave = cave;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AnimalSequence")
	@SequenceGenerator(allocationSize=1,name="AnimalSequence",sequenceName="SQ_ANIMAL_PK")
	@Column(name="ANIMAL_ID")
	private int id;
	
	@Column(name="ANIMAL_NAME")
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

	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
}

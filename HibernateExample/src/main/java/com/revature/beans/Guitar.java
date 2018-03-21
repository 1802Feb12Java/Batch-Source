package com.revature.beans;
<<<<<<< HEAD
=======

import java.io.Serializable;

>>>>>>> KnightenM
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
<<<<<<< HEAD

@Entity
@Table(name="GUITAR")
public class Guitar {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="guitSeq")
	@SequenceGenerator(allocationSize=1, name="guitSeq", sequenceName="GUIT_SEQ")
	@Column(name="GUITAR_ID")
	private int id;
	
	@Column(name="MAKE")
=======
import javax.validation.constraints.Size;

@Entity
@Table(name="GUITAR")
public class Guitar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="guitSeq")
	@SequenceGenerator(allocationSize=1, name="guitSeq", sequenceName="GUIT_SEQ")
	@Column(name="ID")
	private int id;
	
	@Column(name="MAKE")
	@Size(min = 4,
	max = 255,
	message = "Make is required, maximum 255 characters")
>>>>>>> KnightenM
	private String make;
	
	@Column(name="MODEL")
	private String model;
	
	@Column(name="NUMOFSTRINGS")
	private int numOfStrings;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="COST")
	private double cost;
	
	public Guitar() {
		super();
		// TODO Auto-generated constructor stub
	}
<<<<<<< HEAD
	
	
	public Guitar(String make, String model, int numOfStrings, String color, double cost) {
		super();
=======
	public Guitar(  String make, String model, int numOfStrings, String color, double cost) {
		super();
	
>>>>>>> KnightenM
		this.make = make;
		this.model = model;
		this.numOfStrings = numOfStrings;
		this.color = color;
		this.cost = cost;
	}
<<<<<<< HEAD



=======
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
>>>>>>> KnightenM
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getNumOfStrings() {
		return numOfStrings;
	}
	public void setNumOfStrings(int numOfStrings) {
		this.numOfStrings = numOfStrings;
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
<<<<<<< HEAD
=======
	@Override
	public String toString() {
		return "Guitar [id=" + id + ", make=" + make + ", model=" + model + ", numOfStrings=" + numOfStrings
				+ ", color=" + color + ", cost=" + cost + "]";
	}
	
>>>>>>> KnightenM
}

package com.revature.beans;

import javax.persistence.*;


@Entity
@Table(name="Guitar")
public class Guitar {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="guitSeq")
	@SequenceGenerator(allocationSize=1, name="guitSeq", sequenceName="GUIT_SEQ")
	@Column(name="GUITAR_ID")
	private int id;
	@Column(name="GUITAR_MODEL")
	private String model;
	@Column(name="GUITAR_MAKE")
	private String make;
	@Column(name="GUITAR_NUM_STRINGS")
	private int numOfStrings;
	@Column(name="GUITAR_COST")
	private double cost;
	
	public Guitar(String model, String make, int numOfStrings, double cost) {
		super();
		this.model = model;
		this.make = make;
		this.numOfStrings = numOfStrings;
		this.cost = cost;
	}
	public Guitar() {
		super();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getNumnOfStrings() {
		return numOfStrings;
	}

	public void setNumnOfStrings(int numnOfStrings) {
		this.numOfStrings = numnOfStrings;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Guitar [id=" + id + ", model=" + model + ", make=" + make + ", numnOfStrings=" + numOfStrings
				+ ", cost=" + cost + "]";
	}
	
	

}

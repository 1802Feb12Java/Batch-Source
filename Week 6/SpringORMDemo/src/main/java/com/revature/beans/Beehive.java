package com.revature.beans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="BEEHIVE")
public class Beehive implements Serializable {
	
	public Beehive(int weight) {
		super();
		this.weight = weight;
	}
	public Beehive(int id, int weight) {
		super();
		this.id = id;
		this.weight = weight;
	}
	public Beehive() {
		super();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="beehiveSequence")
	@SequenceGenerator(allocationSize=1,name="beehiveSequence",sequenceName="SQ_BEEHIVE_PK")
	@Column(name="BEEHIVE_ID")
	private int id;
	@Column(name="BEEHIVE_WEIGHT")
	private int weight;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Beehive [id=" + id + ", weight=" + weight + "]";
	}
	

}

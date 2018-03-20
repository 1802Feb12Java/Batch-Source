package com.revature.beans;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@NamedQueries({
	@NamedQuery(
		name="findBearByName",
		query="from Bear where name = :namevar"
	)
})

@Entity
@Table(name = "BEAR")
public class Bear {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bearSeq")
	@SequenceGenerator(allocationSize=1, name="bearSeq", sequenceName="BEAR_SEQ")
	@Column(name="BEAR_ID")
	private int id;

	@Column(name="BEAR_NAME")
	private String name;
	
	@Column(name="BEAR_AGE")
	private int age;
	
	@Column(name="BEAR_WEIGHT")
	private int weight;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BEAR_CAVE")
	Cave cave;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Bear() {
		
	}
	
	public Bear(String name, int age, int weight, Cave cave) {
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.cave = cave;
	}

	@Override
	public String toString() {
		return "Bear [name=" + name + ", age=" + age + ", weight=" + weight + ", cave=" + cave.getName() + "]";
	}
}

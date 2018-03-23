package com.revature.beans;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "findBearByCave", query = "from Animal where cave=:caveVar and species = bear") })

@XmlRootElement
@Entity
@DiscriminatorValue("bear")
public class Bear extends Animal implements Serializable {

	

	public Bear(String name, Cave cave, BearType bearType, int weight, Date birthdate) {
		super(name,cave);
		this.bearType = bearType;
		this.weight = weight;
		this.birthdate = birthdate;
	}

	public Bear() {
		super();
	}

/*	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bearSequence")
	@SequenceGenerator(allocationSize = 1, name = "bearSequence", sequenceName = "SQ_BEAR_PK")
	@Column(name = "BEAR_ID")
	private int id;
	@Column(name = "BEAR_NAME")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAVE_ID")
	private Cave cave;*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BEAR_TYPE_ID")
	private BearType bearType;
	@Column(name = "BEAR_WEIGHT")
	private int weight;
	@Column(name = "BEAR_BIRTHDATE")
	private Date birthdate;

	/*@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Cave getCave() {
		return cave;
	}

	public void setCave(Cave cave) {
		this.cave = cave;
	}
*/
	@XmlElement
	public BearType getBearType() {
		return bearType;
	}

	public void setBearType(BearType bearType) {
		this.bearType = bearType;
	}

	@XmlElement
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@XmlElement
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Bear [id=" + this.getId() + ", name=" + this.getName() + ", cave=" + this.getCave() + ", bearType=" + bearType + ", weight=" + weight
				+ ", birthdate=" + birthdate + "]";
	}

}

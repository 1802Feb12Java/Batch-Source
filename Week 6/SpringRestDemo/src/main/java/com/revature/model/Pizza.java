package com.revature.model;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pizza")
@XmlAccessorType(XmlAccessType.NONE)
public class Pizza {
	@XmlElementWrapper
	@XmlElement
	private String[] toppings;
	@XmlAttribute(name="id")
	private Long slices;
	@XmlElement
	private String sauce;

	public String[] getToppings() {
		return toppings;
	}

	public void setToppings(String[] toppings) {
		this.toppings = toppings;
	}

	public Long getSlices() {
		return slices;
	}

	public void setSlices(Long slices) {
		this.slices = slices;
	}

	public String getSauce() {
		return sauce;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public Pizza(String[] toppings, Long slices, String sauce) {
		super();
		this.toppings = toppings;
		this.slices = slices;
		this.sauce = sauce;
	}

	public Pizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Pizza [toppings=" + Arrays.toString(toppings) + ", slices=" + slices + ", sauce=" + sauce + "]";
	}

}

package com.revature.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BAT")
@AttributeOverrides({
	@AttributeOverride(name="id", column=@Column(name="BAT_ID")),
	@AttributeOverride(name="name", column=@Column(name="BAT_NAME")),
	@AttributeOverride(name="cave", column=@Column(name="BAT_CAVE"))
})
public class Bat extends Animal{
	@Column(name="WING_SPAN")
	private int wingspan;
	
	public Bat(String name, int wingspan, Cave cave) {
		this.name = name;
		this.wingspan = wingspan;
		this.cave = cave;
	}
}

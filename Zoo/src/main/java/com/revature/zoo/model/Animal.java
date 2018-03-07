package com.revature.zoo.model;

public class Animal {
	private int animalID;
	private String name, species;
	public Animal() {
	}
	public Animal(int animalID, String name, String species) {
		super();
		this.animalID = animalID;
		this.name = name;
		this.species = species;
	}
	public Animal(String name, String species) {
		super();
		this.animalID = 0;
		this.name = name;
		this.species = species;
	}
	public int getAnimalID() {
		return animalID;
	}
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", name=" + name + ", species=" + species + "]";
	}

	
}

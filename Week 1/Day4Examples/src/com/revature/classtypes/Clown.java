package com.revature.classtypes;

<<<<<<< HEAD
public class Clown extends Fishes{
	private int fins;
	private String name;
	
	public Clown() {
		super();
	}
	public Clown(String name, String type) {
		super();
		this.fins = this.numOfFins;
		this.name = name;
		this.type = type;
	}
	private String type;
=======
public class Clown extends Fishes {
	private int fins=this.numofFins;
	private String name;
	private String type;
	
	public Clown() {
		super();
		// TODO Auto-generated constructor stub
	}
	
>>>>>>> KnightenM

	public int getFins() {
		return fins;
	}

<<<<<<< HEAD
=======

>>>>>>> KnightenM
	public void setFins(int fins) {
		this.fins = fins;
	}

<<<<<<< HEAD
=======

>>>>>>> KnightenM
	public String getName() {
		return name;
	}

<<<<<<< HEAD
=======

>>>>>>> KnightenM
	public void setName(String name) {
		this.name = name;
	}

<<<<<<< HEAD
	public Clown(int fins, String name) {
		super();
		this.fins = fins;
		this.name = name;
	}

=======

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Clown( String name, String type) {
		super();
	
		this.name = name;
		this.type = type;
	}


>>>>>>> KnightenM
	@Override
	public void typeOFish(String[] a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void swim(int speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}

}

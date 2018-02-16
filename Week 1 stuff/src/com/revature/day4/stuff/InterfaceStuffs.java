package com.revature.day4.stuff;




public class InterfaceStuffs extends AbstractClassStuff implements InterfaceFun{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfaceStuffs guppy = new InterfaceStuffs(2, "Jerry", "Guppy");
		
		guppy.wayFinder();
	}
	
	
	private int fins;
	private String name;
	private String type;
	
	public InterfaceStuffs(int fins, String name, String type) {
		super();
		this.fins = fins;
		this.name = name;
		this.type = type;
	}

	@Override
	public void typeOFish(String[] args) {
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

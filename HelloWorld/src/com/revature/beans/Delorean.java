package com.revature.beans;

public class Delorean implements Car{
	
	private double MPG;
	
	private int speed;
	
	private int gear;
	
	public double getMPG(){
		
		return MPG;
		
	}
	
	public int getSpeed(){
		
		return speed;
		
	}
	
	public void drive(int speed){
		
		
		this.speed = speed;
		
	}
	
	public void changeGears(int gear){
		
		this.gear = gear;
		
	}

}

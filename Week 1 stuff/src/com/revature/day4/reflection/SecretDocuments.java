package com.revature.day4.reflection;

public class SecretDocuments {

	private int id;
	private String title;
	private String owner;
	public static int deaths=0;
	
	
	public SecretDocuments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SecretDocuments(int id, String title, String owner) {
		super();
		this.id = id;
		this.title = title;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public static int getDeaths() {
		return deaths;
	}


	public static void setDeaths(int deaths) {
		SecretDocuments.deaths = deaths;
	}


	
}

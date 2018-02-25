package com.revature.reflection;

public class SecretDocuments {
	private int id;
	private String title;
	private String owner;
	public static int deaths = 0;
	public SecretDocuments() {
		super();
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
	@Override
	public String toString() {
		return "SecretDocuments [id=" + id + ", title=" + title + ", owner=" + owner + "]";
	}
	
}

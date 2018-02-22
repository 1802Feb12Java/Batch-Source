package com.revature.reflection;

public class SecretDocument {
	private int id;
	private String title;
	private String owner;
	public static int deaths = 0;

	public SecretDocument() {

	}

	public SecretDocument(int id, String title, String owner) {
		super();
		this.id = id;
		this.title = title;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "SecretDocument [id=" + id + ", title=" + title + ", owner=" + owner + "]";
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
		SecretDocument.deaths = deaths;
	}

}

package com.revature.garbage;

public class SecretDocuments
{
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecretDocuments other = (SecretDocuments) obj;
		if (id != other.id)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SecretDocuments [id=" + id + ", title=" + title + ", owner=" + owner + "]";
	}
	@Override
	protected void finalize() throws Throwable {
		//System.out.println("destroying : "+toString());
		deaths++;
		super.finalize();
	}

	
}

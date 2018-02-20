package com.revature.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = -8203922734648454411L;
	
	// Account info
	private Long id;
	private byte[] pwHash;
	
	// Personal info
	private String firstName, lastName;
	
	public User(long id, String firstName, String lastName) {
		id = 0L;
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		pwHash = new byte[0];
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getPwHash() {
		return pwHash;
	}

	public void setPwHash(byte[] pwHash) {
		this.pwHash = pwHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
}

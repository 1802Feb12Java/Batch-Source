package com.revature.ers.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursements {
	private int r_id;
	private double r_amount;
	private String description;
	private Blob r_receipt;
	private Timestamp r_submitted;
	private Timestamp r_resolved;
	
	private int u_id_author;
	private String author_firstname;
	private String author_lastname;
	
	private int u_id_resolver;
	private String resolver_firstname;
	private String resolver_lastname;
	
	
	private int rt_type;
	private int rt_status;
	
	public Reimbursements() {}

	/**
	 * @param r_id
	 * @param r_amount
	 * @param description
	 * @param r_receipt
	 * @param r_submitted
	 * @param r_resolved
	 * @param u_id_author
	 * @param u_id_resolver
	 * @param rt_type
	 * @param rt_status
	 */
	public Reimbursements(int r_id, double r_amount, String description, Blob r_receipt, Timestamp r_submitted,
			Timestamp r_resolved, int u_id_author, int u_id_resolver, int rt_type, int rt_status) {
		super();
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.description = description;
		this.r_receipt = r_receipt;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.u_id_author = u_id_author;
		this.u_id_resolver = u_id_resolver;
		this.rt_type = rt_type;
		this.rt_status = rt_status;
	}

	/**
	 * @param r_id
	 * @param r_amount
	 * @param description
	 * @param r_receipt
	 * @param r_submitted
	 * @param r_resolved
	 * @param u_id_author
	 * @param author_firstname
	 * @param author_lastname
	 * @param u_id_resolver
	 * @param resolver_firstname
	 * @param resolver_lastname
	 * @param rt_type
	 * @param rt_status
	 */
	public Reimbursements(int r_id, double r_amount, String description, Blob r_receipt, Timestamp r_submitted,
			Timestamp r_resolved, int u_id_author, String author_firstname, String author_lastname, int u_id_resolver,
			String resolver_firstname, String resolver_lastname, int rt_type, int rt_status) {
		this.r_id = r_id;
		this.r_amount = r_amount;
		this.description = description;
		this.r_receipt = r_receipt;
		this.r_submitted = r_submitted;
		this.r_resolved = r_resolved;
		this.u_id_author = u_id_author;
		this.author_firstname = author_firstname;
		this.author_lastname = author_lastname;
		this.u_id_resolver = u_id_resolver;
		this.resolver_firstname = resolver_firstname;
		this.resolver_lastname = resolver_lastname;
		this.rt_type = rt_type;
		this.rt_status = rt_status;
	}

	/**
	 * @return the r_id
	 */
	public int getR_id() {
		return r_id;
	}

	/**
	 * @param r_id the r_id to set
	 */
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	/**
	 * @return the r_amount
	 */
	public double getR_amount() {
		return r_amount;
	}

	/**
	 * @param r_amount the r_amount to set
	 */
	public void setR_amount(double r_amount) {
		this.r_amount = r_amount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the r_receipt
	 */
	public Blob getR_receipt() {
		return r_receipt;
	}

	/**
	 * @param r_receipt the r_receipt to set
	 */
	public void setR_receipt(Blob r_receipt) {
		this.r_receipt = r_receipt;
	}

	/**
	 * @return the r_submitted
	 */
	public Timestamp getR_submitted() {
		return r_submitted;
	}

	/**
	 * @param r_submitted the r_submitted to set
	 */
	public void setR_submitted(Timestamp r_submitted) {
		this.r_submitted = r_submitted;
	}

	/**
	 * @return the r_resolved
	 */
	public Timestamp getR_resolved() {
		return r_resolved;
	}

	/**
	 * @param r_resolved the r_resolved to set
	 */
	public void setR_resolved(Timestamp r_resolved) {
		this.r_resolved = r_resolved;
	}

	/**
	 * @return the rt_type
	 */
	public int getRt_type() {
		return rt_type;
	}

	/**
	 * @param rt_type the rt_type to set
	 */
	public void setRt_type(int rt_type) {
		this.rt_type = rt_type;
	}

	/**
	 * @return the rt_status
	 */
	public int getRt_status() {
		return rt_status;
	}

	/**
	 * @param rt_status the rt_status to set
	 */
	public void setRt_status(int rt_status) {
		this.rt_status = rt_status;
	}

	/**
	 * @return the u_id_author
	 */
	public int getU_id_author() {
		return u_id_author;
	}

	/**
	 * @param u_id_author the u_id_author to set
	 */
	public void setU_id_author(int u_id_author) {
		this.u_id_author = u_id_author;
	}

	/**
	 * @return the u_id_resolver
	 */
	public int getU_id_resolver() {
		return u_id_resolver;
	}

	/**
	 * @param u_id_resolver the u_id_resolver to set
	 */
	public void setU_id_resolver(int u_id_resolver) {
		this.u_id_resolver = u_id_resolver;
	}

	

	/**
	 * @return the author_firstname
	 */
	public String getAuthor_firstname() {
		return author_firstname;
	}

	/**
	 * @param author_firstname the author_firstname to set
	 */
	public void setAuthor_firstname(String author_firstname) {
		this.author_firstname = author_firstname;
	}

	/**
	 * @return the author_lastname
	 */
	public String getAuthor_lastname() {
		return author_lastname;
	}

	/**
	 * @param author_lastname the author_lastname to set
	 */
	public void setAuthor_lastname(String author_lastname) {
		this.author_lastname = author_lastname;
	}

	/**
	 * @return the resolver_firstname
	 */
	public String getResolver_firstname() {
		return resolver_firstname;
	}

	/**
	 * @param resolver_firstname the resolver_firstname to set
	 */
	public void setResolver_firstname(String resolver_firstname) {
		this.resolver_firstname = resolver_firstname;
	}

	/**
	 * @return the resolver_lastname
	 */
	public String getResolver_lastname() {
		return resolver_lastname;
	}

	/**
	 * @param resolver_lastname the resolver_lastname to set
	 */
	public void setResolver_lastname(String resolver_lastname) {
		this.resolver_lastname = resolver_lastname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reimbursements [r_id=" + r_id + ", r_amount=" + r_amount + ", description=" + description
				+ ", r_receipt=" + r_receipt + ", r_submitted=" + r_submitted + ", r_resolved=" + r_resolved
				+ ", u_id_author=" + u_id_author + ", author_firstname=" + author_firstname + ", author_lastname="
				+ author_lastname + ", u_id_resolver=" + u_id_resolver + ", resolver_firstname=" + resolver_firstname
				+ ", resolver_lastname=" + resolver_lastname + ", rt_type=" + rt_type + ", rt_status=" + rt_status
				+ "]";
	}
	
	
}

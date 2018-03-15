package com.revature.beans;

import java.sql.Timestamp;
import java.io.File;
import java.sql.Date;

public class Reimbursement {

	private Integer id;			//reimbursement id
	private Double amount;		//dollar amount for reimbursement
	private String descript;	//can be null //short description of reimbursement
	private java.sql.Date submitted;		//date of submission
	private Integer author;		//person who submitted reimbursement
	private String author_username;
	private String author_fullname;
	private String author_email;
	private Integer resolver;
	private String resolver_name;//can be null //account that approved reimbursement
	private Integer type;
	private String typeStr;
	private Integer status;		// (-1) deny / (0) pending / (1) approved
	private String statusStr;
	private java.sql.Date resovled;		//can be null //date of resolution
	private Object receipt;		//can be null //image of receipt
	//private byte[] image;
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(Integer id, Double amount, String descript, java.sql.Date submitted, Integer author, Integer resolver,
			Integer type, Integer status, java.sql.Date resovled, Object receipt) {
		super();
		this.id = id;
		this.amount = amount;
		this.descript = descript;
		this.submitted = submitted;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
		this.resovled = resovled;
		this.receipt = receipt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public java.sql.Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(java.sql.Date submitted) {
		this.submitted = submitted;
	}
	public Integer getAuthor() {
		return author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public Integer getResolver() {
		return resolver;
	}
	public void setResolver(Integer resolver) {
		this.resolver = resolver;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public java.sql.Date getResovled() {
		return resovled;
	}
	public void setResovled(java.sql.Date resovled) {
		this.resovled = resovled;
	}
	public Object getReceipt() {
		return receipt;
	}
	public void setReceipt(Object receipt) {
		this.receipt = receipt;
	}
	
	
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getAuthor_username() {
		return author_username;
	}
	public void setAuthor_username(String author_username) {
		this.author_username = author_username;
	}
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", descript=" + descript + ", submitted=" + submitted
				+ ", author=" + author + ", resolver=" + resolver + ", type=" + type + ", status=" + status
				+ ", resovled=" + resovled + ", receipt=" + receipt + "]";
	}
	public String getAuthor_fullname() {
		return author_fullname;
	}
	public void setAuthor_fullname(String author_fullname) {
		this.author_fullname = author_fullname;
	}
	public String getAuthor_email() {
		return author_email;
	}
	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}
	public String getResolver_name() {
		return resolver_name;
	}
	public void setResolver_name(String resolver_name) {
		this.resolver_name = resolver_name;
	}
//	public byte[] getImage() {
//		return image;
//	}
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
	
	
	
	
}

package com.revature.bean.helper;

/*
 * Helper Bean to take care of ticket POST requests
 */
public class ViewTicket {
	/*
	 * type can be:
	 * 		status: 
	 * 			pending 
	 * 			approved 
	 * 			denied
	 * 		image
	 * 		all
	*/
	private String type;
	/*
	 * author can be:
	 * 		userid
	 * 		0 for all
	 */
	private int author;
	
	public ViewTicket() {
		this.type = "";
		this.author = -1;//default to -1
	}
	
	public ViewTicket(ViewTicket vt) {
		this.type = vt.type;
		this.author = vt.author;
	}
	
	public ViewTicket(String type, int author) {
		this.type = type;
		this.author = author;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "ViewTicket [type=" + type + ", author=" + author + "]";
	}
	
}

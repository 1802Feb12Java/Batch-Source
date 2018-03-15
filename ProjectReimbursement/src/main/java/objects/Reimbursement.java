package objects;

import java.util.Date;

public class Reimbursement {

	/* reimbursement object is labeled with an id, amount, dates requested and resolved, description, user that requested it, 
	 * user that resolved it, it's status and type */
	
	private int id;
	private double amount;
	private Date date_submitted;
	private Date date_resolved;
	private String description;
	private User author_id;
	private User resolver_id;
	private RequestStatus status_id;
	private RequestType type_id;
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", date_submitted=" + date_submitted
				+ ", date_resolved=" + date_resolved + ", description=" + description + ", author_id=" + author_id
				+ ", resolver_id=" + resolver_id + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
	
	public Reimbursement(int id, double amount, Date date_submitted, Date date_resolved, String description,
		
		User author_id, User resolver_id, RequestStatus status_id, RequestType type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.date_submitted = date_submitted;
		this.date_resolved = date_resolved;
		this.description = description;
		this.author_id = author_id;
		this.resolver_id = resolver_id;
		this.status_id = status_id;
		this.type_id = type_id;
	}
	
	public Reimbursement() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate_submitted() {
		return date_submitted;
	}
	public void setDate_submitted(Date date_submitted) {
		this.date_submitted = date_submitted;
	}
	public Date getDate_resolved() {
		return date_resolved;
	}
	public void setDate_resolved(Date date_resolved) {
		this.date_resolved = date_resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(User author_id) {
		this.author_id = author_id;
	}
	public User getResolver_id() {
		return resolver_id;
	}
	public void setResolver_id(User resolver_id) {
		this.resolver_id = resolver_id;
	}
	public RequestStatus getStatus_id() {
		return status_id;
	}
	public void setStatus_id(RequestStatus status_id) {
		this.status_id = status_id;
	}
	public RequestType getType_id() {
		return type_id;
	}
	public void setType_id(RequestType type_id) {
		this.type_id = type_id;
	}	
}
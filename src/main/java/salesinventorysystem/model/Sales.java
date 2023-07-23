package salesinventorysystem.model;

import java.util.Date;

public class Sales {
	private int id;
	private double amount;
	private String type;
	private Date created_at;
//	private Date month;
	
	public Sales(int id, double amount, String type, Date created_at) {
		this.id = id;
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
	}
		
	public Sales(double amount, String type, Date created_at) {
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
	}
	
	public Sales(double amount) {
		this.amount = amount;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	
	
}

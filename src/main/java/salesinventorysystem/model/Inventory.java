package salesinventorysystem.model;

import java.util.Date;

public class Inventory {
	private int id;
	private String name;
	private int amount;
	private boolean status;
	private Date created_at;
	
	public Inventory(int id, String name, int amount, boolean status, Date created_at) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.status = status;
		this.created_at = created_at;
	}
	
	public Inventory(String name, int amount, boolean status, Date created_at) {
		this.name = name;
		this.amount = amount;
		this.status = status;
		this.created_at = created_at;
	}
	
	public Inventory(String name, int amount, boolean status) {
		super();
		this.name = name;
		this.amount = amount;
		this.status = status;
	}
	
	

	public Inventory(int id, String name, int amount, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}	
}

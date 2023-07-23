package salesinventorysystem.model;

import java.util.Date;

public class InventoryHistory {
	private int id;
	private int inventory_id;
	private String inventory_name;
	private int amount;
	private String type;
	private Date created_at;
	
	public InventoryHistory(int id, int inventory_id, int amount, String type, Date created_at) {
		super();
		this.id = id;
		this.inventory_id = inventory_id;
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
	}
	
	public InventoryHistory(int inventory_id, int amount, String type, Date created_at) {
		this.inventory_id = inventory_id;
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
	}
	
	public InventoryHistory(int id, int inventory_id, String inventory_name, int amount, String type, Date created_at) {
		this.id = id;
		this.inventory_id = inventory_id;
		this.inventory_name = inventory_name;
		this.amount = amount;
		this.type = type;
		this.created_at = created_at;
	}

	
	public String getInventory_name() {
		return inventory_name;
	}

	public void setInventory_name(String inventory_name) {
		this.inventory_name = inventory_name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
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

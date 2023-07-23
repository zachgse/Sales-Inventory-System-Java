package salesinventorysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import salesinventorysystem.jdbc.JdbcConnector;
import salesinventorysystem.model.Inventory;
import salesinventorysystem.model.InventoryHistory;

public class InventoryHistoryDao {
	private static final String CREATE_INVENTORY_HISTORY_SQL = "INSERT into inventory_history (inventory_id, amount, type, created_at) VALUES (?,?,?,?)";
//	private static final String SELECT_INVENTORY_HISTORY_SQL = "SELECT id, inventory_id, inventory.name , inventory_history.amount, inventory_history.created_at\r\n"
//																+ "FROM inventory_history\r\n"
//																+ "INNER JOIN inventory ON inventory.id = inventory_history.inventory_id\r\n"
//																+ "ORDER BY inventory_history.created_at";
	private static final String SELECT_INVENTORY_HISTORY_SQL = 
		    "SELECT inventory_history.id AS id, " +
		    "inventory.id AS inventory_id, " +
		    "inventory.name AS inventory_name, " +
		    "inventory_history.amount, " +
		    "inventory_history.type, " +
		    "inventory_history.created_at " +
		    "FROM inventory_history " +
		    "INNER JOIN inventory ON inventory.id = inventory_history.inventory_id " +
		    "ORDER BY inventory_history.created_at";
	private JdbcConnector jdbcConnector;
	
	public InventoryHistoryDao() {
		jdbcConnector = new JdbcConnector();
	}	
	
	public void store(InventoryHistory inventoryHistory) {
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_INVENTORY_HISTORY_SQL);) {
			java.util.Date createdAt = inventoryHistory.getCreated_at();
			java.sql.Date sqlCreatedAt = new java.sql.Date(createdAt.getTime());
			
			preparedStatement.setInt(1, inventoryHistory.getInventory_id());
			preparedStatement.setInt(2, inventoryHistory.getAmount());
			preparedStatement.setString(3, inventoryHistory.getType());
			preparedStatement.setDate(4, sqlCreatedAt);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<InventoryHistory> index() {
		List<InventoryHistory> inventoryHistoryList = new ArrayList<>();
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVENTORY_HISTORY_SQL);
				ResultSet rs = preparedStatement.executeQuery()){
	        while (rs.next()) {
	        	int id = rs.getInt("id");
	        	int inventory_id = rs.getInt("inventory_id");
	        	String inventory_name = rs.getString("inventory_name");
	            int amount = rs.getInt("amount");
	            String type = rs.getString("type");
	            Date created_at = rs.getDate("created_at");

	            inventoryHistoryList.add(new InventoryHistory(id, inventory_id, inventory_name,amount, type, created_at));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryHistoryList;
	}
	
	
}

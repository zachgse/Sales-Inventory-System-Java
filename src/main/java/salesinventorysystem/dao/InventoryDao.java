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

public class InventoryDao {
	private JdbcConnector jdbcConnector;
	private static final String SELECT_ALL_DATA_SQL = "SELECT * FROM inventory ORDER BY id";
	private static final String GET_DATA_ID_SQL = "SELECT id, name, amount, status FROM inventory WHERE id=?";
	private static final String CREATE_DATA_SQL = "INSERT INTO inventory (name,amount,status,created_at) VALUES (?,?,?,?)";
	private static final String UPDATE_INVENTORY_INFO_SQL = "UPDATE inventory SET name=? WHERE id=?";
	private static final String UPDATE_INVENTORY_AMOUNT_SQL = "UPDATE inventory SET amount=?, status=? WHERE id=?";
	
	public InventoryDao() {
		jdbcConnector = new JdbcConnector();
	}
	
	public void store(Inventory inventory) {
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DATA_SQL)) {
			// Assuming inventory.getCreated_at() returns a java.util.Date object
			java.util.Date createdAt = inventory.getCreated_at();
			// Convert java.util.Date to java.sql.Date
			java.sql.Date sqlCreatedAt = new java.sql.Date(createdAt.getTime());
			
			preparedStatement.setString(1, inventory.getName());
			preparedStatement.setInt(2, inventory.getAmount());
			preparedStatement.setBoolean(3, inventory.isStatus());
			preparedStatement.setDate(4, sqlCreatedAt);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Inventory> index() {
		List<Inventory> inventoryList = new ArrayList<>();
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DATA_SQL);
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int amount = rs.getInt("amount");
				boolean status = rs.getBoolean("status");
				Date created_at = rs.getDate("created_at");
				inventoryList.add(new Inventory(id,name,amount,status,created_at));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventoryList;
	}
	
	public Inventory view(int id) {
		Inventory inventory = null;
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_DATA_ID_SQL)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int amount = rs.getInt("amount");
				boolean status = rs.getBoolean("status");
				inventory = new Inventory(id,name,amount,status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventory;
	}
	
	public boolean update_info(Inventory inventory) {
		boolean updatedRow = false;
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_INFO_SQL)) {
			preparedStatement.setString(1, inventory.getName());
			preparedStatement.setInt(2, inventory.getId());
			updatedRow = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRow;
	}
	
	public boolean update_amount(Inventory inventory) {
		boolean updatedRow = false;
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_AMOUNT_SQL)) {
			preparedStatement.setInt(1, inventory.getAmount());
			preparedStatement.setBoolean(2, inventory.isStatus());
			preparedStatement.setInt(3, inventory.getId());
			updatedRow = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedRow;
	}
}

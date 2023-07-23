package salesinventorysystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import salesinventorysystem.jdbc.JdbcConnector;
import salesinventorysystem.model.Sales;

public class SalesDao {
	private JdbcConnector jdbcConnector;
	private static final String SELECT_ALL_DATA_SQL = "SELECT * FROM sales ORDER BY created_at";
	private static final String CREATE_DATA_SQL = "INSERT INTO sales (amount,type,created_at) VALUES (?,?,?)";
	private static final String GET_DATA_ID_SQL = "SELECT * FROM sales WHERE id = ?";
	
	public SalesDao() {
		jdbcConnector = new JdbcConnector();
	}
	
	public List<Sales> index() {
		List<Sales> salesList = new ArrayList<>();
		try (Connection connection = jdbcConnector.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DATA_SQL);
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				double amount = rs.getDouble("amount");
				String type = rs.getString("type");
				Date created_at = rs.getDate("created_at");
				Sales sales = new Sales(amount,type,created_at);
				salesList.add(sales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salesList;
	}
	
	public void store(Sales sales) {
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DATA_SQL)) {
			// Assuming inventory.getCreated_at() returns a java.util.Date object
			java.util.Date createdAt = sales.getCreated_at();
			// Convert java.util.Date to java.sql.Date
			java.sql.Date sqlCreatedAt = new java.sql.Date(createdAt.getTime());
			preparedStatement.setDouble(1, sales.getAmount());
			preparedStatement.setString(2, sales.getType());
			preparedStatement.setDate(3, sqlCreatedAt);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Sales view(int id) {
		Sales sales = null;
		try (Connection connection = jdbcConnector.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_DATA_ID_SQL);
				ResultSet rs = preparedStatement.executeQuery()) {
			preparedStatement.setInt(1, id);
			while (rs.next()) {
				double amount = rs.getDouble("amount");
				String type = rs.getString("type");
				Date created_at = rs.getDate("created_at");
				sales = new Sales(amount,type,created_at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}

	public Sales view(Sales sales) {
		// TODO Auto-generated method stub
		return null;
	}


}

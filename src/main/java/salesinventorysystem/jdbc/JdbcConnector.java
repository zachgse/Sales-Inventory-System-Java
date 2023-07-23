package salesinventorysystem.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
	private String url = "jdbc:postgresql://localhost:5432/sample"; 
	private String username = "postgres";
	private String password = "admin";
	private String driver = "org.postgresql.Driver";
	public Connection getConnection() throws SQLException{
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
	}
}

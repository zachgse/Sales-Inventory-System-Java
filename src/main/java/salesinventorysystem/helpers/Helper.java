package salesinventorysystem.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import salesinventorysystem.jdbc.JdbcConnector;

public class Helper {
	private static JdbcConnector jdbcConnector;
	private static final String SELECT_INVENTORY_NAME_DATA_SQL = "SELECT name from inventory";
	
	public Helper() {
		jdbcConnector = new JdbcConnector();
	}
	
	public static String ucFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
            	sb.append(Character.toLowerCase(c));
            }
        }
        
        return sb.toString();
	}
	
	public static boolean positiveInput(int amount) {
		if (amount > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean existingName(String name) {
		try (Connection connection = jdbcConnector.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVENTORY_NAME_DATA_SQL);
				ResultSet rs = preparedStatement.executeQuery()) {
			while (rs.next()) {
				String name1 = rs.getString("name");
				if (name1.equals(name)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}

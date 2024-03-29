package net.javaguides.jsp.tutorial.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDao {
	
	public int registerStudent(Student stud) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO student" 
	            + "  (first_name, last_name, username, password, college, address, contact) VALUES "
				+ " (?, ?, ?, ?,?,?,?);";

		int result = 0;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/my_db?useSSL=false", "root", "root");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//			preparedStatement.setInt(1, 1);
			preparedStatement.setString(1, stud.getFirstName());
			preparedStatement.setString(2, stud.getLastName());
			preparedStatement.setString(3, stud.getUsername());
			preparedStatement.setString(4, stud.getPassword());
			preparedStatement.setString(5, stud.getCollege());
			preparedStatement.setString(6, stud.getAddress());
			preparedStatement.setString(7, stud.getContact());
			
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return result;
	}
	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

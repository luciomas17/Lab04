package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private static final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root&password=root&serverTimezone=UTC";

	public static Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
		
		return conn;
	}
}

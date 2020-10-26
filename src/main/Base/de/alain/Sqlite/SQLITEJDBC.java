package de.alain.Sqlite;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLITEJDBC {

	public void SQLITEJDBCConnectToDatabase() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}

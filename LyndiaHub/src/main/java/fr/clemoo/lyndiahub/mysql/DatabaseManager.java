package fr.clemoo.lyndiahub.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private String urlBase, host, database, username, password;
	private int port;
	private Connection connection;
	
	public DatabaseManager(String urlBase, String host, int port,String database, String username, String password) {
		this.urlBase = urlBase;
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	public void connection() {
		try {
			if(!isOnline()) {
				connection = DriverManager.getConnection(this.urlBase + this.host + ":" +this.port + "/" + this.database, this.username, this.password);
				System.out.println("§aSuccessfully connection to the database");
			}
		}catch(SQLException e) {
			System.out.println("Connectiion failed");
			e.printStackTrace();
		}
	}
	
	public void disconnection() {
		try {
			if(isOnline()) {
				connection.close();
				System.out.println("§aSuccessfully disconnection to the database");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isOnline() {
		try {
			if(connection == null || connection.isClosed()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

package fr.clemoo.lyndiahub.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

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
	
	
	public boolean hasAccount(UUID uuid) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM players WHERE uuid = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				rs.close();
				preparedStatement.close();
				return true;
			}
			rs.close();
			preparedStatement.close();
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createAccount(UUID uuid) {
		if(!hasAccount(uuid))
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (player_uuid, player_pseudo, money) VALUES (?,?,?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, Bukkit.getPlayer(uuid).getName());
				preparedStatement.setInt(3, 400);
				preparedStatement.execute();
				preparedStatement.close();
				return;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return;
	}
	
}

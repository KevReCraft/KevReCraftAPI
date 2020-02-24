package de.kevrecraft.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.kevrecraft.KevReCraftAPI;

public class MySQL {
	private String host;
	private String port;
	private String database;
	private String username;
	private String password;
	private Connection con;
	
	
	public MySQL(String host, String port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;
	}
	
	public MySQL(MySQLConfigFile config) {
		this.host = config.getHost();
		this.port = config.getPort();
		this.database = config.getDatabase();
		this.username = config.getUsername();
		this.password = config.getPassword();
	}
	
	public void connect() {
		if(!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
				Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + ChatColor.GREEN + " MySQL" + ChatColor.WHITE +" connected to " + this.database + "!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void disconnect() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.con;
	}
	
	public boolean isConnected() {
		return (con == null ? false : true);
	}
}

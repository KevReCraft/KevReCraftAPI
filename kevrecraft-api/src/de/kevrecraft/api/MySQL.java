package de.kevrecraft.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.kevrecraft.KevReCraftAPI;

public class MySQL {
	// Variablen ----------------------------------------------------------
	private Connection con;
	
	// Methoden -----------------------------------------------------------
	public MySQL(String host, String port, String database, String username, String password) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
			Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + ChatColor.GREEN + " MySQL" + ChatColor.WHITE +" connected to " + database + "!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MySQL(MySQLConfigFile config) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase(), config.getUsername(), config.getPassword());
			Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GRAY + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + ChatColor.GREEN + " MySQL" + ChatColor.WHITE +" connected to " + config.getDatabase() + "!");
		} catch (SQLException e) {
			e.printStackTrace();
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

package de.kevrecraft.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.kevrecraft.KevReCraftAPI;

public enum MySQL {
	PLAYERDATA(0, "PlayerData"),
	PERMISSIONS(1, "Permission");
	
	public static MySQL[] getList() {
		MySQL[] list = { 
				MySQL.PLAYERDATA,
				MySQL.PERMISSIONS
		};
		return list;
	}
	
	private int id;
	private String name;
	
	private static HashMap<Integer, Connection> connections = new HashMap<Integer, Connection>();
	
	private static HashMap<Integer, Connection> getConnectionsHashMap() {
		return connections;
	}
	
	MySQL(int id, String name) {
		this.id = id;
		this.name = name;
		if(true) {
			FileWriter fw = new FileWriter("MySQLConfig");
			if(fw.exist()) {
				String host = fw.getString(this.name + ".host");
				String port = fw.getString(this.name  + ".port");
				String database = fw.getString(this.name()  + ".database");
				String username = fw.getString(this.name()  + ".username");
				String password = fw.getString(this.name()  + ".password");

				try {
					getConnectionsHashMap().put(this.id, DriverManager.getConnection("jdbc:mysql//" + host + ":" + port + "/" + database, username, password));
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL connected!");
				} catch (SQLException e) {
					e.printStackTrace();
					
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error!");
				}
			} else {
				fw.setValue(this.name + ".host", "localhost");
				fw.setValue(this.name + ".port", "3306");
				fw.setValue(this.name + ".database", "database");
				fw.setValue(this.name + ".username", "username");
				fw.setValue(this.name + ".password", "password");
				
				fw.save();
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error! Config File not found!");
			}
		}
		
	}
	
	public Connection getConnection() {
		return getConnectionsHashMap().get(this.id);
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isConnected() {
		return getConnectionsHashMap().get(this.id) == null ? false : true;
	}
	
	public void disconnect() {
		try {
			if(getConnectionsHashMap().containsKey(this.id)) {
				getConnectionsHashMap().get(this.id).close();
				Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL disconnected!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

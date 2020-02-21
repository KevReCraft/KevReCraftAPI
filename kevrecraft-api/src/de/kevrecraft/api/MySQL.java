package de.kevrecraft.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.kevrecraft.KevReCraftAPI;

public enum MySQL {
	PLAYERDATA(0, "PlayerData");
	
	public static MySQL[] getList() {
		MySQL[] list = { 
				MySQL.PLAYERDATA
		};
		return list;
	}
	
	private int id;
	private String name;
	
	private static HashMap<Integer, Connection> connections;
	
	private static HashMap<Integer, Connection> getHashMap() {
		return connections;
	}
	
	MySQL(int id, String name) {
		this.id = id;
		this.name = name;
		if(getHashMap().get(this.id) == null) {
			FileWriter fw = new FileWriter("MySQLConfig");
			if(fw.exist()) {
				String host = fw.getString(this.name + ".host");
				String port = fw.getString(this.name  + ".port");
				String database = fw.getString(this.name()  + ".database");
				String username = fw.getString(this.name()  + ".username");
				String password = fw.getString(this.name()  + ".password");

				try {
					getHashMap().put(this.id, DriverManager.getConnection("jdbc:mysql//" + host + ":" + port + "/" + database, username, password));
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL connected!");
				} catch (SQLException e) {
					e.printStackTrace();
					
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error!");
					KevReCraftAPI.getInstance().getPluginLoader().disablePlugin(KevReCraftAPI.getInstance());
				}
			} else {
				fw.setValue("host", "localhost");
				fw.setValue("port", "3306");
				fw.setValue("database", "database");
				fw.setValue("username", "username");
				fw.setValue("password", "password");
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error! Config File not found!");
				KevReCraftAPI.getInstance().getPluginLoader().disablePlugin(KevReCraftAPI.getInstance());
			}
		}
		
	}
	
	public Connection getConnection() {
		return getHashMap().get(this.id);
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isConnected() {
		return getHashMap().get(this.id) == null ? false : true;
	}
	
	public void disconnect() {
		try {
			getHashMap().get(this.id).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

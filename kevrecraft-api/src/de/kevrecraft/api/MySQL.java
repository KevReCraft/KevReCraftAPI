package de.kevrecraft.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.kevrecraft.KevReCraftAPI;
import de.kevrecraft.api.type.MySQLType;

public class MySQL {
	private MySQLType type;
	
	private static HashMap<MySQLType, Connection> connections;
	
	public MySQL(MySQLType type) {
		this.type = type;
		if(connections.get(type) == null) {
			FileWriter fw = new FileWriter("MySQLConfig");
			if(fw.exist()) {
				String host = fw.getString(type.name() + ".host");
				String port = fw.getString(type.name()  + ".port");
				String database = fw.getString(type.name()  + ".database");
				String username = fw.getString(type.name()  + ".username");
				String password = fw.getString(type.name()  + ".password");

				try {
					connections.put(type, DriverManager.getConnection("jdbc:mysql//" + host + ":" + port + "/" + database, username, password));
				} catch (SQLException e) {
					e.printStackTrace();
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
	
	public boolean isConnected() {
		return connections.get(this.type) == null ? false : true;
	}
	
	public void disconnect() {
		try {
			connections.get(this.type).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

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
	
	public MySQL(MySQLType type) {
		this.type = type;
	}
	
	public Connection getConnection() {
		return connections.get(this.type);
	}
	
	public MySQLType getType() {
		return this.type;
	}
	
	public String getName() {
		return this.type.getName();
	}
	
	public boolean isConnected() {
		return connections.get(this.type) == null ? false : true;
	}
	
	// STATIC ---------------------------------------------------------------------------------------------------
	private static HashMap<MySQLType, Connection> connections = new HashMap<MySQLType, Connection>();
	
	private static FileWriter fw = new FileWriter("MySQL//","config");
	
	public static void load() {
		if(!fw.exist()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL can't find Config File!");
			for(MySQLType type : MySQLType.getAll()) {
				fw.setValue(type.getName() + ".host", "localhost");
				fw.setValue(type.getName() + ".port", "3306");
				fw.setValue(type.getName() + ".database", "database");
				fw.setValue(type.getName() + ".username", "username");		
				fw.setValue(type.getName() + ".password", "password");
			}
			fw.save();
			Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL created Config File!");
			
		} else {
			for(MySQLType type : MySQLType.getAll()) {
				String host = fw.getString(type.getName() + ".host");
				String port = fw.getString(type.getName()  + ".port");
				String database = fw.getString(type.getName()  + ".database");
				String username = fw.getString(type.getName()  + ".username");
				String password = fw.getString(type.getName()  + ".password");
				
				try {
					connections.put(type, DriverManager.getConnection("jdbc:mysql//" + host + ":" + port + "/" + database, username, password));
				} catch (SQLException e) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error! Can't connect to " + type.getName() + " Server!");
				}
			}
			
			
		}
	}
	
	public static void unload() {
		for (MySQLType type : MySQLType.getAll()) {
			if(new MySQL(type).isConnected()) {
				try {
					new MySQL(type).getConnection().close();
				} catch (SQLException e) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error! Can't close " + type.getName() + "'s Connection !");
				}
			}
		}
	}
}

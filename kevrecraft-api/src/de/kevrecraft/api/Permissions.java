package de.kevrecraft.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.kevrecraft.KevReCraftAPI;

public class Permissions implements Listener {
	// Variablen
	private static HashMap<UUID, ArrayList<String>> permissions = new HashMap<UUID, ArrayList<String>>();
		
	// Methoden
	public static boolean exist(UUID uuid) {
		return permissions.containsKey(uuid);
	}
	
	public static boolean exist(Player p) {
		return permissions.containsKey(p.getUniqueId());
	}
	
	public static void add(UUID uuid, String permission) {
		ArrayList<String> perm = getList(uuid);
		if(perm == null) {
			perm = new ArrayList<String>();
		}
		perm.add(permission);
		permissions.put(uuid, perm);
	}
	
	public static void add(Player p, String permission) {
		ArrayList<String> perm = getList(p.getUniqueId());
		if(perm == null) {
			perm = new ArrayList<String>();
		}
		perm.add(permission);
		permissions.put(p.getUniqueId(), perm);
	}
	
	public static void add(UUID uuid, ArrayList<String> permission) {
		permissions.put(uuid, permission);
	}
	
	public static void add(Player p, ArrayList<String> permission) {
		permissions.put(p.getUniqueId(), permission);
	}
	
	public static void remove(UUID uuid, String permission) {
		if(exist(uuid)) {
			for(int i = 0; i<permissions.get(uuid).size(); i++) {
				if(permissions.get(uuid).get(i).equalsIgnoreCase(permission)) {
					permissions.get(uuid).remove(i);
				}
			}
		}
	}
	
	public static void remove(Player p, String permission) {
		if(exist(p.getUniqueId())) {
			for(int i = 0; i<permissions.get(p.getUniqueId()).size(); i++) {
				if(permissions.get(p.getUniqueId()).get(i).equalsIgnoreCase(permission)) {
					permissions.get(p.getUniqueId()).remove(i);
				}
			}
		}
	}
	
	public static void removeAll(UUID uuid) {
		if(exist(uuid)) {
			permissions.get(uuid).clear();
		}
	}
	
	public static void removeAll(Player p) {
		if(exist(p.getUniqueId())) {
			permissions.get(p.getUniqueId()).clear();
		}
	}
	
	public static void delete(UUID uuid) {
		if(exist(uuid)) {
			permissions.remove(uuid);
		}
		
		deleteMySQL(uuid);
	}
	
	public static void delete(Player p) {
		if(exist(p.getUniqueId())) {
			permissions.remove(p.getUniqueId());
		}
		
		deleteMySQL(p.getUniqueId());
	}
	
	public static ArrayList<String> getList(UUID uuid) {
		return permissions.get(uuid);
	}
	
	public static ArrayList<String> getList(Player p) {
		return permissions.get(p.getUniqueId());
	}
	
	public static String toString(UUID uuid) {
		String list = "";
		for(int i = getList(uuid).size() - 1; i >= 0; i--) {
			if(i == 0) {
				list += getList(uuid).get(i);
			} else {
				list += getList(uuid).get(i) + ", ";
			}
		}
		return list;
	}
	
	public static String toString(Player p) {
		String list = "";
		for(int i = getList(p.getUniqueId()).size() - 1; i >= 0; i--) {
			if(i == 0) {
				list += getList(p.getUniqueId()).get(i);
			} else {
				list += getList(p.getUniqueId()).get(i) + ", ";
			}
		}
		return list;
	}
	
	public static boolean has(UUID uuid, String permission) {
		if(exist(uuid)) {
			return permissions.get(uuid).contains(permission);
		}
		return false;
	}
	
	public static boolean has(Player p, String permission) {
		if(exist(p.getUniqueId())) {
			return permissions.get(p.getUniqueId()).contains(permission);
		}
		return false;
	}
	
	// MySQL Stuff ----------------------------------------------------------------------------------------
	// Variablen
	private static MySQL mySQL;
	
	// Methoden
	private static MySQLConfigFile getPermissionMySQLConfigFile() {
		MySQLConfigFile config = new MySQLConfigFile("permissions");
		config.createValuesIfNotExist();
		return config;
	}
	
	public static void connect() {
		mySQL = new MySQL(getPermissionMySQLConfigFile());
		try {
			PreparedStatement ps = mySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Player (UUID VARCHAR(100), Permissions TEXT(65535))");
			ps.executeUpdate();
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + KevReCraftAPI.getInstance().getName() + ChatColor.WHITE + "]" + " MySQL Error! Can't create Permissions Table");
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(exist(p.getUniqueId())) {
				permissions.remove(p.getUniqueId());
			}
			add(p.getUniqueId(), getAllFromMySQL(p.getUniqueId()));
		}
	}
	
	public static void disconect() {
		mySQL.disconnect();
	}

	private static boolean isUserExistsMySQL(UUID uuid) {
		try {
			PreparedStatement ps = mySQL.getConnection().prepareStatement("SELECT Permissions FROM Player WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static void updateMySQL(UUID uuid, ArrayList<String> perms) {
		if(isUserExistsMySQL(uuid)) {
			try {
				PreparedStatement ps = mySQL.getConnection().prepareStatement("UPDATE Player SET Permissions = ? WHERE UUID = ?");
				
				ps.setString(1, toString(uuid));
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement ps = mySQL.getConnection().prepareStatement("INSERT INTO Player (UUID, Permissions) VALUES (?, ?)");
				ps.setString(1, uuid.toString());
				ps.setString(2, getList(uuid).toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void deleteMySQL(UUID uuid) {
		if(isUserExistsMySQL(uuid)) {
			try {
				PreparedStatement ps = mySQL.getConnection().prepareStatement("DELETE * FROM Player WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static ArrayList<String> getAllFromMySQL(UUID uuid) {
		try {
			PreparedStatement ps = mySQL.getConnection().prepareStatement("SELECT Permissions FROM Player WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			String list = "";
			while(rs.next()) {
				list =rs.getString("Permissions");
			}
			ArrayList<String> perms = new ArrayList<String>();
			for(int i = 0; i<list.split(", ").length; i++) {
				perms.add(list.split(", ")[i]);
			}
			return perms;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	// Events ---------------------------------------------------------------------------------------------
		@EventHandler
		public void onPlayerLogin(PlayerLoginEvent e) {
			Player p = e.getPlayer();
			if(exist(p.getUniqueId())) {
				permissions.remove(p.getUniqueId());
			}
			add(p.getUniqueId(), getAllFromMySQL(p.getUniqueId()));
		}
		
		@EventHandler
		public void onPlayerQuit(PlayerQuitEvent e) {
			Player p = e.getPlayer();
			updateMySQL(p.getUniqueId(), getList(p.getUniqueId()));
		}
}

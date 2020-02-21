package de.kevrecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.kevrecraft.api.MySQL;
import de.kevrecraft.api.type.MySQLType;

public class KevReCraftAPI extends JavaPlugin {
	private static KevReCraftAPI instance = null;
	
	public static KevReCraftAPI getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		new MySQL(MySQLType.PlayerData);
		
		registerCommands();
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + this.getName() + ChatColor.WHITE + "]" + " successfully started!");
	}
	
	@Override
	public void onDisable() {
		for(MySQLType type : MySQLType.getTypeList()) {
			new MySQL(type).disconnect();
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + this.getName() + ChatColor.WHITE + "]" + " successfully terminated!");
	}
	
	// Hier sind alle zu registrierenden Kommandos eingetragen
	private void registerCommands() {
		this.getCommand("help").setExecutor(new de.kevrecraft.commands.Help());
	}
}

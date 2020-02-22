package de.kevrecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.kevrecraft.api.MySQL;

public class KevReCraftAPI extends JavaPlugin {
	private static KevReCraftAPI instance = null;
	
	public static KevReCraftAPI getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		registerCommands();
		registerEvents();
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + this.getName() + ChatColor.WHITE + "]" + " successfully started!");
	}
	
	@Override
	public void onDisable() {
		//MySQL verbindungen trennen
		for(int i = 0; i<MySQL.getList().length; i++) {
			MySQL.getList()[i].disconnect();
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + this.getName() + ChatColor.WHITE + "]" + " successfully terminated!");
	}
	
	// Hier sind alle zu registrierenden Kommandos eingetragen
	private void registerCommands() {
		this.getCommand("help").setExecutor(new de.kevrecraft.commands.Help());
	}
	
	// Hier sind alle zu registrierenden Event Listener eingetragen
	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new de.kevrecraft.api.Permissions(), this);
	}
}

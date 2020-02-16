package de.kevrecraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class KevReCraftAPI extends JavaPlugin {
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		super.onEnable();
		
		registerCommands();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "KevReCraftAPI" + ChatColor.WHITE + "]" + " API successfully started!");
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
		
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "KevReCraftAPI" + ChatColor.WHITE + "]" + " API successfully terminated!");
	}
	
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
	}
	
	// Hier sind alle zu registrierenden Kommandos eingetragen
	private void registerCommands() {
		this.getCommand("help").setExecutor(new de.kevrecraft.commands.Help());
	}
}

package de.kevrecraft.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Permissions implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
	}
	
	// Ob der Spieler in der Datenbank existiert
	public static boolean exist(Player p) {
		
		
		return false;
	}
	
	// Permissions einem Spieler geben
	public static boolean add(Player p, String permission) {
		if (Bukkit.getPlayer(p.getName()) != null) {
			
		} else {
			
		}
		
		return false;
	}
	
	public static boolean remove(Player p, String permission) {
		
		return false;
	}
	
	public static String[] get(Player p) {
		if(exist(p)) {
			
		}
		
		return null;
	}
	
}

package de.kevrecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.kevrecraft.api.PermissionsManager;

public class Perm implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			if(PermissionsManager.has((Player)sender, "perm.*")) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("get")) {
						if(Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]); 
							sender.sendMessage(PermissionsManager.toString(target));
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
							return true;
						}
					} else if(args[0].equalsIgnoreCase("remove")) {
						if(Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]); 
							PermissionsManager.removeAll(target.getUniqueId());
							sender.sendMessage("Permissions von " + target.getName() + " gelöscht!");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
							return true;
						}
					}
				}
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("add")) {
						if(Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]); 
							de.kevrecraft.api.PermissionsManager.add(target.getUniqueId(), args[2]);
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hatt nun die Permission " + args[2]);
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
							return true;
						}
						
					} else if(args[0].equalsIgnoreCase("has")) {
						if(Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]); 
							if(de.kevrecraft.api.PermissionsManager.has(target.getUniqueId(), args[2])) {
								sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + "!");
							} else {
								sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht!");
							}
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
							return true;
						}
						
					} else if(args[0].equalsIgnoreCase("remove")) {
						if(Bukkit.getPlayer(args[1]) != null) {
							Player target = Bukkit.getPlayer(args[1]); 
							de.kevrecraft.api.PermissionsManager.remove(target.getUniqueId(), args[2]);
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht mehr!");
							return true;
						} else {
							sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
							return true;
						}
						
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Das darfst du nicht tun!");
				return true;
			}
		} else if(sender instanceof ConsoleCommandSender) {
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("get")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						sender.sendMessage(PermissionsManager.toString(target));
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
						return true;
					}
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						PermissionsManager.removeAll(target.getUniqueId());
						sender.sendMessage("Permissions von " + target.getName() + " gelöscht!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
						return true;
					}
				}
			}
			
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("add")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						de.kevrecraft.api.PermissionsManager.add(target.getUniqueId(), args[2]);
						sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hatt nun die Permission " + args[2]);
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
						return true;
					}
					
				} else if(args[0].equalsIgnoreCase("has")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						if(de.kevrecraft.api.PermissionsManager.has(target.getUniqueId(), args[2])) {
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + "!");
						} else {
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht!");
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
						return true;
					}
					
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						de.kevrecraft.api.PermissionsManager.remove(target.getUniqueId(), args[2]);
						sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht mehr!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
						return true;
					}
					
				}
			}
		}
		
		return false;
	}
}

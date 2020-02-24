package de.kevrecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.kevrecraft.api.Permissions;

public class Perm implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.RED + "Das darfst du nicht tun!");
		} else if(sender instanceof ConsoleCommandSender) {
			// /permissions add/get/remove player [permission]
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("get")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						sender.sendMessage(Permissions.getString(target.getUniqueId()));
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
					}
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						Permissions.removeAll(target.getUniqueId());
						sender.sendMessage("Permissions von " + target.getName() + " gel�scht!");
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
					}
				}
			}
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("add")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						de.kevrecraft.api.Permissions.add(target.getUniqueId(), args[2]);
						sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hatt nun die Permission " + args[2]);
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
					}
					
				} else if(args[0].equalsIgnoreCase("get")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						if(de.kevrecraft.api.Permissions.has(target.getUniqueId(), args[2])) {
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + "!");
						} else {
							sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
					}
					
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1]) != null) {
						Player target = Bukkit.getPlayer(args[1]); 
						de.kevrecraft.api.Permissions.remove(target.getUniqueId(), args[2]);
						sender.sendMessage(ChatColor.GRAY + "Der Spieler " + target.getName() + " hat die Permission " + args[2] + " nicht mehr!");
					} else {
						sender.sendMessage(ChatColor.RED + "Der Spieler " + args[1] + " konnte nicht gefunden werden!");
					}
					
				}
			}
		}
		
		return false;
	}
}
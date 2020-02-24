package de.kevrecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kevrecraft.api.Permissions;

public class Help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Hilfe für Kommandos");
			
			if(args.length == 0) {
				if(Permissions.has(p, "perm.help") || Permissions.has(p, "perm.*")) {
					sender.sendMessage(ChatColor.GRAY + "/help perm" + ChatColor.WHITE + " -> " + ChatColor.DARK_GRAY + "für mehr Permissions Hilfe!");
				}
				return true;
			} else if(args.length == 1){
				if(Permissions.has(p, "perm.help") || Permissions.has(p, "perm.*"))
					if(args[0].equalsIgnoreCase("perm")) {
						sender.sendMessage(ChatColor.GRAY + "/perm get [Player]");
						sender.sendMessage(ChatColor.DARK_GRAY + " -> Listet dir alle Permissions eines Spielers!");
						sender.sendMessage(ChatColor.GRAY + "/perm remove [Player]");
						sender.sendMessage(ChatColor.DARK_GRAY + " -> Löscht alle Permissions eines Spielers!");
						sender.sendMessage(ChatColor.GRAY + "/perm has [Player] [Permission]");
						sender.sendMessage(ChatColor.DARK_GRAY + " -> Sagt dir ob ein Spieler eine bestimmte Permission hat!");
						sender.sendMessage(ChatColor.GRAY + "/perm add [Player] [Permission]");
						sender.sendMessage(ChatColor.DARK_GRAY + " -> Gieb einem Spieler eine bestimmte Permission!");
						sender.sendMessage(ChatColor.GRAY + "/perm remove [Player] [Permission]");
						sender.sendMessage(ChatColor.DARK_GRAY + " -> Losche eine bestimmte Permissions eines Spielers!");
					}
			}
			p.sendMessage(ChatColor.DARK_GRAY + "Wrong Syntax: " + ChatColor.GRAY + "/help" + ChatColor.DARK_GRAY + " for more help!");
			return true;
		}
		return false;
	}
}

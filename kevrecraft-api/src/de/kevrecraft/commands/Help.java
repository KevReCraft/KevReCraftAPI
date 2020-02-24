package de.kevrecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.kevrecraft.api.Permissions;

public class Help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			player.sendMessage(ChatColor.GREEN + "KevReCraftAPI");
			
			if(Permissions.has(player.getUniqueId(), "help.*") || Permissions.has(player.getUniqueId(), "help.admin")) {
				player.sendMessage("HELP -> ADMIN");
			}
			return true;
		} else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender) sender;
			
			console.sendMessage(ChatColor.GREEN + "KevReCraftAPI");
			return true;
		}
		
		
		return false;
	}

}

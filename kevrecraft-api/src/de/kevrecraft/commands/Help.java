package de.kevrecraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			player.sendMessage(ChatColor.GREEN + "KevReCraftAPI");
			return true;
		}
		
		else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender console = (ConsoleCommandSender) sender;
			
			console.sendMessage(ChatColor.GREEN + "KevReCraftAPI");
			return true;
		}
		
		
		return false;
	}

}

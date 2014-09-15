package me.Kelson.util;

import me.Kelson.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class KelsonReloadCommand implements CommandExecutor{
	
	Commands plugin;
			public KelsonReloadCommand(Commands passedPlugin)
			{
				this.plugin = passedPlugin;
			}
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
		{
		if(cmd.getName().equalsIgnoreCase("kbp")){
			if(args.length == 0){
				
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			}if(!(sender instanceof Player)){
			  plugin.reloadConfig();
			  plugin.getServer().broadcastMessage(ChatColor.WHITE + "Console" + ChatColor.YELLOW + " has reloaded kelsons plugin's config!");
			  return true;
	         }
			Player player = (Player) sender;
			if(sender instanceof Player){
				if(player.hasPermission("kelson.reload")){
		    	        plugin.reloadConfig();
		    	        plugin.getServer().broadcastMessage(ChatColor.WHITE + player.getName() + " has reloaded kelsons plugin's config!");
                  
	              }			
	           }
	        }
		  return false;
		}

}

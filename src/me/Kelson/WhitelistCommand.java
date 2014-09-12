package me.Kelson;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WhitelistCommand implements CommandExecutor 
{
		Commands plugin;
		
		public WhitelistCommand(Commands passedPlugin)
		{
			this.plugin = passedPlugin;
		}
        
		public boolean onCommand(CommandSender sender, Command cmd, 
				String CommandLabel, String[] args) {
			
			      if(cmd.getName().equalsIgnoreCase("kwhitelist") && sender.hasPermission("kelson.whitelist")){
			        if(args.length == 0 && sender.hasPermission("kelson.whitelist")){
			        sender.sendMessage("Too few arguments, use the command like this /whitelist on/off");
			        return true;
			         }
			      }
			        if(args.length == 1 && args[0].equalsIgnoreCase("on")){
			        plugin.getServer().setWhitelist(true);
			          Bukkit.broadcastMessage("The whitelist has been enabled!");
			        }
			        if(args.length == 1 && args[0].equalsIgnoreCase("off")){
        	    plugin.getServer().setWhitelist(false);
                Bukkit.broadcastMessage("The whitelist has been disabled!");
		        } else {
		        sender.sendMessage("You do not have permission to disable the whitelist");
		           }
			
			return false;
		}
}

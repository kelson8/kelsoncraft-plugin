package me.Kelson;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationCommand implements CommandExecutor {
	
   Commands plugin;

   public LocationCommand(Commands passedPlugin) 
   {
	this.plugin = passedPlugin;
   }

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{

	Player player = (Player) sender;
	
  	  
      if(cmd.getName().equalsIgnoreCase("location")) {
      if (args.length == 0) {
  	  Location loc = player.getLocation();
  	  sender.sendMessage("The world you are in is '" + player.getWorld().getName() + "'" + 
  			  " \nthe coords are " + ChatColor.YELLOW + "\nX: " + loc.getBlockX() + " \nY: " + loc.getBlockY() + " \nZ: " + loc.getBlockZ() + "\nYaw: " + loc.getYaw() + " (Rotation)" + "\nPitch: " + loc.getPitch() + " (Head angle)");
      return true;
      }
  	    if(args.length == 1) {
        //The other player code does not work atm, it has some sort of glitch in it or something.
  		Player target = Bukkit.getServer().getPlayer(args[0]);
  		//if the targetplayer is offline then this says "<playername> is not online!"
  		if (target == null) {
  		    sender.sendMessage(args[0] + " is not online!");
  		    return true;
  		    }
  		Player targetPlayer = player.getServer().getPlayer(args[0]);
  		
  		Location loc1 = targetPlayer.getLocation();
           sender.sendMessage("The world " + targetPlayer.getName() + " is in is " + "'" + targetPlayer.getWorld().getName() + "'" + "\nThe coords are" + ChatColor.YELLOW + "\nX: " + loc1.getBlockX() + "\nY: " + loc1.getBlockY() + "\nZ: " + loc1.getBlockZ() + "\nYaw: " + loc1.getYaw() + " (Rotation)" + "\nPitch: " + loc1.getPitch() + " (Head angle)");
        return true;
  	    }
  	 }
	return false;
   }
}

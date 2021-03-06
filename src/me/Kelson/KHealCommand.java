package me.Kelson;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KHealCommand implements CommandExecutor{

	Commands plugin;

	public KHealCommand(Commands passedPlugin) 
	{
		this.plugin = passedPlugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("kheal")){
		if (args.length == 0) {
			player.setFireTicks(0);
			player.setFoodLevel(20);
			player.setHealth(20.0);
			player.sendMessage(Commands.main + "You have been healed!");
		} else if (args.length == 1) {

			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
			    sender.sendMessage(Commands.main + args[0] + " is not online!");
			    return true;
			    }

			Player targetPlayer = player.getServer().getPlayer(args[0]);
			targetPlayer.setFireTicks(0);
			targetPlayer.setFoodLevel(20);
			targetPlayer.setHealth(20.0);
			player.sendMessage(Commands.main + "You have healed " + targetPlayer.getName() + "!");
			targetPlayer.sendMessage(Commands.main + "You have been healed by " + player.getName() + "!");
		    }
	    }
	 
		return false;
    }

}

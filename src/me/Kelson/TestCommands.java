package me.Kelson

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TestCommands implements CommandExecutor, Listener{
   
   public void onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
   if(cmd.getName().equalsIgnoreCase("testcmds") && sender.hasPermission("kelson-test.testcmds")){
     if(args.length == 0){
     sender.sendMessage("Test commands list: /testcmds test");
     return true;
     }
     if(args.length == 1 && args[0].equalsIgnoreCase("test"){
     sender.sendMessage("Hello user this command sends a message to you and does something else");
     
     if(args.length >1){
     sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Too many arguments!");
     }
     Player player = (Player) sender;
     if(player.isOp(){
       sender.sendMessage("Hello %s, your an op".replace("%s", player.getName);
       }
     }
     
   }
   return false;
   }

}

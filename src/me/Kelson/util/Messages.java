package me.Kelson.util;

import me.Kelson.Commands;

import org.bukkit.ChatColor;

public class Messages {
	public static Commands plugin;

	public static String NoPermissionError = ChatColor.DARK_RED + "Error: " + ChatColor.RED + "You do not have permission to use that command!";

	public static String CommandsThatAreWorking = "\n";

	public static String CommandsThatAreNotWorking = "/location does not show up when another player is specified, only one found so far";

	public static String TestCommands = "Working on Test Commands list!";
	
	public static String KelsonReloadCommandPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kelson-reloadPermissionMessage"));

	public static String CommandsThatAreNotWorking() {
		return CommandsThatAreNotWorking;
	}

	public static String NoPermissionError(){
		return NoPermissionError;
	}

	public static String CommandsThatAreWorking(){
		return CommandsThatAreWorking;
	}

	public static String TestCommands(){
		return TestCommands;
	}

	public static String KelsonReloadCommandPermission(){
		return KelsonReloadCommandPermission;
	}

}

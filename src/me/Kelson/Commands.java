package me.Kelson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.Kelson.test.TempTestCommands;
import me.Kelson.test.Test_Stuff;
import me.Kelson.util.Events;
import me.Kelson.util.Interface;
import me.Kelson.util.Messages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands extends JavaPlugin implements Listener, Interface{
	public final Logger logger = Logger.getLogger("Minecraft.KBP");
	public static Commands plugin;
	public String Question_mark = "?";
	public static final String main = ChatColor.GOLD + "[KBP]: " + ChatColor.GREEN;
	private String ServerRules = "http://tinyurl.com/kc-server-rules";
	private String ServerMotd = ChatColor.BLUE + getServer().getMotd();
	private String ServerInfo = "Hi, the owner of this server is " + ChatColor.AQUA + "kelson8\n"
			+ ChatColor.GREEN + "The servers website is " + ChatColor.AQUA + "http://tinyurl.com/pfkqoce\n"
			+ ChatColor.GREEN + "The forums is " + ChatColor.AQUA + "http://tinyurl.com/qz6fa8nn\n"
			+ ChatColor.GREEN + "Read the server rules at " + ChatColor.AQUA + "http://tinyurl.com/omgkzoe\n"
			+ ChatColor.GREEN + "The servers motd is " + ServerMotd
			
			;
	public Commands(){
		
	}
	public String Null = null;
	public String NewLine = "\n";
	public String PlayerMaxHealth = "20";
	public String PlayerMinHealth = "0";
	public int Test = 2;
	public int PlayerMaxHealthInt = 20;
	public int PlayerMinHealthInt = 0;
	public String PlayerMaxAndMinHealth = "Max health: " + PlayerMaxHealth + "\n" + "Min health: " + PlayerMinHealth;
	public String Total = PlayerMaxAndMinHealth;
	public String TotalMaxAndMinHealth(){
		return Total;
	}
	@SuppressWarnings("unused")
	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		if(player.getName().equals("kelson8")){
			player.chat("Hello everyone!");
    	player.sendMessage("Read the rules at " + ServerRules + " or you can read the rules in /rules");
	    }
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event){
		Player player = event.getPlayer();
		if(player.getName().equals("kelson8")){
			player.chat("Hello everyone!");
			player.sendMessage("Welcome back to the server kelson8!");
		}
	}

	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
        if(event.getItem().equals(Material.TNT) && !player.hasPermission("kelson.pickup.blocked.item") || !player.isOp()){
        	event.setCancelled(true);
        	player.sendMessage(ChatColor.translateAlternateColorCodes('^', getConfig().getString("PickupBlockedItemMessage").replace("*new_line*", "\n")));
        } else {
        	event.setCancelled(false);
        }
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		if(getConfig().getBoolean("BlockBreak", true));
		    if(player.hasPermission("kelson.block.break") || player.isOp()){
		    	event.setCancelled(false);
		    }
		    if(!(player.hasPermission("kelson.block.break") || player.isOp())){
		    	event.setCancelled(true);
		    	player.sendMessage("You do not have permission to build!");
		    }
		if(getConfig().getBoolean("BlockBreak", false)){
			event.setCancelled(false);
		}
	}

	@EventHandler
	 public void DispenseEvent(BlockDispenseEvent event){
		 
	}

    	@EventHandler
    	  public void onFoodChange(FoodLevelChangeEvent event)
    	  {
    		//If the player has permission or if the player is op it disables their hunger
    	    if ((event.getEntity().hasPermission("no.hunger")) || (event.getEntity().isOp())) {
    	      event.setCancelled(true);
    	    } else {
    	      event.setCancelled(false);
    	    }
    	
    }
    	@EventHandler
    	public void onPlayerChat(AsyncPlayerChatEvent event) {
    		Player player = event.getPlayer();
    	
    	      if(event.getMessage().contains(".help") && player.hasPermission("kelson.secret.commands.help")){
    	    	  player.sendMessage("Secret command usage: .help");
    	    	  event.setCancelled(true);
    	      }
    	}
    	@EventHandler
    	public void onPlayerMove(PlayerMoveEvent event){
    		Player player = event.getPlayer();
    		if(getConfig().getBoolean("PlayerMove", false)){
    			if(player.hasPermission("kelson.move") || player.isOp()){
    				event.setCancelled(false);
    			}
    			if(!(player.hasPermission("kelson.move") || player.isOp())){
    				event.setCancelled(true);
    			}
    		if(getConfig().getBoolean("PlayerMove", true)){
    			    event.setCancelled(false);
    		    }
    		}
    	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() + " Has Been Disabled!");
		saveConfig();
		
	}
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Test_Stuff(), this);

		if(!(getDataFolder().exists())){
			logger.log(Level.INFO, "The folder for this plugin was not found! creating one for you");
			getDataFolder().mkdirs();
			try {
				getDataFolder().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " v" + pdfFile.getVersion() +  " Has Been Enabled!");
	    this.getCommand("kwhitelist").setExecutor(new WhitelistCommand(this));
	    this.getCommand("tempfly").setExecutor(new TempflyCommand(this));
	    this.getCommand("kheal").setExecutor(new KHealCommand(this));
	    this.getCommand("test").setExecutor(new TestCommand(this));
	    this.getCommand("location").setExecutor(new LocationCommand(this));
	    this.getCommand("kelson-reload").setExecutor(new me.Kelson.util.KelsonReloadCommand(this));
	    this.getCommand("kmotd").setExecutor(new KMotdCommand(this));
	    this.getCommand("setkmotd").setExecutor(new SetKMotdCommand(this));
	    this.getCommand("playerinfo").setExecutor(this);
	    
		getConfig().options().copyDefaults(true);
		saveConfig();


	}
	private PluginDescriptionFile pdfFile = this.getDescription();
	private String version = pdfFile.getVersion();

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		//The "=" indicates the start of the string, the start of it is after the "{"
		String[] KelsonCommandUsage = { 
			main + "Command Usage: \n"
			+ ChatColor.YELLOW + "/kelson version\n"
			 + ChatColor.YELLOW + "/kelson-reload (You have to have permission)"
			 + ChatColor.YELLOW + "/whitelist-list"
		};
						   
	  if(!(sender instanceof Player)){
		if(cmd.getName().equalsIgnoreCase("kelson")){
			sender.sendMessage(ServerInfo + "\n");
		  }
		if(cmd.getName().equalsIgnoreCase("kelson")){
			  
			   if(args.length == 0){
				   sender.sendMessage(main + "Command Usage: \n"
						   + ChatColor.YELLOW + "/kelson version\n"
						   + ChatColor.YELLOW + "/kelson-reload (You have to have permission)");
				          return true;
			        }

			   if(args[0].equalsIgnoreCase("version")){
				   sender.sendMessage(version);
			     }
			   String Version = Bukkit.getServer().getVersion();
			   if(cmd.getName().equals("version")){
				   sender.sendMessage(Version);
			   }
			   if(args.length >1){
				   sender.sendMessage("Too many arguments! Command help: \n" + KelsonCommandUsage);
			     }
		       }
		}
		Player player = (Player) sender;
		
		   if(cmd.getName().equalsIgnoreCase("serverinfo")){
	    	   sender.sendMessage(ServerInfo);
	       }
		   if(cmd.getName().equalsIgnoreCase("kelson")){
			  
			   if(args.length == 0){
				   sender.sendMessage(main + ChatColor.AQUA + "======Command Usage====== \n"
						   + ChatColor.YELLOW + "/kelson version\n"
						   + ChatColor.YELLOW + "/kelson cmdsNotWorking (Shows the commands not working in this plugin)\n"
						   + ChatColor.YELLOW + "/kelson testCommands (Shows the test commands in this plugin)\n"
						   + ChatColor.YELLOW + "/kelson-reload (You have to have permission)"
						   + ChatColor.AQUA + "=====Command Usage====="
						   
						   
						   );
				          return true;
			        }

			   if(args[0].equalsIgnoreCase("version")){
				   sender.sendMessage(version);
			     }
			   if(args[0].equalsIgnoreCase("cmdsNotWorking")){
				   sender.sendMessage("These are the commands that are not working: " + Messages.CommandsThatAreNotWorking);
			     }
			   if(args[0].equalsIgnoreCase("TestCommands")){
				   sender.sendMessage("These are the test commands: " + Messages.TestCommands);
			     }//If the player has too much arguments it sends them the help message
			   if(args.length >1){
				   sender.sendMessage("Too many arguments! Command help: \n" + KelsonCommandUsage);
			     }
			   String Version = Bukkit.getServer().getVersion();
			   String kbpCommandUsage = "/kbp version\n/kbp help\n";
			   if(cmd.getName().equals("kbp")){
				   if(args.length <1){
					   sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "not enough arguments! Command usage: " + kbpCommandUsage);
				   }
				   if(args.length == 1 && args[0].equalsIgnoreCase("version")){
				   sender.sendMessage(Version);
				   }
				   if(args.length >1){
					   sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "too many arguments! Command usage: " + plugin.getConfig().getString("KBPUsage")
							   .replace("%s", player.getName()));
					   
				   }
			     }
		       }
	        
	        if(commandLabel.equalsIgnoreCase("ipbans") && sender.hasPermission("kelson.ipbans")){
	        	
	        	sender.sendMessage("ip bans: " + Bukkit.getIPBans());
	        	this.getCommand("ipbans").setExecutor(this);
	        	return true;
	        }
	        if(cmd.getName().equalsIgnoreCase("kelson-help") && sender.hasPermission("kelson.help")){
	        	sender.sendMessage(ChatColor.YELLOW + "-------------------------\n"
	        			+ ChatColor.YELLOW + "This is the help for my plugin.\n"
	        			+ ChatColor.YELLOW + "-------------------------\n"
	        			+ ChatColor.BLUE + "Command 1: " + ChatColor.GOLD + "/kheal [playername] this heals the player, heals the targetplayer if specified\n"
	        			+ ChatColor.BLUE + "Command 2: " + ChatColor.GOLD + "/tempfly <off, on> this will turn on and off a players tempfly\n"
	        			+ ChatColor.BLUE + "Command 3: " + ChatColor.GOLD + "/disablewhitelist this will disable the whitelist\n"
	        			+ ChatColor.BLUE + "Command 4: " + ChatColor.GOLD + "/enablewhitelist this will enable the whitelist\n"
	        			+ ChatColor.BLUE + "Command 5: " + ChatColor.GOLD + "/kmotd this command shows the motd to the players\n"
	        		    + ChatColor.BLUE + "Command 6: " + ChatColor.GOLD + "/location [playername] this command shows your location if no player is specified and shows a players location if a player is specified\n"
	        		    + ChatColor.BLUE + "Command 7: " + ChatColor.GOLD + "/kelson testCommands Shows the test commands in this plugin\n"
	        		    + ChatColor.BLUE + "Command 8: " + ChatColor.GOLD + "/kbp-reload Command Description: This reloads KBP's Config.yml" + " Permission: kelson.reload"
	        			+ ChatColor.YELLOW + "-------------------------\n"
	        			+ ChatColor.YELLOW + "There might be more commands that is in the plugin here later!\n"
	        			+ ChatColor.YELLOW + "-------------------------\n"
	        			
	        			);
	        	return true;
	        }

	        if(cmd.getName().equalsIgnoreCase("playerinfo") && sender.hasPermission("kelson.playerinfo")){
	        	//Coming soon, offline players ex: type a playername that is offline such as /playerinfo offlinePlayer would then work
	        	if (args.length == 0) {
	        	sender.sendMessage("You must specify a player like /playerinfo <playername> to view others info\n Your player info:\n"
	        	        + ChatColor.GREEN + "Gamemode: " + ChatColor.AQUA + player.getGameMode() + "\n"
	        			+ ChatColor.GREEN + "Location: " + ChatColor.AQUA + player.getWorld().getName() + "\n"
	        			+ ChatColor.GREEN + "Op: " + ChatColor.AQUA + player.getPlayer().isOp() + "\n"
	        			+ ChatColor.GREEN + "Banned: " + ChatColor.AQUA + player.getPlayer().isBanned() + "\n"
	        			+ ChatColor.GREEN + "Online: " + ChatColor.AQUA + player.getPlayer().isOnline() + "\n"
	        			+ ChatColor.GREEN + "Ip Address: " + ChatColor.AQUA + player.getAddress().getAddress() + "\n"
	        			+ ChatColor.GREEN + "Name: " + ChatColor.AQUA + player.getCustomName() + "\n"
	        			+ ChatColor.GREEN + "Health: " + ChatColor.AQUA + player.getHealth());
	        	return true;
	        	    }
	        	} if (args.length == 1 && sender.hasPermission("kelson.playerinfo.others")) {
	        		Player targetPlayer = player.getServer().getPlayer(args[0]);
	        		
	        	sender.sendMessage(ChatColor.AQUA + targetPlayer.getName() + "'s playerinfo: \n"
	        			+ ChatColor.GREEN + "Gamemode: " + ChatColor.AQUA + targetPlayer.getGameMode() + "\n"
	        			+ ChatColor.GREEN + "Location: " + ChatColor.AQUA + targetPlayer.getWorld().getName() + "\n"
	        			+ ChatColor.GREEN + "Op: " + ChatColor.AQUA + targetPlayer.getPlayer().isOp() + "\n"
	        			+ ChatColor.GREEN + "Banned: " + ChatColor.AQUA + targetPlayer.getPlayer().isBanned() + "\n"
	        			+ ChatColor.GREEN + "Online: " + ChatColor.AQUA + targetPlayer.getPlayer().isOnline() + "\n"
	        			+ ChatColor.GREEN + "Ip Address: " + ChatColor.AQUA + targetPlayer.getAddress().getAddress() + "\n"
	        			+ ChatColor.GREEN + "Name: " + ChatColor.AQUA + targetPlayer.getCustomName() + "\n"
	        			+ ChatColor.GREEN + "Health: " + ChatColor.AQUA + targetPlayer.getHealth()
	        			);
	        			return true;
      }
		return false;
   }
}

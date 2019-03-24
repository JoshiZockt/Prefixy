package de.prefixy.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.prefixy.commands.PrefixyCommand;
import de.prefixy.utils.Messages;
import de.prefixy.utils.Prefix;
import de.prefixy.utils.PrefixAPI;


public class Main extends JavaPlugin{
	
	public static String Prefix;
	public static Prefix errorPrefix;
	public static Prefix defaultPrefix;
	public static String Format,
	                     TabFormat,
	                     Perms,
	                     NoPerms,
	                     Help_1,
	                     Help_2,
	                     Help_3,
	                     Help_4,
	                     Help_5,
	                     boolean_false,
	                     boolean_true,
	                     list,
	                     IllegalArgument,
	                     SuccessfulDeactivatedPrefix,
	                     NotOnline;
	
	private static Main plugin;
	
	public void onEnable() {
	
		plugin = this;
		
		loadConfig();
		
		Prefix = getConfig().getString("Prefix").replaceAll("&", "§");
		errorPrefix = new Prefix("error", 0, "4", true);
		defaultPrefix = new Prefix("default", 1, Messages.defaultPrefixColor, false);
		Format = getConfig().getString("Format").replaceAll("&", "§");
		TabFormat = getConfig().getString("Tablist Format").replaceAll("&", "§");
		Perms = getConfig().getString("Permissions");
		NoPerms = getConfig().getString("NoPerms");

		Help_1 = getConfig().getString("Help.1").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		Help_2 = getConfig().getString("Help.2").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		Help_3 = getConfig().getString("Help.3").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		Help_4 = getConfig().getString("Help.4").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		Help_5 = getConfig().getString("Help.5").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		
		list = getConfig().getString("List").replaceAll("&", "§");

		boolean_true = getConfig().getString("Booleans.true").replaceAll("&", "§");
		boolean_false = getConfig().getString("Booleans.false").replaceAll("&", "§");
		
		SuccessfulDeactivatedPrefix = getConfig().getString("SuccessfulDeactivatedPrefix").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		
		IllegalArgument = getConfig().getString("IllegalArgument").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		
		NotOnline = getConfig().getString("Spieler is not Online").replaceAll("&", "§").replaceAll("%prefix%", Prefix);
		
		Bukkit.getPluginManager().registerEvents(new PrefixAPI(), this);
		
		getCommand("prefixy").setExecutor(new PrefixyCommand());
		
		Bukkit.broadcastMessage(Prefix + "§e§lPrefixy enabled");
		
	}
	
	private final void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        
        Messages.load();
		
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public static boolean isNumeric(String str) {  
	  try {  
	    @SuppressWarnings("unused")
		double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}

}

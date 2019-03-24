package de.prefixy.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.prefixy.main.Main;

public class Database {
	
	public File prefixFile = new File("plugins//Prefixy//prefixs.yml");
	public YamlConfiguration prefixCfg = YamlConfiguration.loadConfiguration(prefixFile);
	
	public File playersFile = new File("plugins//Prefixy//players.yml");
	public YamlConfiguration playersCfg = YamlConfiguration.loadConfiguration(playersFile);
	
	public Database() {  }
	
	public void putPrefix(String Name, String ColorCodeAsInteger, boolean bold, int id) {
		
		if(prefixCfg.get("IDs." + String.valueOf(id)) == null) {

			prefixCfg.set("IDs." + id + ".Name", Name);
			prefixCfg.set("IDs." + id + ".Color", ColorCodeAsInteger);
			prefixCfg.set("IDs." + id + ".Bold", bold);
			prefixCfg.set("IDs." + id + ".Activated", true);
			
			try {
				prefixCfg.save(prefixFile);
			} catch (IOException e) { new Logger().sendError(e.getMessage() + " ERROR WHILE SAVING prefixs.yml !!!PLEASE CONTACT THE OWNER OF THIS PLUGIN!!!"); }
			
		}
		
	}
	
	public boolean havePlayerAnyPrefix(Player player) {
		
		if(playersCfg.get(player.getUniqueId().toString()) == null) {
			return false;
		} else
			return true;
		
	}
	
	public void setPrefixToPlayer(Player player, Prefix prefix) {
		
		playersCfg.set(player.getUniqueId().toString(), prefix.getID());
		
		try {
			playersCfg.save(playersFile);
		} catch (IOException e) {  new Logger().sendError(e.getMessage() + "ERROR WHILE SAVING THE FILE players.yml !!!FIX IT!!!");  }
		
	}
	
	public Prefix getPlayersPrefix(Player player) {
		
		if(havePlayerAnyPrefix(player) == false) {
			return null;
		} else {
			
			return getPrefixByID(playersCfg.getInt(player.getUniqueId().toString()));
			
		}
		
	}
	
	public void setPrefixNameByID(int id, String newName) {
		if(prefixCfg.getBoolean("IDs." + id + ".Activated") == false || prefixCfg.get("IDs" + id + ".Activated") == null) {
			
			new Logger().sendError("Error while get old name für prefix with ID '" + id + "'! Cant set a new name for this prefix because the prefix don't exists! This error message will only displayed to console \n" + "ERROR WHILE SET NAME FOR A PREFIX! !!!FIX IT!!!");
			
		} else {
			
			prefixCfg.set("IDs." + id + ".Name", newName);
			
			try {
				prefixCfg.save(prefixFile);
			} catch (IOException e) { new Logger().sendError(e.getMessage() + "ERROR WHILE SAVING THE FILE prefixs.yml !!!FIX IT!!!"); }
			
		}
	}
	
	public Prefix getPrefixByID(int id) {
		
		if(prefixCfg.getBoolean("IDs." + id + ".Activated") == false || prefixCfg.get("IDs." + id + ".Activated") == null) {
			new Logger().sendError("Error while get prefix with id '" + id + "'");
			return Main.errorPrefix;
		} else {
			return new Prefix(prefixCfg.getString("IDs." + id + ".Name"), id, prefixCfg.getString("IDs." + id + ".Color"), prefixCfg.getBoolean("IDs." + id + ".Bold"));
		}
		
	}
	
	public void deactivatePrefixByID(int id) {
		
		if(prefixCfg.getBoolean("IDs." + id + ".Activated") == false || prefixCfg.get("IDs." + id + ".Activated") == null) {
			new Logger().sendError("Can't remove Prefix with ID '" + id + "' is already removed!");
		} else {
			
			prefixCfg.set("IDs." + id + ".Activated", false);
			
			try {
				prefixCfg.save(prefixFile);
			} catch (IOException e) { new Logger().sendError(e.getMessage() + "ERROR WHILE SAVING THE FILE prefixs.yml !!!FIX IT!!!"); }
			
		}
		
	}

}

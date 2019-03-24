package de.prefixy.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	
	public static String defaultPrefixColor;
	public static String Prefix;
	
	public static void load() {

        final File file = new File("plugins/Prefixy/config.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		Prefix = cfg.getString("Prefix").replaceAll("&", "§");
		defaultPrefixColor = cfg.getString("defaultPrefix.Color");
				
	}

}

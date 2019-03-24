package de.prefixy.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prefixy.main.Main;
import de.prefixy.utils.Database;
import de.prefixy.utils.Prefix;

public class PrefixyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player)sender;
			
			if(p.hasPermission(Main.Perms)) {
				
				if(args.length == 0) {
					
					p.sendMessage("");
					p.sendMessage("§e§lPrefixy by JoshiZockt_ aka. JoshiCodes_");
					p.sendMessage("§cBitte nutze /prefixy help");
					p.sendMessage("");
					
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("help")) {
						
						sendHelp(p);
						
					} else if(args[0].equalsIgnoreCase("list")) {
						
						for(String str : new Database().prefixCfg.getConfigurationSection("IDs").getKeys(false)) {
						int nr = Integer.parseInt(str);
						String mainpath = "IDs." + nr;

						String id = String.valueOf(nr);
						String name = new Database().prefixCfg.getString(mainpath + ".Name");
						String color = new Database().prefixCfg.getString(mainpath + ".Color");
						boolean bold = new Database().prefixCfg.getBoolean(mainpath + ".Bold");
						boolean isActivated = new Database().prefixCfg.getBoolean(mainpath + ".Activated");
						
						String bold_name = null;
						String activated_name = null;
						
						if(bold == true) {
							bold_name = Main.boolean_true;
						} else 
							bold_name = Main.boolean_false;
						
						if(isActivated == true) {
							activated_name = Main.boolean_true;
						} else
							activated_name = Main.boolean_false;
						
						p.sendMessage(Main.list.replaceAll("%name%", name).replaceAll("%color%", color).replaceAll("%bold%", bold_name).replaceAll("%activated%", activated_name).replaceAll("%id%", id));
						
						}
						
					} else {
						
						sendHelp(p);
						
					}
				} else if(args.length == 2) {
					//arg arg0 arg1
					//prefixy remove <id>
					
					if(args[0].equalsIgnoreCase("remove")) {
						
						if(Main.isNumeric(args[1]) == false) {
							p.sendMessage(Main.IllegalArgument.replaceAll("%type%", "Zahl"));
						} else {
							
							Prefix prefix = new Database().getPrefixByID(Integer.parseInt(args[1]));
							
							new Database().deactivatePrefixByID(Integer.parseInt(args[1]));
							
							p.sendMessage(Main.SuccessfulDeactivatedPrefix.replaceAll("%id%", String.valueOf(prefix.getID())).replaceAll("%name%", prefix.getName()).replaceAll("%color%", prefix.getColorCode()));
							
						}
						
					} else
						sendHelp(p);
					
				} else if(args.length == 3) {
					//args args0 args1 args2
					///prefixy set <player> <id>
					
					if(args[0].equalsIgnoreCase("set")) {
						
						Player target = Bukkit.getPlayer(args[1]);
						
						if(target != null) {
							
							if(!Main.isNumeric(args[2])) {
								p.sendMessage(Main.IllegalArgument.replaceAll("%type%", "Zahl"));
							} else {
								
								new Database().setPrefixToPlayer(target, new Database().getPrefixByID(Integer.parseInt(args[2])));
								
							}
							
						} else
							p.sendMessage(Main.NotOnline);
						
					}
					
				}
				
			} else
				p.sendMessage(Main.NoPerms);
			
		}
		
		return false;
	}

	private void sendHelp(Player p) {
		
		p.sendMessage("");
		p.sendMessage(Main.Help_1);
		p.sendMessage(Main.Help_2);
		p.sendMessage(Main.Help_3);
		p.sendMessage(Main.Help_4);
		p.sendMessage(Main.Help_5);
		p.sendMessage("");
		
	}

}

package de.prefixy.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.prefixy.main.Main;

public class PrefixAPI implements Listener {
	
	public PrefixAPI() {}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
	
		Database base = new Database();
		
		if(base.havePlayerAnyPrefix(e.getPlayer()) == false) {
			
			if(base.getPrefixByID(1).isBold() == false) {
				e.setFormat(base.getPrefixByID(1).getColorCode() + Main.Format.replaceAll("%message%", e.getMessage()).replaceAll("%player%", e.getPlayer().getName().replaceAll("%displayname%", e.getPlayer().getDisplayName())));
			} else {
				e.setFormat(base.getPrefixByID(1).getColorCode() + "§l" + Main.Format.replaceAll("%message%", e.getMessage()).replaceAll("%player%", e.getPlayer().getName().replaceAll("%displayname%", e.getPlayer().getDisplayName())));
			}
			
		} else {
			
			Prefix prefix = base.getPlayersPrefix(e.getPlayer());
			
			if(base.getPrefixByID(prefix.getID()).isBold() == false) {
				e.setFormat(prefix.getColorCode() + Main.Format.replaceAll("%message%", e.getMessage()).replaceAll("%player%", e.getPlayer().getName()).replaceAll("%displayname%", e.getPlayer().getDisplayName()));
			} else {
				e.setFormat(prefix.getColorCode() + "§l" + Main.Format.replaceAll("%message%", e.getMessage()).replaceAll("%player%", e.getPlayer().getName().replaceAll("%displayname%", e.getPlayer().getDisplayName())));
			}
			
		}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = (Player)e.getPlayer();
		
		Database base = new Database();
		
		if(base.havePlayerAnyPrefix(p) == false) {
			
			if(base.getPrefixByID(1).isBold() == false) {
				p.setPlayerListName(base.getPrefixByID(1).getColorCode() + Main.TabFormat.replaceAll("%player%", p.getName()).replaceAll("%displayname%", p.getDisplayName()));
			} else 
				p.setPlayerListName(base.getPrefixByID(1).getColorCode() + "§l" + Main.TabFormat.replaceAll("%player%", p.getName()).replaceAll("%displayname%", p.getDisplayName()));
			
		} else {
			
			Prefix prefix = base.getPlayersPrefix(p);
			
			if(prefix.isBold() == false) {
				p.setPlayerListName(prefix.getColorCode() + Main.TabFormat.replaceAll("%player%", p.getName()).replaceAll("%displayname%", p.getDisplayName()));
			} else 
				p.setPlayerListName(prefix.getColorCode() + "§l" + Main.TabFormat.replaceAll("%player%", p.getName()).replaceAll("%displayname%", p.getDisplayName()));
			
			
		}
		
	}

}

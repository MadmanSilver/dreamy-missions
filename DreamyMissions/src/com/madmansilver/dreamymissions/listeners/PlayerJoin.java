package com.madmansilver.dreamymissions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.madmansilver.dreamymissions.Main;

public class PlayerJoin implements Listener{

	private static Main plugin;
	
	public PlayerJoin(Main main) {
		plugin = main;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (!plugin.statman.checkPlayer(p.getUniqueId().toString())) {
			plugin.statman.addPlayer(p.getUniqueId().toString());
		}
		//plugin.statman.incrementStat(p.getUniqueId().toString(), "joins");
	}
}

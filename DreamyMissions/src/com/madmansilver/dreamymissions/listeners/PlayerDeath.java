package com.madmansilver.dreamymissions.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.madmansilver.dreamymissions.Main;

public class PlayerDeath implements Listener{

	private static Main plugin;
	
	public PlayerDeath(Main main) {
		plugin = main;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		
		plugin.statman.incrementStat(p.getUniqueId().toString(), "deaths");
		if (p.getKiller() != null) {
			plugin.statman.incrementStat(p.getUniqueId().toString(), "deaths.from" + p.getKiller().getUniqueId());
			plugin.statman.incrementStat(p.getKiller().getUniqueId().toString(), "kills." + p.getUniqueId());
		} else {
			plugin.statman.incrementStat(p.getUniqueId().toString(), "deaths.from" + p.getLastDamageCause().getEntity().getName().toLowerCase());
		}
	}
}

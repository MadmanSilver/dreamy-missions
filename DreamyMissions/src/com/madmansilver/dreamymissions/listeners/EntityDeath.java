package com.madmansilver.dreamymissions.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.madmansilver.dreamymissions.Main;

public class EntityDeath implements Listener{

	private static Main plugin;
	
	public EntityDeath(Main main) {
		plugin = main;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity e = event.getEntity();
		
		if (e.getKiller() != null) {
			plugin.statman.incrementStat(e.getKiller().getUniqueId().toString(), "kills");
			if (e.getType() != EntityType.PLAYER) {
				plugin.statman.incrementStat(e.getKiller().getUniqueId().toString(), "kills." + e.getName().toLowerCase());
			}
		}
	}
}

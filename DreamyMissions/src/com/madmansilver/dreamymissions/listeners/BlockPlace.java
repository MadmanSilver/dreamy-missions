package com.madmansilver.dreamymissions.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.madmansilver.dreamymissions.Main;

public class BlockPlace implements Listener {

	private static Main plugin;
	
	public BlockPlace(Main main) {
		plugin = main;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlock();
		String s = b.getBlockData().getAsString();
		
		if (s.indexOf('[') != -1) {
			plugin.statman.incrementStat(p.getUniqueId().toString(), "blockplace." + s.substring(0, s.indexOf('[')));
		} else {
			plugin.statman.incrementStat(p.getUniqueId().toString(), "blockplace." + s);
		}
	}
}

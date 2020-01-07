package com.madmansilver.dreamymissions;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.madmansilver.dreamymissions.commands.Stats;
import com.madmansilver.dreamymissions.listeners.PlayerJoin;

public class Main extends JavaPlugin implements Listener{

	public boolean missionActive = true;
	public Mission currentMission = new Mission("love");
	public StatManager statman = new StatManager();
	
	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		
		pm.registerEvents(new PlayerJoin(this), this);
		/*pm.registerEvents(new BlockBreak(this), this);
		pm.registerEvents(new BlockPlace(this), this);
		pm.registerEvents(new EntityDeath(this), this);
		pm.registerEvents(new PlayerDeath(this), this);*/
		this.getServer().getPluginManager().registerEvents(this, this);
		
		this.saveDefaultConfig();
		regCmds();
		
		//runnable();
	}
	
	@Override
	public void onDisable() {
		statman.saveStats();
	}
	
	public void regCmds() {
		this.getCommand("stats").setExecutor(new Stats(this));
	}
	
	/*public void runnable() {
		new BukkitRunnable() {

			@Override
			public void run() {
				getServer().broadcastMessage("Updating Stats...");
				for (Player p : getServer().getOnlinePlayers()) {
					statman.setStat(p.getUniqueId().toString(), "deaths", p.getStatistic(Statistic.DEATHS));
					for (Material m : Material.values()) {
						if (!m.toString().contains("LEGACY")) {
							statman.setStat(p.getUniqueId().toString(), "craft." + m.toString(), p.getStatistic(Statistic.CRAFT_ITEM, m));
						}
					}
				}
			}
		}.runTaskTimerAsynchronously(this, 0, 100);
	}*/
}

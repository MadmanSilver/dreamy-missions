package com.madmansilver.dreamymissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.madmansilver.dreamymissions.Main;

public class Stats implements CommandExecutor {

	static Main plugin;
	
	public Stats(Main main) {
		plugin = main;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("stats")) {
			if (args.length == 1 || args.length == 2) {
				Player p = null;
				String stat = args[0];
				String spec = null;
				
				if (stat.indexOf('.') != -1) {
					stat = args[0].substring(0, args[0].indexOf('.'));
					spec = args[0].substring(args[0].indexOf('.') + 1);
				}
				
				if (sender instanceof Player) {
					p = (Player) sender;
				}
				
				if (args.length == 2) {
					p = Bukkit.getPlayer(args[1]);
					
					if (p == null) {
						sender.sendMessage(ChatColor.RED + "Player not found!");
						return true;
					}
				}
				
				if (p == null) {
					sender.sendMessage(ChatColor.YELLOW + "Global stats not supported yet!");
					return true;
				}

				try {
					//sender.sendMessage(p.getName() + "'s " + args[0] + " stat: " + plugin.statman.getStat(p.getUniqueId().toString(), args[0]));
					if (spec == null) {
						sender.sendMessage(p.getName() + "'s " + stat + " stat: " + p.getStatistic(plugin.statman.statFromString(stat)));
					} else if (plugin.statman.matFromString(spec) == null) {
						sender.sendMessage(p.getName() + "'s " + stat + " for " + spec + " stat: " + p.getStatistic(plugin.statman.statFromString(stat), plugin.statman.typeFromString(spec)));
					} else {
						sender.sendMessage(p.getName() + "'s " + stat + " for " + spec + " stat: " + p.getStatistic(plugin.statman.statFromString(stat), plugin.statman.matFromString(spec)));
					}
				} catch (NullPointerException | IllegalArgumentException e) {
					sender.sendMessage(ChatColor.YELLOW + "That is not a supported stat!");
				}
				
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Correct usage: /stats <statType>.(material/entitytype) (player)" + ChatColor.YELLOW + " Example: /stats craft_item.stick MadmanSilver");
			return true;
		}
		return false;
	}
}

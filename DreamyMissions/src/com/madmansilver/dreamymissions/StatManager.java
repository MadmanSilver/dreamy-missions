package com.madmansilver.dreamymissions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class StatManager {

	private HashMap<String, HashMap<String, Double>> stats = new HashMap<String, HashMap<String, Double>>();
	private Gson gsonf = new GsonBuilder().setPrettyPrinting().create();
	private Gson gson = new GsonBuilder().create();
	
	public StatManager() {
		loadStats();
	}
	
	public void addPlayer(String id) {
		stats.put(id, new HashMap<String, Double>());
	}
	
	public boolean checkPlayer(String id) {
		if (stats.get(id) != null) {
			return true;
		}
		return false;
	}
	
	public double getStat(String id, String stat) {
		return stats.get(id).get(stat);
	}
	
	public void setStat(String id, String stat, double value) {
		stats.get(id).put(stat, value);
	}
	
	public void incrementStat(String id, String stat) {
		try {
			stats.get(id).put(stat, stats.get(id).get(stat) + 1.0);
		} catch (NullPointerException e) {
			stats.get(id).put(stat, 1.0);
		}
	}
	
	public void decrementStat(String id, String stat) {
		try {
			stats.get(id).put(stat, stats.get(id).get(stat) - 1.0);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadStats() {
		HashMap<String, String> s = new HashMap<String, String>();
		
		try {
			s = gsonf.fromJson(new FileReader("stats.json"), HashMap.class);
			Iterator it = s.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        stats.put(pair.getKey().toString(), gson.fromJson(pair.getValue().toString(), HashMap.class));
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		} catch (JsonIOException | IOException e) {
			System.out.println("No existing custom stat file found. One will be created upon server shutdown.");
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void saveStats() {
		HashMap<String, String> s = new HashMap<String, String>();
		
		try (FileWriter writer = new FileWriter("stats.json")) {
			Iterator it = stats.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        s.put(pair.getKey().toString(), gson.toJson(pair.getValue()));
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		    gsonf.toJson(s, writer);
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Statistic statFromString(String string) {
		for (Statistic stat : Statistic.values()) {
			if (stat.toString().equalsIgnoreCase(string)) {
				return stat;
			}
		}
		return null;
	}
	
	public Material matFromString(String string) {
		for (Material mat : Material.values()) {
			if (mat.toString().equalsIgnoreCase(string)) {
				return mat;
			}
		}
		return null;
	}
	
	public EntityType typeFromString(String string) {
		for (EntityType type : EntityType.values()) {
			if (type.toString().equalsIgnoreCase(string)) {
				return type;
			}
		}
		return null;
	}
}

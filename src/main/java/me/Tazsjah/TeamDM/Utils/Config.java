package me.Tazsjah.TeamDM.Utils;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class Config {
	
	// This is used to pull different strings in configuration & already color coded
	
	File f = new File("plugins/TeamDM/", "config.yml");
	FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	
	public String prefix() {
		return ChatColor.translateAlternateColorCodes('&', config.getString("prefix"));
	}
	
	public String joinMessage() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("join-message"));
	}
	
	public String leaveMessage() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("leave-message"));
	}
	
	public String denyMessage() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("deny-message"));
	}
	
	public String countdownMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("counting-msg"));
	}
	
	public String notEnough() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("not-enough"));
	}
	
	public String starting() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("starting"));
	}
	
	public int startTimer() {
		return config.getInt("start-timer");
	}
	
	public int gameTimer() {
		return config.getInt("game-timer");
	}
	
	public String slayerMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("slayer-message"));
	}
	public String dragonMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("dragon-message"));
	}
	public String slayerWinMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("slayer-win"));
	}
	public String dragonWinMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("dragon-win"));
	}
	public String gameDrawMsg() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("team-draw"));
	}
	public String endTimer() {
		return ChatColor.translateAlternateColorCodes('&', prefix() + config.getString("game-ending"));
	}
	
	@SuppressWarnings("unchecked")
	public List<String> helpMsg() {
		
		List<String> list = (List<String>) config.getList("help-message");
		
		for (String s : list) {
			list.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		
		return list;
		
		
	}

}

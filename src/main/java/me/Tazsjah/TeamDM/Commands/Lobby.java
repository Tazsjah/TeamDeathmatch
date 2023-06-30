package me.Tazsjah.TeamDM.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Managers.LocationManager;
import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class Lobby implements CommandExecutor {
	
	// This is how I set the spawn for the game
	
	LocationManager lm;
	
	Config config;
	
	public Lobby(Config config, LocationManager lm) {
		this.config = config;
		this.lm = lm;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(player.hasPermission("tdm.admin")) {
				
				lm.setLobby(player.getLocation());
				
				sender.sendMessage(config.prefix() + ChatColor.YELLOW + "The lobby has been set");
				
				
			} else {
				
				sender.sendMessage(config.prefix() + config.denyMessage());
				
			}
			
		}
		
		return false;
	}

}

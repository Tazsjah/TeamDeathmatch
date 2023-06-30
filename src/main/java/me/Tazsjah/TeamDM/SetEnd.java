package me.Tazsjah.TeamDM;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Managers.LocationManager;
import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class SetEnd implements CommandExecutor {
	
	Config config;
	LocationManager lm;
	
	public SetEnd(Config config, LocationManager lm) {
		this.config = config;
		this.lm = lm;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
	
		if(sender instanceof Player) {
			if(sender.hasPermission("tdm.admin")) {
				
				Player p = (Player) sender;
				
				lm.setEnd(p.getLocation());
				
				sender.sendMessage(ChatColor.YELLOW + "The location for the end has been set");
				
			} else {
				config.denyMessage();
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
		}
		
		return false;
	}

}

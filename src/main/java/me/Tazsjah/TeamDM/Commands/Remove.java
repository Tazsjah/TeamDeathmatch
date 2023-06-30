package me.Tazsjah.TeamDM.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Events.General;
import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class Remove implements CommandExecutor {

	Config config;
	General general;

	public Remove(Config config, General general) {
		this.config = config;
		this.general = general;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender.hasPermission("tdm.admin")) {

			if(args != null) {
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) {
					sender.sendMessage(ChatColor.RED + "Player cannot be found.");
					return true;
				}
				
				general.players.remove(target.getUniqueId());
				general.teams.remove(target.getUniqueId());
				target.kickPlayer(ChatColor.RED + "You have been removed from the game");
				String msg = ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8&l» &e" + target.getName() + " &7has been removed from the game");
				general.announce(msg);

			}


		} else {
			config.denyMessage();
		}

		return false;
	}

}

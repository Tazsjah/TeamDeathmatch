package me.Tazsjah.TeamDM.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Managers.LocationManager;
import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class SetPoint implements CommandExecutor {
	
	// This is how I set the 2 different spawn points for the teams

	LocationManager lm;
	Config config;

	public SetPoint(LocationManager lm, Config config) {
		this.lm = lm;
		this.config = config;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {

			Player player = (Player) sender;

			if(player.hasPermission("tdm.admin")){
				
				if(args.length == 0) {
					sender.sendMessage(config.prefix() + ChatColor.RED + "You must specify a point to set slayers or dragons");
					return true;
				}

				if(args[0].equalsIgnoreCase("slayers")) {

					lm.setA(player.getLocation());

					sender.sendMessage(config.prefix() + ChatColor.RED + "Set location for the " + ChatColor.WHITE + "Slayers");

					return true;

				}

				if(args[0].equalsIgnoreCase("dragons")) {

					lm.setB(player.getLocation());

					sender.sendMessage(config.prefix() + ChatColor.RED + "Set location for the " + ChatColor.DARK_PURPLE + "Dragons");

					return true;

				}


				sender.sendMessage(config.prefix() + ChatColor.RED + "You must specify a point to set slayers or dragons");
				return true;



			} else {

				player.sendMessage(config.prefix() + config.denyMessage());

			}

		}

		return false;
	}

}

package me.Tazsjah.TeamDM.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class Help implements CommandExecutor {

	// This is the help command

	Config config;

	public Help(Config config) {
		this.config = new Config();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {

			Player p = (Player) sender;

			if(p.hasPermission("tdm.admin")) {

				List<String> msg = new ArrayList<String>();

				msg.add("&r");
				msg.add("&f&lSlayers &c&lvs &5&lDragons");
				msg.add("&r");
				msg.add("&e/help &8- &7This command shows this message");
				msg.add("&e/setlobby &8- &7Sets the lobby for when a players joins the game");
				msg.add("&e/setpoint (slayers/dragons) &8- &7Sets the spawnpoint for each team");
				msg.add("&e/remove (player) &8- &7Remove a player from the game");
				msg.add("&e/forcestop &8- &7Force stops the current game if running");
				msg.add("&e/forcestart &8- &7Force starts the game if stopped");
				
				for (String s : msg) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
				}

			} else {

				sender.sendMessage(config.denyMessage());

			}

		}

		return false;
	}

}


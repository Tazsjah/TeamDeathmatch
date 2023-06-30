package me.Tazsjah.TeamDM.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.Tazsjah.TeamDM.Events.General;
import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Utils.Config;
import net.md_5.bungee.api.ChatColor;

public class ForceStart implements CommandExecutor {
	Config config;
	General general;

	public ForceStart(Config config, General general) {
		this.config = config;
		this.general = general;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("tdm.admin")) {
			if(!GameState.isState(GameState.GAME)) {
				general.teampoints.put("slayers", 0);
				general.teampoints.put("dragons", 0);
				general.startGame();
				sender.sendMessage(ChatColor.YELLOW + "The game has been forcefully started");

			} else {
				sender.sendMessage(ChatColor.RED+ "The game has been started already");
			}

		} else {
			sender.sendMessage(config.denyMessage());
		}
		return false;
	}
}

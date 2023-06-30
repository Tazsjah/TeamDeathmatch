package me.Tazsjah.TeamDM.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tazsjah.TeamDM.Events.General;
import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Utils.Config;
import me.Tazsjah.TeamDM.Utils.Sidebar;
import net.md_5.bungee.api.ChatColor;

public class ForceStop implements CommandExecutor {
	
	Config config;
	General general;
	Sidebar bar;
	
	public ForceStop(Config config, General general, Sidebar bar) {
		this.config = config;
		this.general = general;
		this.bar = bar;
	}
	
	private void resetBar() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			bar.setScore(p, 0, null);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("tdm.admin")) {
			if(!GameState.isState(GameState.HALT)) {
			GameState.setState(GameState.HALT);
			general.gameTimer = 30;
			general.teams.clear();
			general.players.clear();
			general.endMethod();
			sender.sendMessage(ChatColor.RED + "The game has been forcefully stopped");
			resetBar();
			} else {
				sender.sendMessage(ChatColor.RED + "Game has been stopped already");
			}
		} else {
			sender.sendMessage(config.denyMessage());
		}
		return false;
	}

}

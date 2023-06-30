package me.Tazsjah.TeamDM.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class HowTo implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Welcome to &eSlayers &cv &5Dragons"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Version: &cV1.0 by PvPTime"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7The objective of this game is to get"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7the most kills before the timer runs out."));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " "));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7play.pvptime.net"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9Report issues to our discord"));
		}
		return false;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.getPlayer().performCommand("howto");
	}
	

}

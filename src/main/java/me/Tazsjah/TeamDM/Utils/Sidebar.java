package me.Tazsjah.TeamDM.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.Tazsjah.TeamDM.Managers.GameState;
import net.md_5.bungee.api.ChatColor;

public class Sidebar {
	
	// This is my Score-Board 

	public void setScore(Player p, int timer, String pteam) {
		
		if(GameState.isState(GameState.HALT)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD   "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.RED + "Stopped");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}

		if(GameState.isState(GameState.WAITING)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD   "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.RED + "Waiting...");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}
		
		if(GameState.isState(GameState.COUNTING)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD"));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.RED + "Starting in " + ChatColor.RED  + timer + ChatColor.RED + "s");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}

		if(GameState.isState(GameState.GAME)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD  "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.BLACK + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(4);

			Team gameTime = board.registerNewTeam("gameTime");
			gameTime.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			gameTime.setPrefix(ChatColor.RED + "Ends in " + ChatColor.RED + timer + ChatColor.RED + "s");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			Score team = obj.getScore(ChatColor.GRAY + "Team:");
			team.setScore(11);

			Team myTeam = board.registerNewTeam("myTeam");
			myTeam.addEntry(ChatColor.BLUE + "" + ChatColor.GREEN);
			myTeam.setPrefix(ChatColor.translateAlternateColorCodes('&', pteam));
			obj.getScore(ChatColor.BLUE + "" + ChatColor.GREEN).setScore(10);

			Score spacer3 = obj.getScore(ChatColor.YELLOW + " ");
			spacer3.setScore(9);
			
			Score dScoret = obj.getScore(ChatColor.GRAY + "Points:");
			dScoret.setScore(8);
			
			Team sScore = board.registerNewTeam("sScore");
			sScore.addEntry(ChatColor.RED + "" + ChatColor.GREEN);
			sScore.setPrefix(ChatColor.translateAlternateColorCodes('&', "&eSlayers&8: &c0"));
			obj.getScore(ChatColor.RED + "" + ChatColor.GREEN).setScore(7);
			
			Team dScore = board.registerNewTeam("dScore");
			dScore.addEntry(ChatColor.YELLOW + "" + ChatColor.GREEN);
			dScore.setPrefix(ChatColor.translateAlternateColorCodes('&', "&5Dragons&8: &c0"));
			obj.getScore(ChatColor.YELLOW + "" + ChatColor.GREEN).setScore(6);
			
			Score spacer4 = obj.getScore(ChatColor.RED + " ");
			spacer4.setScore(5);

			p.setScoreboard(board);
		}
		
		if(GameState.isState(GameState.SLAYERSW)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD   "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.YELLOW + "Slayers Win!");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}
		
		if(GameState.isState(GameState.DRAGONW)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD   "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.DARK_PURPLE + "Dragons Win!");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}
		
		if(GameState.isState(GameState.DRAW)) {

			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			Objective obj = board.registerNewObjective("PvP", "dummy", ChatColor.translateAlternateColorCodes('&', "&c&lPvP&4&lTime &8» &e&lSvD   "));
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);

			Score status = obj.getScore(ChatColor.GRAY + "Status:");
			status.setScore(14);

			Score spacer1 = obj.getScore(ChatColor.RED + "");
			spacer1.setScore(15);

			Score spacer2 = obj.getScore(ChatColor.RED + " ");
			spacer2.setScore(12);

			Score ip = obj.getScore(ChatColor.GRAY + "play.pvptime.net");
			ip.setScore(11);

			Team currentStatus = board.registerNewTeam("currentStatus");
			currentStatus.addEntry(ChatColor.BLUE + "" + ChatColor.RED);
			currentStatus.setPrefix(ChatColor.GREEN + "Game is a Draw!");
			obj.getScore(ChatColor.BLUE + "" + ChatColor.RED).setScore(13);

			p.setScoreboard(board);
		}
		
	}

	public void updateScore(Player p, int timer, String team, int slayerscore, int dragonscore) {
		if(GameState.isState(GameState.COUNTING)) {
			Scoreboard board = p.getScoreboard();
			board.getTeam("currentStatus").setPrefix(ChatColor.RED + "Begins in " + ChatColor.RED  + timer + ChatColor.RED + "s");
		}
		
		if(GameState.isState(GameState.GAME)) {
			Scoreboard board = p.getScoreboard();
			board.getTeam("gameTime").setPrefix(ChatColor.RED + "Ends in " + ChatColor.RED  + timer + ChatColor.RED + "s");
			board.getTeam("sScore").setPrefix(ChatColor.translateAlternateColorCodes('&', "&eSlayers&8: &c" + slayerscore));
			board.getTeam("dScore").setPrefix(ChatColor.translateAlternateColorCodes('&', "&5Dragons&8: &c" + dragonscore));
		}
	}

}

package me.Tazsjah.TeamDM.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Tazsjah.TeamDM.Main;
import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Managers.KitManager;
import me.Tazsjah.TeamDM.Managers.LocationManager;
import me.Tazsjah.TeamDM.Utils.Config;
import me.Tazsjah.TeamDM.Utils.Sidebar;
import net.md_5.bungee.api.ChatColor;

public class General implements Listener {

	// Calling classes to use methods & global variables

	Config config;
	LocationManager lm;
	Sidebar bar;
	KitManager km;

	public General(Config config, LocationManager lm, Sidebar bar, KitManager km) {
		this.config = config;
		this.lm = lm;
		this.bar = bar;
		this.km = km;
	}

	// Initializing my variables
	
	public List<UUID> players = new ArrayList<UUID>(); // THIS IS THE LIST OF GAME PLAYERS
	public HashMap<UUID, String> teams = new HashMap<UUID, String>(); // THIS IS THE TEAMS SET
	public HashMap<String, Integer> teampoints = new HashMap<String, Integer>(); // THIS IS THE TEAM POINTS
	public int cdTimer = 10;
	public int gameTimer = 60;
	public int endTimer = 10;


	
	
	// These are my event handlers for join / leave


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		if(GameState.isState(GameState.GAME)) {
			event.setJoinMessage(config.prefix() + ChatColor.GRAY + event.getPlayer().getName() + "joined as a spectator");
			event.getPlayer().setGameMode(GameMode.SPECTATOR);
			return;
		}

		if(GameState.getState() == GameState.HALT) {
			GameState.setState(GameState.WAITING);
			teampoints.put("slayers", 0);
			teampoints.put("dragons", 0);
		}

		bar.setScore(event.getPlayer(), 0, "");
		event.setJoinMessage(null);
		Player p = event.getPlayer();
		players.add(p.getUniqueId());
		announce(config.joinMessage().replace("%player", p.getName()).replace("%status",  players.size() + ""));
		if(GameState.getState() == GameState.WAITING) {
			if(players.size() > 1) {
				GameState.setState(GameState.COUNTING);
				countdown();
			}
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage(null);
		if(GameState.getState() != GameState.HALT) {
			if(GameState.getState() != GameState.END) {
				Player p = event.getPlayer();
				players.remove(p.getUniqueId());

				if(stillInGame() == true) {
					checkState();
				}

				if(players.size() <= 1) {
					GameState.setState(GameState.WAITING);
					cdTimer = 10;
				}

				String msg = config.leaveMessage().replace("%player", p.getName()).replace("%status", players.size() + "");
				announce(msg);
				if(players.size() <= 0) {
					GameState.setState(GameState.HALT);
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Game is now halted");
				}
			}
		}

	}

	
	
	// The methods that interact with the timers
	
	
	public void announce(String s) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s));
	}

	private Boolean playerCheck() {
		if(players.size() > 1) {
			return true; // IF ENOUGH PLAYERS ARE IN THE GAME
		}
		return false; // IF TOO LOW
	}

	public void score() {
		if(GameState.isState(GameState.COUNTING)) {
			for(UUID id : players) {
				Player p = Bukkit.getPlayer(id);
				bar.updateScore(p, cdTimer, " ", teampoints.get("slayers"), teampoints.get("dragons"));
			}
		} else {
			for(UUID id : players) {
				Player p = Bukkit.getPlayer(id);
				if(teams.get(id) == "slayers") {
					bar.updateScore(p, gameTimer, "&eSlayers", teampoints.get("slayers"), teampoints.get("dragons"));
				}
				if(teams.get(id) == "dragons") {
					bar.updateScore(p, gameTimer, "&5Dragons", teampoints.get("slayers"), teampoints.get("dragons"));
				}
			}
		}
	}

	public void setScore() {
		for(UUID id : players) {
			Player p = Bukkit.getPlayer(id);
			if(teams.get(id) == "slayers") {
				bar.setScore(p, gameTimer, "&eSlayers");
			}
			if(teams.get(id) == "dragons") {
				bar.setScore(p, gameTimer, "&5Dragons");
			}
		}
	}

	public void startGame() {
		GameState.setState(GameState.GAME);
		announce(config.prefix() + config.starting());
		movePlayers();
		setScore();
		gameTimer();
	}

	public void checkState() {
		if(GameState.getState() == GameState.GAME) {
			if(teampoints.get("slayers") > teampoints.get("dragons")){
				GameState.setState(GameState.SLAYERSW);
			}
			if(teampoints.get("slayers") < teampoints.get("dragons")){
				GameState.setState(GameState.DRAGONW);
			}
			if(teampoints.get("slayers") == teampoints.get("dragons")){
				GameState.setState(GameState.DRAW);
			}
		}
	}

	public void endMethod() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setGameMode(GameMode.SURVIVAL);
			p.getActivePotionEffects().clear();
			p.getInventory().clear();
			p.setArrowsInBody(0);
			p.teleport(lm.getEnd());
			p.setHealth(20);
			p.setFoodLevel(20);
		}
		
	}

	public void kickAll() {
		
		cdTimer = 10;
		gameTimer = 300;
		endTimer = 10;
		players.clear();
		teams.clear();
		teampoints.clear();
		GameState.setState(GameState.HALT);
		
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.kickPlayer(null);
		}
		

		
		for(Entity e : lm.getLobby().getWorld().getEntities()) {
			e.remove();
		}
	}

	private void movePlayers() {
		if(GameState.getState() == GameState.GAME) {
			int team = 0;
			for (UUID id : players) {
				if(team == 0) {
					teams.put(id, "slayers");
					team++;
				} else {
					teams.put(id, "dragons");
					team--;
				}
			}
			for (UUID id : players) {
				if(teams.get(id) == "slayers") {
					Player p = Bukkit.getPlayer(id);
					km.kitSlayer(p);
					p.teleport(lm.getA());
					p.sendMessage(config.prefix() + config.slayerMsg());
				}
				if(teams.get(id) == "dragons") {
					Player p = Bukkit.getPlayer(id);
					km.kitDragon(p);
					p.teleport(lm.getB());
					p.sendMessage(config.dragonMsg());
				}
			}
		}
	}

	public void addPoint(String team) {
		int i = teampoints.get(team);
		teampoints.replace(team, i + 1);
	}

	private boolean stillInGame() {
		for(UUID id : players) {
			if(teams.get(id) == "slayers") {
				if(teams.get(id) != "dragons") {				
					return false;
				}
				if(GameState.getState() == GameState.DRAGONW) { // IF THE DRAGONS WIN
					return false;
				}
				if(GameState.getState() == GameState.DRAW) { // IF DRAW
					return false;
				}
			}
			if(teams.get(id) == "dragons") {
				if(teams.get(id) != "slayers") {	
					announce(config.slayerWinMsg());
					endTimer();
					return false;
				}
				if(GameState.getState() == GameState.SLAYERSW) { // IF THE DRAGONS WIN
					announce(config.dragonWinMsg());
					endTimer();
					return false;
				}

				if(GameState.getState() == GameState.DRAW) { // IF DRAW
					announce(config.gameDrawMsg());
					endTimer();
					return false;
				}
			}
		}
		return true;
	}

	
	
	
	// These are the timers used throughout the game


	private void countdown() {
		new BukkitRunnable() {
			@Override
			public void run() {
				score();
				if(GameState.getState() == GameState.COUNTING) {
					if(cdTimer > 0) {
						if(playerCheck() == true) {
							String msg = config.countdownMsg().replace("%seconds", cdTimer + "");
							cdTimer--;
							announce(msg);
							for (Player online : Bukkit.getOnlinePlayers()) {
								online.playSound(online.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 2, 2);
							}
						} else {
							announce(config.notEnough());
							this.cancel();
							GameState.setState(GameState.WAITING);
							cdTimer = 10;
						}
					} else {
						this.cancel();
						startGame();
						cdTimer = 10;
					}
				}else {
					this.cancel();
					announce(config.notEnough());
				}
			}

		}.runTaskTimer(JavaPlugin.getPlugin(Main.class), 0, 20);
	}

	private void gameTimer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(GameState.getState() == GameState.GAME) {
					if(gameTimer > 0) {
						gameTimer--;
						score();
					} else {
						checkState();
						if(GameState.getState() == GameState.SLAYERSW) { // IF THE SLAYERS WIN
							this.cancel();
							announce(config.slayerWinMsg());
							endTimer();
							endMethod();
						}
						if(GameState.getState() == GameState.DRAGONW) { // IF THE DRAGONS WIN
							this.cancel();
							announce(config.dragonWinMsg());
							endTimer();
							endMethod();
						}
						if(GameState.getState() == GameState.DRAW) { // IF DRAW
							this.cancel();
							announce(config.gameDrawMsg());
							endTimer();
							endMethod();
						}
					}
				} else {
					checkState();
					if(GameState.getState() == GameState.SLAYERSW) { // IF THE SLAYERS WIN
						this.cancel();
						announce(config.slayerWinMsg());
						endTimer();
						endMethod();
					}
					if(GameState.getState() == GameState.DRAGONW) { // IF THE DRAGONS WIN
						this.cancel();
						announce(config.dragonWinMsg());
						endTimer();
						endMethod();
					}
					if(GameState.getState() == GameState.DRAW) { // IF DRAW
						this.cancel();
						announce(config.gameDrawMsg());
						endTimer();
						endMethod();
					}
				}
			}
		}.runTaskTimer(JavaPlugin.getPlugin(Main.class), 0, 20);
	}

	private void endTimer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(endTimer > 0) {
					if(endTimer <= 5) {
						String msg = config.endTimer().replace("%seconds", endTimer + "");
						announce(msg);
						endTimer--;
					} else {
						endTimer--;
					}
				} else {
					this.cancel();
					GameState.setState(GameState.END);
					endTimer = 5;
					kickAll();
				}
			}

		}.runTaskTimer(JavaPlugin.getPlugin(Main.class), 0, 20);
	}

}

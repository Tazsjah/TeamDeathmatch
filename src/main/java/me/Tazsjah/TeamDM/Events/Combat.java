package me.Tazsjah.TeamDM.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tazsjah.TeamDM.Main;
import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Managers.KitManager;
import me.Tazsjah.TeamDM.Managers.LocationManager;
import net.md_5.bungee.api.ChatColor;

public class Combat implements Listener {

	// This class is used to disable
	// & enable combat. It is also used
	// to count kills & regenerate players

	General general;
	LocationManager lm;
	KitManager km;

	public Combat(General general, LocationManager lm, KitManager km) {
		this.general = general;
		this.lm = lm;
		this.km = km;
	}

	@EventHandler
	public void onCombat(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getDamager() instanceof Player) {
				Player victim = (Player) event.getEntity();
				Player attacker = (Player) event.getDamager();
				if(general.teams.get(victim.getUniqueId()) == general.teams.get(attacker.getUniqueId())){
					event.setCancelled(true);
					return;
				}
			}
		}
	}

	@EventHandler
	public void onCombat2(EntityDamageEvent event) {
		if(GameState.getState() != GameState.GAME) {
			event.setCancelled(true);
		}
	}
	
	public void announce(String s) {
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', s));
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.getEntity().getInventory().clear();
		if(event.getEntity() instanceof Player) {
			if(event.getEntity().getKiller() instanceof Player) {
				if(GameState.isState(GameState.GAME)) {
					Player victim = (Player) event.getEntity();
					Player attacker = (Player) event.getEntity().getKiller();
					String team = general.teams.get(attacker.getUniqueId());
					general.addPoint(team);
					ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
					attacker.getInventory().addItem(apple);
					ItemStack food = new ItemStack(Material.COOKED_BEEF);
					food.setAmount(2);
					attacker.getInventory().addItem(food);
					if(team == "slayers") {
						victim.teleport(lm.getA());
						event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&c" + victim.getName() + "&7 has been killed by" + "&c " + attacker.getName() + " &7(&d+1 &eSlayers&7)"));
					}
					if(team == "dragons") {
						victim.teleport(lm.getB());
						event.setDeathMessage(ChatColor.translateAlternateColorCodes('&', "&c" + victim.getName() + "&7 has been killed by" + "&c " + attacker.getName() + " &7(&d+1 &5Dragons&7)"));
					}
				}
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		Player p = event.getPlayer();
		String team = general.teams.get(p.getUniqueId());
		if(team == "slayers") {
			Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> p.teleport(lm.getA()), 5L);
			km.kitSlayer(p);
		}
		if(team == "dragons") {
			Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> p.teleport(lm.getB()), 5L);
			km.kitDragon(p);
		}

	}

}

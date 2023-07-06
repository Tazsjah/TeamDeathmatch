package me.Tazsjah.TeamDM.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Managers.LocationManager;
import net.md_5.bungee.api.ChatColor;

public class Map implements Listener {

	// Resets Players & Cancels Hunger

	General general;
	LocationManager lm;

	public Map(General general, LocationManager lm) {
		this.general = general;
		this.lm = lm;
	}

	HashMap<UUID, DamageCause> causes = new HashMap<UUID, DamageCause>();

	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		if(!GameState.isState(GameState.GAME)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onUse(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(!event.getPlayer().hasPermission("tdm.admin")) {
				event.setCancelled(true);
			}

		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setFormat(event.getPlayer().getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + event.getMessage());
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.getInventory().clear();
		player.setHealth(20);
		player.setFoodLevel(20);
		player.teleport(lm.getLobby());
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			causes.put(event.getEntity().getUniqueId(), event.getCause());
		}
	}

	@EventHandler
	public void onVoid(EntityDeathEvent event) { 
		if(event.getEntity() instanceof Player) {
			if(GameState.isState(GameState.GAME)) {
				if(!(event.getEntity().getKiller() instanceof Player)) {
					if(causes.get(event.getEntity().getUniqueId()) == DamageCause.VOID) {
						if(general.teams.get(event.getEntity().getUniqueId()) == "slayers") {
							general.announce(ChatColor.translateAlternateColorCodes('&', "&c" + event.getEntity().getName() + " &7died. &7(&d+1 &5Dragons&7)"));
							general.addPoint("dragons");
							causes.remove(event.getEntity().getUniqueId());
						}
						if(general.teams.get(event.getEntity().getUniqueId()) == "dragons") {
							general.announce(ChatColor.translateAlternateColorCodes('&', "&c" + event.getEntity().getName() + " &7died. &7(&d+1 &eSlayers&7)"));
							general.addPoint("slayers");
							causes.remove(event.getEntity().getUniqueId());
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(!event.getPlayer().hasPermission("tdm.admin")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if(!event.getPlayer().hasPermission("tdm.admin")) {
			event.setCancelled(true);
		}
	}

}

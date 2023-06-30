package me.Tazsjah.TeamDM.Events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		event.setDeathMessage(null);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player p = event.getPlayer();
		Location block = event.getPlayer().getLocation();
		block.setY(block.getY() -1);

		if(general.teams.get(p.getUniqueId()) == "slayers") {
			if(block.getBlock().getType() == Material.PURPLE_WOOL || block.getBlock().getType() == Material.OBSIDIAN) {
				PotionEffect poision = new PotionEffect(PotionEffectType.POISON, 100, 10);
				PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1);
				PotionEffect sick = new PotionEffect(PotionEffectType.CONFUSION, 100, 1);
				PotionEffect hunger = new PotionEffect(PotionEffectType.HUNGER, 100, 5);
				PotionEffect weak = new PotionEffect(PotionEffectType.WEAKNESS, 100, 2);
				p.addPotionEffect(poision);
				p.addPotionEffect(blind);
				p.addPotionEffect(sick);
				p.addPotionEffect(hunger);
				p.addPotionEffect(weak);
			}
		}
		if(general.teams.get(p.getUniqueId()) == "dragons") {
			if(block.getBlock().getType() == Material.GRAY_WOOL || block.getBlock().getType() == Material.LIGHT_GRAY_WOOL) {
				PotionEffect poision = new PotionEffect(PotionEffectType.POISON, 100, 10);
				PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1);
				PotionEffect sick = new PotionEffect(PotionEffectType.CONFUSION, 100, 1);
				PotionEffect hunger = new PotionEffect(PotionEffectType.HUNGER, 100, 5);
				PotionEffect weak = new PotionEffect(PotionEffectType.WEAKNESS, 100, 2);
				p.addPotionEffect(poision);
				p.addPotionEffect(blind);
				p.addPotionEffect(sick);
				p.addPotionEffect(hunger);
				p.addPotionEffect(weak);
			}
		}
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

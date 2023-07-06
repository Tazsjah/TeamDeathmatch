package me.Tazsjah.TeamDM.Managers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Tazsjah.TeamDM.Main;
import net.md_5.bungee.api.ChatColor;

public class ProtectionManager implements Listener {

	HashMap<UUID, Integer> pTime = new HashMap<UUID, Integer>();
	HashMap<UUID, Boolean> inProtect = new HashMap<UUID, Boolean>();

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		inProtect.put(event.getPlayer().getUniqueId(), false);
		pTime.put(event.getPlayer().getUniqueId(), 5);
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player p) {
			if(event.getDamager() instanceof Player k) {
				if(inProtect.get(p.getUniqueId()) == true) {
					event.setCancelled(true);
				}
				if(inProtect.get(event.getDamager().getUniqueId()) == true) {
					inProtect.replace(event.getDamager().getUniqueId(), false);
					event.getDamager().sendMessage(ChatColor.RED + "Spawn protection was taken away because you hit someone");
				}
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if(inProtect.get(event.getPlayer().getUniqueId()) == false) {
			spawnTime(event.getPlayer());
			inProtect.replace(event.getPlayer().getUniqueId(), true);
		}
	}



	private void spawnTime(Player p) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(pTime.get(p.getUniqueId()) > 0) {
					int i = pTime.get(p.getUniqueId());
					pTime.replace(p.getUniqueId(), i -1);
				} else {
					this.cancel();
					pTime.replace(p.getUniqueId(), 5);
					inProtect.replace(p.getUniqueId(), false);
				}
			}
		}.runTaskTimer(JavaPlugin.getPlugin(Main.class), 0, 20);

	}




}

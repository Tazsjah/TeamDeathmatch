package me.Tazsjah.TeamDM.Events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GoldenApples implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.GOLDEN_APPLE) {
				event.setCancelled(true);
				int amount = event.getPlayer().getInventory().getItemInMainHand().getAmount();
				Player p = event.getPlayer();
				if(amount > 1){
					event.getPlayer().getInventory().getItemInMainHand().setAmount(amount - 1);
					PotionEffect ab = new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0);
					PotionEffect rg = new PotionEffect(PotionEffectType.REGENERATION, 100, 2);
					p.addPotionEffect(ab);
					p.addPotionEffect(rg);
					p.playSound(p, Sound.ENTITY_PLAYER_BURP, 1, 1);
				} else {
					p.getInventory().getItemInMainHand().setAmount(0);
					PotionEffect ab = new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0);
					PotionEffect rg = new PotionEffect(PotionEffectType.REGENERATION, 100, 2);
					p.addPotionEffect(ab);
					p.addPotionEffect(rg);
					p.playSound(p, Sound.ENTITY_PLAYER_BURP, 1, 1);
				}
			}
		}
	}

}

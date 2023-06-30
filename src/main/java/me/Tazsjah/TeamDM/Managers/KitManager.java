package me.Tazsjah.TeamDM.Managers;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitManager {
	
	// This classes assigns kits
	// Will add configuration later on

	int arrowcount = 30;

	public void kitSlayer(Player p) {
		ItemStack helmet = new ItemStack(Material.YELLOW_WOOL);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.YELLOW + "Slayer Cap");
		helmet.setItemMeta(helmetMeta);
		p.getInventory().setHelmet(helmet);

		ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta chestMeta = chestplate.getItemMeta();
		chestMeta.setDisplayName(ChatColor.YELLOW + "Slayer Chestplate");	
		chestplate.setItemMeta(chestMeta);
		p.getInventory().setChestplate(chestplate);

		ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.YELLOW + "Slayer Pants");
		leggings.setItemMeta(leggingsMeta);	
		p.getInventory().setLeggings(leggings);

		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.YELLOW + "Slayer Boots");
		boots.setItemMeta(bootsMeta);
		p.getInventory().setBoots(boots);



		for(ItemStack item : p.getInventory().getArmorContents()) {
			ItemMeta meta = item.getItemMeta();
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
			item.setItemMeta(meta);
		}

		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.YELLOW + "Slaying Sword");
		sword.setItemMeta(swordMeta);

		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName(ChatColor.YELLOW + "Slayer Bow");
		bow.setItemMeta(bowMeta);

		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowMeta = arrow.getItemMeta();
		arrow.setAmount(arrowcount);
		arrow.setItemMeta(arrowMeta);
		
		ItemStack food = new ItemStack(Material.COOKED_BEEF);
		food.setAmount(10);


		p.getInventory().addItem(sword);
		p.getInventory().addItem(bow);
		p.getInventory().addItem(arrow);
		p.getInventory().addItem(food);

	}

	public void kitDragon(Player p) {
		ItemStack helmet = new ItemStack(Material.PURPLE_WOOL);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Head");
		helmet.setItemMeta(helmetMeta);
		p.getInventory().setHelmet(helmet);

		ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta chestMeta = chestplate.getItemMeta();
		chestMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Chestplate");
		chestplate.setItemMeta(chestMeta);
		p.getInventory().setChestplate(chestplate);

		ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Pants");
		leggings.setItemMeta(leggingsMeta);
		p.getInventory().setLeggings(leggings);

		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta bootsMeta = boots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Boots");
		boots.setItemMeta(bootsMeta);
		p.getInventory().setBoots(boots);

		for(ItemStack item : p.getInventory().getArmorContents()) {
			ItemMeta meta = item.getItemMeta();
			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
			item.setItemMeta(meta);
		}

		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Blade");
		sword.setItemMeta(swordMeta);

		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dragon Sword");
		bow.setItemMeta(bowMeta);

		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowMeta = arrow.getItemMeta();
		arrow.setItemMeta(arrowMeta);
		arrow.setAmount(arrowcount);
		
		ItemStack food = new ItemStack(Material.COOKED_BEEF);
		food.setAmount(10);

		
		p.getInventory().addItem(sword);
		p.getInventory().addItem(bow);
		p.getInventory().addItem(arrow);
		p.getInventory().addItem(food);
	}

}

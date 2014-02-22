package net.minekingdom.backstab;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BackStabListener implements Listener {
	
	public Set<Material> items;
	
	public BackStabListener(BackStab plugin) {
		this.items = new HashSet<Material>();
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("items");
		for (String key : section.getKeys(false)) {
			Material item = Material.matchMaterial(key);
			if (item == null) {
				
			}
			this.items.add(item);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event, EntityDamageEvent event2) {
		
		Entity damager = event.getDamager();
		Entity damaged = event.getEntity();
		
		if (!(damager instanceof Player)) {
			return;
		}
		
		if (!(damaged instanceof Player)) {
			return;
		}
		
		Vector damagerV = damager.getLocation().getDirection();
		Vector damagedV = damaged.getLocation().getDirection();
		
		if (damagedV.angle(damagerV) <= 225 && damagedV.angle(damagerV) >= 135) {
			
			Player damagedPlayer = ((Player) damaged);
			Player damagerPlayer = ((Player) damager);
			ItemStack item = damagerPlayer.getItemInHand();
			
			if ((damagedPlayer.hasPermission("minekingdom.backStabbed") || (damagerPlayer.hasPermission("minekingdom.backStabber")))) {
				
			}
			
			if (items.contains(item.getType())) {
				
				if (event2.getEntity().equals(damaged)) {
					
					Double damage = event2.getDamage();
					
					event2.setDamage(damage*2);
					damagerPlayer.sendMessage(ChatColor.GREEN + "Backstab succeded !");
					damagedPlayer.sendMessage(ChatColor.RED + "You've been Backstabbed !");
					
				}
			}	
		}
	}
}

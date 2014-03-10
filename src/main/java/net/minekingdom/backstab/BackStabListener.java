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
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BackStabListener implements Listener {
	
	public Set<Material> items;
	
	public BackStabListener(BackStab plugin) {
		this.items = new HashSet<Material>();
		Object section = plugin.getConfig().get("items");
		
		if (!(section instanceof ConfigurationSection)) {
			return;
		}
		
		ConfigurationSection config = (ConfigurationSection) section;
		
		for (String key : config.getKeys(false)) {
			try {
				Material item = Material.matchMaterial(key);
				if (item == null) {
					
				} else {
					this.items.add(item);
				}
			} catch (Exception e) {
				
			}

		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent damageEvent) {
		
		Entity damager = damageEvent.getDamager();
		Entity damaged = damageEvent.getEntity();
		
		if (!(damager instanceof Player)) {
			return;
		}
		
		if (!(damaged instanceof Player)) {
			return;
		}
		
		Vector damagerV = damager.getLocation().getDirection();
		Vector damagedV = damaged.getLocation().getDirection();
		
		if (Math.abs(damagerV.angle(damagedV)) <= Math.PI/4) {
			
			Player damagedPlayer = ((Player) damaged);
			Player damagerPlayer = ((Player) damager);
			ItemStack item = damagerPlayer.getItemInHand();
			
			if (!(damagedPlayer.hasPermission("minekingdom.backStabbed") || (damagerPlayer.hasPermission("minekingdom.backStabber")))) {
				return;
			}
			
			if (items.contains(item.getType())) {
				
				if (damageEvent.getEntity().equals(damaged)) {
					
					Double damage = damageEvent.getDamage();
					damageEvent.setDamage(damage*1.5);
					
					damagerPlayer.sendMessage(ChatColor.GREEN + "Backstab successful !");
					damagedPlayer.sendMessage(ChatColor.RED + "You've been backstabbed.");
					
				}
			}	
		}
	}
}

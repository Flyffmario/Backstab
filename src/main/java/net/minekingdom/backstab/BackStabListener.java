package net.minekingdom.backstab;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BackStabListener implements Listener {
	
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
			
			ItemStack item = ((Player) damager).getItemInHand();
			
			if (item.getType() == Material.DIAMOND_SWORD) {
				
				if (event2.getEntity().equals(damaged)) {
					
					Double damage = event2.getDamage();
					Player backstabber = ((Player) damager);
					
					event2.setDamage(damage*2);
					backstabber.sendMessage(ChatColor.GREEN + "BackStab done !");
					
				}
				

				

			}
			
		}
		
		
		
		
	}
}

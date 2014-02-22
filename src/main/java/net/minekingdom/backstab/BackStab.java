package net.minekingdom.backstab;

import org.bukkit.plugin.java.JavaPlugin;

public class BackStab extends JavaPlugin {
	
	private static BackStab instance;
	
	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		this.getConfig();
		this.getServer().getPluginManager().registerEvents(new BackStabListener(this), this);
	}
	
	public void onDisable() {
		
	}
	
	public static BackStab getInstance() {
		return instance;
	}
}

package de.tomjschwanke.unlimitedenchantment;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.Plugin;

public class AnvilEvents implements Listener {
    Plugin plugin;

    public AnvilEvents( UnlimitedEnchantment plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void anvilCost(PrepareAnvilEvent event) {
        AnvilInventory inv = event.getInventory();
        Bukkit.getScheduler().runTask(plugin, ()->inv.setMaximumRepairCost(999));
    }
}

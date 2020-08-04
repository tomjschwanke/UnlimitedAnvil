package de.tomjschwanke.unlimitedenchantment;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.Plugin;

public class AnvilEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void anvilCost(PrepareAnvilEvent event) {
        AnvilInventory inv = event.getInventory();
        inv.setMaximumRepairCost(Integer.MAX_VALUE);
    }
}

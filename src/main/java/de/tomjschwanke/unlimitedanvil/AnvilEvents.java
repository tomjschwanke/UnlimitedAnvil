package de.tomjschwanke.unlimitedanvil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

public class AnvilEvents implements Listener {
    long lastCalled = System.currentTimeMillis();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void anvilCost(PrepareAnvilEvent event) {
        Player p = (Player) event.getViewers().get(0);
        AnvilInventory inv = event.getInventory();
        inv.setMaximumRepairCost(Integer.MAX_VALUE);
        if(event.getResult() != null && System.currentTimeMillis() - lastCalled > 10 && inv.getRepairCost() >= 40) {
            lastCalled = System.currentTimeMillis();
            p.sendMessage("Cost: " + inv.getRepairCost());
        }
    }
}

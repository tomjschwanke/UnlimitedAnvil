package de.tomjschwanke.unlimitedanvil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;

public class AnvilEvents implements Listener {
    // Setup time variable, we'll need it later
    long lastCalled = System.currentTimeMillis();

    // Event handler gets called when something is put into the anvil
    @EventHandler(priority = EventPriority.HIGHEST)
    public void anvilCost(PrepareAnvilEvent event) {
        // Get anvil inventory
        AnvilInventory inv = event.getInventory();
        // Set max cost to max integer --> unlimited
        inv.setMaximumRepairCost(Integer.MAX_VALUE);
        /*
            Only send a message about cost if
            1. both slots are filled,
            2. no msg was sent in the last 10ms (this gets called 3 times so it would send 3 messages) and
            3. only if the cost is >= 40 since a cost higher than 40 will result in the anvil still showing "too expensive", regardless of set maximum repair cost
        */
        if(event.getResult() != null && System.currentTimeMillis() - lastCalled > 10 && inv.getRepairCost() >= 40) {
            // Set last called for 10ms cooldown (see above)
            lastCalled = System.currentTimeMillis();
            // Get player so we can send a message
            Player p = (Player) event.getViewers().get(0);
            // Send message about cost to player
            p.sendMessage("Cost: " + inv.getRepairCost() + " levels");
        }
    }
}

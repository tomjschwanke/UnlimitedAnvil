package de.tomjschwanke.unlimitedanvil;

import org.bukkit.GameMode;
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
        // Get anvil inventory reference
        AnvilInventory inv = event.getInventory();
        // Get player so we can send a message and get data
        Player p = (Player) event.getViewers().get(0);
        // Set max cost to max integer --> unlimited
        inv.setMaximumRepairCost(Integer.MAX_VALUE);
        /*
            Only send a message about cost if
            1. both slots are filled,
            2. no msg was sent in the last 10ms (this gets called 3 times so it would send 3 messages),
            3. only if the cost is >= 40 since a cost higher than 40 will result in the anvil still showing "too expensive", regardless of set maximum repair cost and
            4. player is not in creative gamemode
        */
        if(event.getResult() != null && System.currentTimeMillis() - lastCalled > 10 && inv.getRepairCost() >= 40 && p.getGameMode() != GameMode.CREATIVE) {
            // Set last called for 10ms cooldown (see above)
            lastCalled = System.currentTimeMillis();

            // Send message about cost to player
            p.sendMessage("Cost: " + inv.getRepairCost() + " levels");
        }
    }
}

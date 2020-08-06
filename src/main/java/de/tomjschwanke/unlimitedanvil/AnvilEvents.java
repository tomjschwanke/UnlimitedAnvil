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
    private int nr;

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
            sendLocalizedCost(p, inv.getRepairCost());
        }
    }

    private void sendLocalizedCost(Player p, int repairCost) {
        switch(p.getLocale()) {
            case "en_us":
            case "en_gb":
            case "en_ca":
            case "en_au":
            default:      p.sendMessage("Cost: " + repairCost + " levels"); break;
            case "en_ud": p.sendMessage("slǝʌǝ˥ " + upsideDownNumber(repairCost) + " :ʇsoƆ");
            case "de_at":
            case "de_de": p.sendMessage("Kosten: " + repairCost + " Level"); break;
            case "fr_fr":
            case "fr_ca": p.sendMessage("Frais: " + repairCost + " niveaux"); break;
            case "nl_nl": p.sendMessage("Kosten: " + repairCost + " niveaus"); break;
            case "es_es":
            case "es_uy":
            case "es_mx":
            case "es_ar":
            case "es_ve": p.sendMessage("Costo: " + repairCost + " niveles"); break;
            case "da_dk": p.sendMessage("Omkostninger: " + repairCost + " niveauer"); break;
            case "fi_fi": p.sendMessage("Kustannukset: " + repairCost + " tasoa"); break;
            case "sv_se": p.sendMessage("Kostar: " + repairCost + " nivåer"); break;
            case "it_it": p.sendMessage("Costi: " + repairCost + " livelli"); break;
            case "pl_pl": p.sendMessage("Koszty: " + repairCost + " poziomów"); break;
            case "tr_tr": p.sendMessage("Maliyetler: " + repairCost + " seviye"); break;
            case "ru_ru": p.sendMessage("Pасходы: " + repairCost + " получил"); break;
            case "ja_jp": p.sendMessage("費用: " + repairCost + " レベル"); break;
        }
    }

    String upsideDownNumber(int nr) {
        String number = Integer.toString(nr);
        StringBuilder numberUD = new StringBuilder();
        for(int i = number.length(); i > 0; i--) {
            switch (number.substring(i - 1, i)) {
                case "1": numberUD.append("Ɩ"); break;
                case "2": numberUD.append("ᄅ"); break;
                case "3": numberUD.append("Ɛ"); break;
                case "4": numberUD.append("ㄣ"); break;
                case "5": numberUD.append("ϛ"); break;
                case "6": numberUD.append("9"); break;
                case "7": numberUD.append("ㄥ"); break;
                case "8": numberUD.append("8"); break;
                case "9": numberUD.append("6"); break;
            }
        }
        return numberUD.toString();
    }
}

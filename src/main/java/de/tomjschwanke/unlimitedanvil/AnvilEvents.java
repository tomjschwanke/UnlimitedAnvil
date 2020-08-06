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
            String[] strings = localizedCost(p.getLocale());
            if(!strings[2].equals("inv")) {
                // Normal message
                p.sendMessage(strings[0] + inv.getRepairCost() + strings[1]);
            }else {
                // Using upside down text, turning number upside down
                p.sendMessage(strings[0] + upsideDownNumber(inv.getRepairCost()) + strings[1]);
            }
        }
    }

    String[] localizedCost(String locale) {
        String[] strings = new String[3];
        switch(locale) {
            case "en_us":
            case "en_gb":
            case "en_ca":
            case "en_au":
            default:      strings[0] = "Cost: "; strings[1] = " levels"; break;
            case "en_ud": strings[0] = "slǝʌǝ˥ "; strings[1] = " :ʇsoƆ"; strings[2] = "inv"; break;
            case "enws" : strings[0] = "Cost: "; strings[1] = " leveleth"; break;
            case "enp"  : strings[0] = "Outlay: "; strings[1] = " levels"; break;
            case "de_at":
            case "de_de": strings[0] = "Kosten: "; strings[1] = " Level"; break;
            case "fr_fr":
            case "fr_ca": strings[0] = "Frais: "; strings[1] = " niveaux"; break;
            case "nl_nl": strings[0] = "Kosten: "; strings[1] = " niveaus"; break;
            case "es_es":
            case "es_uy":
            case "es_mx":
            case "es_ar":
            case "es_ve": strings[0] = "Costo: "; strings[1] = " niveles"; break;
            case "da_dk": strings[0] = "Omkostninger: "; strings[1] = " niveauer"; break;
            case "fi_fi": strings[0] = "Kustannukset: "; strings[1] = " tasoa"; break;
            case "sv_se": strings[0] = "Kostar: "; strings[1] = " nivåer"; break;
            case "it_it": strings[0] = "Costi: "; strings[1] = " livelli"; break;
            case "pl_pl": strings[0] = "Koszty: "; strings[1] = " poziomów"; break;
            case "tr_tr": strings[0] = "Maliyetler: "; strings[1] = " seviye"; break;
            case "ru_ru": strings[0] = "Pасходы: "; strings[1] = " получил"; break;
            case "ja_jp": strings[0] = "費用: "; strings[1] = " レベル"; break;
        }
        return strings;
    }

    String upsideDownNumber(int nr) {
        String number = Integer.toString(nr);
        StringBuilder numberUD = new StringBuilder();
        for(int i = number.length(); i > 0; i--) {
            switch (number.substring(i - 1, i)) {
                // 068ㄥ9ϛᔭƐᄅƖ
                case "0": numberUD.append("0"); break;
                case "1": numberUD.append("Ɩ"); break;
                case "2": numberUD.append("ᄅ"); break;
                case "3": numberUD.append("Ɛ"); break;
                case "4": numberUD.append("ᔭ"); break;
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

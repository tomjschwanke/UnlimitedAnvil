package de.tomjschwanke.unlimitedenchantment;

import org.bukkit.plugin.java.JavaPlugin;

public final class UnlimitedEnchantment extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        printConsole("Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        printConsole("Disabled");
    }

    public void printConsole(String msg) {
        this.getServer().getConsoleSender().sendMessage("[UnlimitedEnchantment]" + msg);
    }
}

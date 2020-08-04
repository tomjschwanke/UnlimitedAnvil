package de.tomjschwanke.unlimitedanvil;

import org.bukkit.plugin.java.JavaPlugin;

public final class UnlimitedAnvil extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Register event handler
        getServer().getPluginManager().registerEvents(new AnvilEvents(), this);
        printConsole("Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        printConsole("Disabled");
    }
    // Prefix console messages
    public void printConsole(String msg) {
        this.getServer().getConsoleSender().sendMessage("[UnlimitedAnvil] " + msg);
    }
}

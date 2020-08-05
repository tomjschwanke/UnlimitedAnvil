package de.tomjschwanke.unlimitedanvil;

import org.bstats.bukkit.MetricsLite;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class UnlimitedAnvil extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // bStats metrics
        int pluginId = 8429;
        MetricsLite metrics = new MetricsLite(this, pluginId);
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

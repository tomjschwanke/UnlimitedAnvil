package de.tomjschwanke.unlimitedanvil;

import org.bstats.bukkit.MetricsLite;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@SuppressWarnings("unused")
public class UnlimitedAnvil extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // bStats metrics
        int pluginId = 8429;
        MetricsLite metrics = new MetricsLite(this, pluginId);
        // Init config
        initConfig();
        initCommands();
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
        getServer().getConsoleSender().sendMessage("[UnlimitedAnvil] " + msg);
    }

    // Init config and save to plugin folder if not present
    private void initConfig() {
        getConfig().addDefault("sound-enabled", true);
        saveDefaultConfig();
    }

    // Init command executor
    private void initCommands() {
        Objects.requireNonNull(getCommand("unlimitedanvil")).setExecutor(new AnvilCommands());
    }
}

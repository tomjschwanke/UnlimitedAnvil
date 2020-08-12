package de.tomjschwanke.unlimitedanvil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class AnvilCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("unlimitedanvil")) {
            // Correct command
            if(args[0].equals("config")) {
                // Change config path
                if(args[1].equals("sound-enabled")) {
                    // Sound config
                    if(args[2].equals("true") || args[2].equals("false")) {
                        // On or off config, change value
                        UnlimitedAnvil.getPlugin(UnlimitedAnvil.class).getConfig().set("sound-enabled", Boolean.parseBoolean(args[2]));
                        sender.sendMessage("UnlimitedAnvil: Config '" + args[1] + "' set to " + Objects.requireNonNull(UnlimitedAnvil.getPlugin(UnlimitedAnvil.class).getConfig().get("sound-enabled")).toString());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

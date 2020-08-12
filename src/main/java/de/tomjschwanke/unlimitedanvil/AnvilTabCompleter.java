package de.tomjschwanke.unlimitedanvil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AnvilTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if(command.getName().equals("unlimitedanvil")) {
            if(args[0].equals("config")) {
                if(UnlimitedAnvil.getPlugin(UnlimitedAnvil.class).getConfig().get(args[1]) != null && args[1] != null) {
                    // Config key exists, continue
                    if(args[1].equals("sound-enabled")) {
                        list.add("true");
                        list.add("false");
                        return list;
                    }
                }else {
                    list.addAll(UnlimitedAnvil.getPlugin(UnlimitedAnvil.class).getConfig().getKeys(false));
                    return list;
                }
            }else {
                list.add("config");
                return list;
            }
        }
        return list;
    }
}

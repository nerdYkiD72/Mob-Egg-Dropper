package me.thatcher.mobeggdropper.Commands.TabComplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class MEDTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>();
            subCommands.add("reload");
            subCommands.add("list");

            return subCommands;
        }
        if (args.length == 2) {
            List<String> subCommands = new ArrayList<>();
            subCommands.add("add");
            subCommands.add("remove");

            return subCommands;
        }
        if (args.length == 3) {
            List<String> subCommands = new ArrayList<>();
            for (EntityType type : EntityType.values()) {
                subCommands.add(type.toString());
            }

            return subCommands;
        }

        return null;
    }
}

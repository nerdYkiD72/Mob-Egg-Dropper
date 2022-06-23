package me.thatcher.mobeggdropper.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloWorld implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (((Player) sender).getPlayer());

            p.sendMessage("Hello World!");
        } else {
            sender.sendMessage("Hi there console.");
        }
        return true;
    }
}

package me.thatcher.mobeggdropper.Commands;

import me.thatcher.mobeggdropper.Files.PlayerDataFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EggToggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // get user data
        // flip the toggle
        // Save

        if (sender instanceof Player) {
            String path = sender.getName() + ".egg-toggled";
            if (args.length == 0) {
                boolean previousBool = PlayerDataFile.get().getBoolean(path);
                PlayerDataFile.get().set(path, !previousBool);

                if (previousBool) {
                    sender.sendMessage(ChatColor.GRAY + "Eggs will no longer be dropped.");
                } else {
                    sender.sendMessage(ChatColor.GREEN + "Eggs will now be dropped.");
                }
                PlayerDataFile.save();
            } else if (args[0].equals("check")) {
                sender.sendMessage("Egg drops are currently set to: " + ChatColor.GOLD + PlayerDataFile.get().getBoolean(path));
            } else {
                sender.sendMessage("To check status do '" + ChatColor.DARK_AQUA + "/egg-toggle check" +
                        ChatColor.WHITE + "', otherwise do just /egg-toggle");
            }
        }


        return false;
    }
}

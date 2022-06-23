package me.thatcher.mobeggdropper.Commands;

import me.thatcher.mobeggdropper.MobEggDropper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.List;

public class MED implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {
            if (sender.hasPermission("mobEggDropper.edit")) {
                String subCommand = args[0];
                if (subCommand.equals("reload")) {
                    MobEggDropper.getInstance().reloadConfig();
                    sender.sendMessage("Reloaded config");
                } else if (subCommand.equals("list")) {
                    if (args.length > 1) {
                        // TODO: Need to stop people from adding more than one reference to a certain type of mob
                        if (args[1].equals("add")) {
                            if (args.length > 2) {
                                // Add the animal to the list
                                sender.sendMessage("Adding animal to list...");
                                if (changeList(true, args[2])) {
                                    sender.sendMessage(ChatColor.GREEN + "Added Successfully");
                                }
                            } else {
                                // Error - Missing argument
                                sender.sendMessage("You must provide an animal to add to the list.");
                            }
                        } else if (args[1].equals("remove")) {
                            if (args.length > 2) {
                                // Remove the animal from the list
                                sender.sendMessage("Removing animal from list...");
                                if (changeList(false, args[2])) {
                                    sender.sendMessage(ChatColor.GREEN + "Removed Successfully");
                                }
                            } else {
                                // Error - Missing argument
                                sender.sendMessage("You must provide an animal to remove from the list.");
                            }
                        }
                    } else {
                        sender.sendMessage(listMobsFromConfig());
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permissions to use this command.");
            }

        } else {
            sender.sendMessage(ChatColor.GOLD + "~~~~" + ChatColor.AQUA + " Mob Egg Dropper " + ChatColor.GOLD + "~~~~");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.YELLOW + "Description: " + ChatColor.WHITE + ChatColor.ITALIC +
                    "A plugin to give players the spawn eggs of animals they kill.");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.YELLOW + "Commands:\n " +
                    ChatColor.WHITE + " - " + ChatColor.DARK_AQUA + "/med [list] [remove/add] " + ChatColor.WHITE + "..." + ChatColor.GRAY + ChatColor.ITALIC + " (Admin) Modify the config and show/change what mobs can drop eggs.\n" +
                    ChatColor.WHITE + "  - " + ChatColor.DARK_AQUA + "/med [reload] " + ChatColor.WHITE + ".............................." + ChatColor.GRAY + ChatColor.ITALIC + " (Admin) Reloads the plugin.\n" +
                    ChatColor.WHITE + "  - " + ChatColor.AQUA + "/egg-toggle [check] " + ChatColor.WHITE + ".............." + ChatColor.GRAY + ChatColor.ITALIC + " (All) Allows you to toggle weather or not you will get an egg after killing a mob. Adding 'check' to then end will show if egg drops are toggled.");
        }

        return false;
    }

    private String listMobsFromConfig() {
        StringBuilder output = new StringBuilder(ChatColor.GOLD + "Animals included" + ChatColor.WHITE + ":\n");

        for (String str: MobEggDropper.getInstance().getConfig().getStringList("mobs")) {
            output.append(ChatColor.WHITE + " - " + ChatColor.AQUA + str).append("\n");
        }
        return output.toString();
    }

    private boolean changeList(boolean addingToList, String animal) {
        List<String> mobsList = MobEggDropper.getInstance().getConfig().getStringList("mobs");
        EntityType newEntity = null;
        if (addingToList) {
            try{
                newEntity = EntityType.valueOf(animal);
                mobsList.add(newEntity.toString());
                MobEggDropper.getInstance().getConfig().set("mobs", mobsList);
                MobEggDropper.getInstance().getConfig().save(MobEggDropper.getInstance().getDataFolder() + "\\config.yml");
                return true;
            }catch(IllegalArgumentException | IOException exp){
                System.out.println(exp);
                return false;
            }
        } else {
            try{
                newEntity = EntityType.valueOf(animal);
                mobsList.remove(newEntity.toString());
                MobEggDropper.getInstance().getConfig().set("mobs", mobsList);
                MobEggDropper.getInstance().getConfig().save(MobEggDropper.getInstance().getDataFolder() + "\\config.yml");
                return true;
            }catch(IllegalArgumentException | IOException exp){
                System.out.println(exp);
                return false;
            }
        }
    }

}

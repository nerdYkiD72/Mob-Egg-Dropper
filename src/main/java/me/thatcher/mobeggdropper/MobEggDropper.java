package me.thatcher.mobeggdropper;

import me.thatcher.mobeggdropper.Commands.EggToggle;
import me.thatcher.mobeggdropper.Commands.HelloWorld;
import me.thatcher.mobeggdropper.Commands.MED;
import me.thatcher.mobeggdropper.Commands.TabComplete.EggToggleComplete;
import me.thatcher.mobeggdropper.Commands.TabComplete.MEDTabComplete;
import me.thatcher.mobeggdropper.Events.MobKillEvent;
import me.thatcher.mobeggdropper.Files.PlayerDataFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobEggDropper extends JavaPlugin {
    private static MobEggDropper instance;

    public static MobEggDropper getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        System.out.println(ChatColor.GOLD + "*******************************");
        System.out.println(ChatColor.GOLD + "** " +
                ChatColor.AQUA + "MobEggDropper Has Started" +
                ChatColor.GOLD + " **");
        System.out.println(ChatColor.GOLD + "*******************************");

        // Commands:
        getCommand("hello-world").setExecutor(new HelloWorld());
        getCommand("egg-toggle").setExecutor(new EggToggle());
        getCommand("egg-toggle").setTabCompleter(new EggToggleComplete());
        getCommand("med").setExecutor(new MED());
        getCommand("med").setTabCompleter(new MEDTabComplete());

        // Listeners:
        getServer().getPluginManager().registerEvents(new MobKillEvent(), this);

        System.out.println(getServer().getOnlinePlayers());


        // Config File Setup
        this.saveDefaultConfig();

        // Player Data File Creation and Setup
        PlayerDataFile.setup();
        PlayerDataFile.get().options().copyDefaults(true);
        PlayerDataFile.save();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

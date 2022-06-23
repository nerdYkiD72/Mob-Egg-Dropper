package me.thatcher.mobeggdropper.Files;

import me.thatcher.mobeggdropper.MobEggDropper;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PlayerDataFile {
    private static File file;
    private static FileConfiguration dataFile;

    // Finds or creates playerDataFile
    public static void setup() {
        System.out.println();
        file = new File(MobEggDropper.getInstance().getDataFolder(), "playerData.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        dataFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return dataFile;
    }

    public static void save() {
        try {
            dataFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't save player data file.");
        }
    }

    public static void reload() {
        dataFile = YamlConfiguration.loadConfiguration(file);
    }
}

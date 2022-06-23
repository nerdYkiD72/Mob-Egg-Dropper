package me.thatcher.mobeggdropper.Events;

import me.thatcher.mobeggdropper.Files.PlayerDataFile;
import me.thatcher.mobeggdropper.MobEggDropper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.security.PublicKey;
import java.util.List;
import java.util.Objects;

public class MobKillEvent implements Listener {
    @EventHandler
    public void onMobKill(EntityDeathEvent e){
        Player p = e.getEntity().getKiller();
        assert p != null;
        String entityType = e.getEntityType().toString();
        List<String> validMobs = MobEggDropper.getInstance().getConfig().getStringList("mobs");


        // TODO: Make this check for a permission that would be needed in order to get eggs no matter if it is toggled or not.

        // TODO: Also need to check to see if the player has enough room in their inventory.

        // TODO: Create permissions for different animals.

        for (String entity : validMobs) {
            if (entity.equals(entityType)) {
                String path = p.getName() + ".egg-toggled";
                boolean doDrops = PlayerDataFile.get().getBoolean(path);
                if (doDrops) {
                    ItemStack newEgg = new ItemStack(Material.matchMaterial(entityType + "_SPAWN_EGG"));
                    p.getInventory().addItem(newEgg);
                    p.sendMessage(ChatColor.GREEN + "A " + ChatColor.BLUE + entityType.toString() + ChatColor.GREEN + " spawn egg has been added to your inventory.");
                }

                break;
            }
        }

    }
}

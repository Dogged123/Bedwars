package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.queue;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class generatorSplitting implements Listener {

    private final GUIs plugin;
    public generatorSplitting(GUIs p) {
        plugin = p;
    }
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Location loc = p.getLocation();
            PersistentDataContainer container = p.getPersistentDataContainer();
            NamespacedKey teamName = new NamespacedKey(plugin, "Team");

            ArrayList<Material> splittableItems = new ArrayList<>();
            splittableItems.add(Material.IRON_INGOT);
            splittableItems.add(Material.GOLD_INGOT);

            switch (container.get(teamName, PersistentDataType.STRING).toLowerCase()) {
                case "red":
                    if (loc.distance(plugin.getConfig().getLocation("Red")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : queue.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            PersistentDataContainer container2 = player.getPersistentDataContainer();
                            if (player.getLocation().distance(loc) <= 2 &&
                                    container2.get(teamName, PersistentDataType.STRING).toLowerCase().equals("red")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "blue":
                    if (loc.distance(plugin.getConfig().getLocation("Blue")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : queue.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            PersistentDataContainer container2 = player.getPersistentDataContainer();
                            if (player.getLocation().distance(loc) <= 2 &&
                                    container2.get(teamName, PersistentDataType.STRING).toLowerCase().equals("blue")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "yellow":
                    if (loc.distance(plugin.getConfig().getLocation("Yellow")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : queue.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            PersistentDataContainer container2 = player.getPersistentDataContainer();
                            if (player.getLocation().distance(loc) <= 2 &&
                                    container2.get(teamName, PersistentDataType.STRING).toLowerCase().equals("yelllow")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "black":
                    if (loc.distance(plugin.getConfig().getLocation("Black")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : queue.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            PersistentDataContainer container2 = player.getPersistentDataContainer();
                            if (player.getLocation().distance(loc) <= 2 &&
                                    container2.get(teamName, PersistentDataType.STRING).toLowerCase().equals("black")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
            }
        }
    }
}

package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.QueueCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

import static me.isaacfediw.guis.commands.QueueCommand.team;

public class SplitGens implements Listener {

    private final GUIs plugin;
    public SplitGens(GUIs p) {
        plugin = p;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Location loc = p.getLocation();

            ArrayList<Material> splittableItems = new ArrayList<>();
            splittableItems.add(Material.IRON_INGOT);
            splittableItems.add(Material.GOLD_INGOT);

            if (team.get(p) == null) return;

            switch (team.get(p).toLowerCase()) {
                case "red":
                    if (loc.distance(plugin.getConfig().getLocation("Red")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : QueueCommand.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            if (player.getLocation().distance(loc) <= 2 &&
                                    team.get(player).equalsIgnoreCase("red")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "blue":
                    if (loc.distance(plugin.getConfig().getLocation("Blue")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : QueueCommand.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            if (player.getLocation().distance(loc) <= 2 &&
                                    team.get(player).equalsIgnoreCase("blue")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "yellow":
                    if (loc.distance(plugin.getConfig().getLocation("Yellow")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : QueueCommand.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            if (player.getLocation().distance(loc) <= 2 &&
                                    team.get(player).equalsIgnoreCase("yelllow")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
                case "black":
                    if (loc.distance(plugin.getConfig().getLocation("Black")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                        for (Player player : QueueCommand.queuedPlayers) {
                            if (player == p) {
                                continue;
                            }
                            if (player.getLocation().distance(loc) <= 2 &&
                                    team.get(player).equalsIgnoreCase("black")) {
                                player.getInventory().addItem(e.getItem().getItemStack());
                            }
                        }
                    }
                    break;
            }
        }
    }
}

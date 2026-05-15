package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.QueueCommand;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.ArrayList;

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

            PlayerData pData;

            if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
            else pData = new PlayerData(p);

            if (pData.getPlayerTeam().equals("N/A")) return;

            switch (pData.getPlayerTeam().toLowerCase()) {
                case "red":
                    if (loc.distance(plugin.getConfig().getLocation("Red")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                    }

                    break;
                case "blue":
                    if (loc.distance(plugin.getConfig().getLocation("Blue")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                    }

                    break;
                case "yellow":
                    if (loc.distance(plugin.getConfig().getLocation("Yellow")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;

                    }

                    break;
                case "black":
                    if (loc.distance(plugin.getConfig().getLocation("Black")) <= 3) {
                        if (!splittableItems.contains(e.getItem().getItemStack().getType())) return;
                        if (e.getItem().getOwner() != null) return;
                    }

                    break;
            }


            PlayerData playerData;
            for (Player player : QueueCommand.queuedPlayers) {
                if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                else playerData = new PlayerData(player);

                if (player == p) continue;

                if (player.getLocation().distance(loc) <= 2 && playerData.getPlayerTeam().equals(pData.getPlayerTeam())) {
                    player.getInventory().addItem(e.getItem().getItemStack());
                }
            }
        }
    }
}

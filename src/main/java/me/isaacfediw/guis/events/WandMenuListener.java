package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WandMenuListener implements Listener {
    GUIs plugin;
    public WandMenuListener(GUIs p){
        plugin = p;
    }

    @EventHandler
    public void onQueOrArenaClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("AddBase")){
            switch (e.getSlot()){
                case 0:
                    p.closeInventory();
                    setArena("Red", p);
                    p.sendMessage();
                    break;
                case 1:
                    p.closeInventory();
                    setArena("Blue", p);
                    break;
                case 2:
                    p.closeInventory();
                    setArena("Yellow", p);
                    break;
                case 3:
                    p.closeInventory();
                    setArena("Black", p);
                    break;
                case 5:
                    p.closeInventory();
                    setArena("Diamond1", p);
                    break;
                case 6:
                    p.closeInventory();
                    setArena("Diamond2", p);
                    break;
                case 7:
                    p.closeInventory();
                    setArena("Diamond3", p);
                    break;
                case 8:
                    p.closeInventory();
                    setArena("Diamond4", p);
                    break;
                case 9:
                    p.closeInventory();
                    setArena("Emerald1", p);
                    break;
                case 10:
                    p.closeInventory();
                    setArena("Emerald2", p);
                    break;
                case 11:
                    p.closeInventory();
                    setArena("Emerald3", p);
                    break;
                case 12:
                    p.closeInventory();
                    setArena("Emerald4", p);
                    break;
                case 17:
                    p.closeInventory();
                    setArena("Que", p);
                    break;
            }
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("DeleteBase")){
            switch (e.getSlot()){
                case 0:
                    p.closeInventory();
                    removeArena("Red", p);
                    break;
                case 1:
                    p.closeInventory();
                    removeArena("Blue", p);
                    break;
                case 2:
                    p.closeInventory();
                    removeArena("Yellow", p);
                    break;
                case 3:
                    p.closeInventory();
                    removeArena("Black", p);
                    break;
                case 5:
                    p.closeInventory();
                    removeArena("Diamond1", p);
                    break;
                case 6:
                    p.closeInventory();
                    removeArena("Diamond2", p);
                    break;
                case 7:
                    p.closeInventory();
                    removeArena("Diamond3", p);
                    break;
                case 8:
                    p.closeInventory();
                    removeArena("Diamond4", p);
                    break;
                case 9:
                    p.closeInventory();
                    removeArena("Emerald1", p);
                    break;
                case 10:
                    p.closeInventory();
                    removeArena("Emerald2", p);
                    break;
                case 11:
                    p.closeInventory();
                    removeArena("Emerald3", p);
                    break;
                case 12:
                    p.closeInventory();
                    removeArena("Emerald4", p);
                    break;
                case 17:
                    p.closeInventory();
                    removeArena("Que", p);
                    break;
            }
            e.setCancelled(true);
        }
    }

    public void setArena(String locationName, Player p){
        Location loc = p.getLocation();

        plugin.getConfig().set(locationName, loc);
        plugin.saveConfig();
        p.sendMessage("§d" + locationName + " area set to your location!");
    }

    public void removeArena(String locationName, Player p){
        if (plugin.getConfig().get(locationName) == null){
            p.sendMessage("§cThis area is not set up!");
            p.closeInventory();
            return;
        }
        plugin.getConfig().set(locationName, null);
        plugin.saveConfig();
        p.sendMessage("§d" + locationName + " area removed!");
    }
}

package me.isaacfediw.guis.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TNTListener implements Listener {

    @EventHandler
    public void onTNTPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.TNT)) {
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
       }
    }
}

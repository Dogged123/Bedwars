package me.isaacfediw.guis.events;

import me.isaacfediw.guis.commands.queue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoFoodDepletion implements Listener {

    @EventHandler
    public void onFoodDepletion(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (queue.queuedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }
}
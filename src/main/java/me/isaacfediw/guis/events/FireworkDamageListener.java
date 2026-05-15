package me.isaacfediw.guis.events;

import me.isaacfediw.guis.commands.QueueCommand;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class FireworkDamageListener implements Listener {

    @EventHandler
    public void onFireworkDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Firework)) return;

        Player p = (Player) event.getEntity();

        if (!QueueCommand.queuedPlayers.contains(p)) return;

        event.setCancelled(true);
    }
}
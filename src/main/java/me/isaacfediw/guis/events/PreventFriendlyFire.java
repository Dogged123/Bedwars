package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.openScoreboard;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.isaacfediw.guis.commands.queue.queuedPlayers;

public class PreventFriendlyFire implements Listener {

    GUIs plugin;

    public PreventFriendlyFire(GUIs p) {
        plugin = p;
    }

    @EventHandler
    public void onPlayerAttackPlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player player = (Player) e.getDamager();

            PersistentDataContainer container = p.getPersistentDataContainer();
            PersistentDataContainer container2 = player.getPersistentDataContainer();
            NamespacedKey team = new NamespacedKey(plugin, "Team");
            NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");

            if (container.get(lifeStatus, PersistentDataType.STRING).equals("N/A") && container2.get(lifeStatus, PersistentDataType.STRING).equals("N/A")){
                return;
            }
            if (container.get(team, PersistentDataType.STRING).equals(container2.get(team, PersistentDataType.STRING))) {
                e.setCancelled(true);
            }else if (container.get(lifeStatus, PersistentDataType.STRING).equals("In_Queue") || container2.get(lifeStatus, PersistentDataType.STRING).equals("In_Queue")){
                e.setCancelled(true);
            }
        }
    }
}

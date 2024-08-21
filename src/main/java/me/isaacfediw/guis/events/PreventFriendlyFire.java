package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import static me.isaacfediw.guis.commands.QueueCommand.lifeStatus;
import static me.isaacfediw.guis.commands.QueueCommand.team;

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

            if (!team.containsKey(p)) return;
            if (!lifeStatus.containsKey(p) || !lifeStatus.containsKey(player)) return;
            if (lifeStatus.get(p).equals("N/A") || lifeStatus.get(player).equals("N/A")) return;

            if (team.get(p).equals(team.get(p))) {
                e.setCancelled(true);
            } else if (lifeStatus.get(p).equals("In_Queue") || lifeStatus.get(player).equals("In_Queue")){
                e.setCancelled(true);
            }
        }
    }
}

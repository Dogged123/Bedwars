package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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

            PlayerData pData, playerData;

            if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
            else pData = new PlayerData(p);

            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
            else playerData = new PlayerData(player);

            if (pData.getPlayerTeam().equals("N/A")) return;
            if (pData.getLifeStatus().equals("N/A") || playerData.getLifeStatus().equals("N/A")) return;

            if (pData.getPlayerTeam().equals(playerData.getPlayerTeam())) {
                e.setCancelled(true);
            } else if (pData.getLifeStatus().equals("In_Queue") || playerData.getLifeStatus().equals("In_Queue")) {
                e.setCancelled(true);
            }
        }
    }
}

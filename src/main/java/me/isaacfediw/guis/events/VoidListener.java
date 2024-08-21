package me.isaacfediw.guis.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class VoidListener implements Listener {

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent e){
        if (queuedPlayers.contains(e.getPlayer())){
            if (e.getPlayer().getLocation().getY() <= 0){
                e.getPlayer().setHealth(0);
            }
        }
    }
}

package me.isaacfediw.guis.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Fireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class throwableFireballs implements Listener {

    @EventHandler
    public void onFireChargeRightClick(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Throwable Fireball")){
                Fireball fireball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Fireball.class);
                fireball.setVelocity(e.getPlayer().getLocation().getDirection());
                fireball.setYield(3);
                e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                e.setCancelled(true);
            }
        }
    }
}

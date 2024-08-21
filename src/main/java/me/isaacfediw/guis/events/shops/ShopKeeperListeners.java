package me.isaacfediw.guis.events.shops;

import me.isaacfediw.guis.commands.ItemShopCommand;
import me.isaacfediw.guis.commands.UpgradeShopCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class ShopKeeperListeners implements Listener {

    @EventHandler
    public void onVillagerClick(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if(!(entity instanceof NPC)){
            return;
        }

        ItemShopCommand shop = new ItemShopCommand();
        Player p = e.getPlayer();
        shop.openItemShop(p);
    }

    @EventHandler
    public void onVindicatorClick(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if (!(entity instanceof Vindicator)) {
            return;
        }

        UpgradeShopCommand shop = new UpgradeShopCommand();
        Player p = e.getPlayer();
        shop.openUpgradesShop(p);

    }
}


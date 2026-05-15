package me.isaacfediw.guis.events.shops;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.ItemShopCommand;
import me.isaacfediw.guis.commands.UpgradeShopCommand;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;


public class ShopKeeperListeners implements Listener {

    private final GUIs plugin;
    public ShopKeeperListeners(GUIs p) {plugin = p;}

    @EventHandler
    public void onItemShopNPCClick(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();

        List<String> shopkeepers = plugin.getConfig().getStringList("npc.entity.item_shop");

        if (!shopkeepers.isEmpty() && !shopkeepers.contains(entity.getType().toString())) return;
        if (shopkeepers.isEmpty() && entity.getType() != EntityType.VILLAGER) return;

        ItemShopCommand shop = new ItemShopCommand();
        Player p = e.getPlayer();
        shop.openItemShop(p);
    }

    @EventHandler
    public void onUpgradesShopNPCClick(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();

        List<String> shopkeepers = plugin.getConfig().getStringList("npc.entity.upgrades_shop");

        if (!shopkeepers.isEmpty() && !shopkeepers.contains(entity.getType().toString())) return;
        if (shopkeepers.isEmpty() && entity.getType() != EntityType.VINDICATOR) return;

        UpgradeShopCommand shop = new UpgradeShopCommand();
        Player p = e.getPlayer();
        shop.openUpgradesShop(p);
    }
}


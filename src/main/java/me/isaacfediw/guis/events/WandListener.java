package me.isaacfediw.guis.events;

import me.isaacfediw.guis.utils.ItemMaker;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WandListener implements Listener {
    @EventHandler
    public void wandClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!e.getAction().toString().contains("CLICK")) return;

        ItemStack base1 = ItemMaker.buildItem(Material.RED_WOOL, "§cRed");
        ItemStack base2 = ItemMaker.buildItem(Material.BLUE_WOOL, "§1Blue");
        ItemStack base3 = ItemMaker.buildItem(Material.YELLOW_WOOL, "§eYellow");
        ItemStack base4 = ItemMaker.buildItem(Material.BLACK_WOOL, "§0Black");

        ItemStack diamond1 = ItemMaker.buildItem(Material.DIAMOND_BLOCK, "§bDiamond 1");
        ItemStack diamond2 = ItemMaker.buildItem(Material.DIAMOND_BLOCK, "§bDiamond 2");
        ItemStack diamond3 = ItemMaker.buildItem(Material.DIAMOND_BLOCK, "§bDiamond 3");
        ItemStack diamond4 = ItemMaker.buildItem(Material.DIAMOND_BLOCK, "§bDiamond 4");

        ItemStack emerald1 = ItemMaker.buildItem(Material.EMERALD_BLOCK, "§2Emerald 1");
        ItemStack emerald2 = ItemMaker.buildItem(Material.EMERALD_BLOCK, "§2Emerald 2");
        ItemStack emerald3 = ItemMaker.buildItem(Material.EMERALD_BLOCK, "§2Emerald 3");
        ItemStack emerald4 = ItemMaker.buildItem(Material.EMERALD_BLOCK, "§2Emerald 4");

        ItemStack que = ItemMaker.buildItem(Material.GLASS, "§6Que");

        if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) return;
        if (p.getInventory().getItemInMainHand().getItemMeta() == null) return;

        Component displayNameComp = p.getInventory().getItemInMainHand().getItemMeta().displayName();
        if (displayNameComp == null) return;
        String displayName = LegacyComponentSerializer.legacySection().serialize(displayNameComp);

        if (e.getAction() == (Action.RIGHT_CLICK_BLOCK) || e.getAction() == (Action.RIGHT_CLICK_AIR)) {
            if (displayName.equals("§e§lBedwars Map Wand")) {
                Inventory addBase = Bukkit.createInventory(p, 18, Component.text("AddBase"));

                addBase.setItem(0, base1);
                addBase.setItem(1, base2);
                addBase.setItem(2, base3);
                addBase.setItem(3, base4);
                addBase.setItem(5, diamond1);
                addBase.setItem(6, diamond2);
                addBase.setItem(7, diamond3);
                addBase.setItem(8, diamond4);
                addBase.setItem(9, emerald1);
                addBase.setItem(10, emerald2);
                addBase.setItem(11, emerald3);
                addBase.setItem(12, emerald4);
                addBase.setItem(17, que);
                p.openInventory(addBase);
            }
        }

        if (e.getAction() == (Action.LEFT_CLICK_BLOCK) || e.getAction() == (Action.LEFT_CLICK_AIR)) {
            if (displayName.equals("§e§lBedwars Map Wand")) {
                Inventory delBase = Bukkit.createInventory(p, 18, Component.text("DeleteBase"));

                delBase.setItem(0, base1);
                delBase.setItem(1, base2);
                delBase.setItem(2, base3);
                delBase.setItem(3, base4);
                delBase.setItem(5, diamond1);
                delBase.setItem(6, diamond2);
                delBase.setItem(7, diamond3);
                delBase.setItem(8, diamond4);
                delBase.setItem(9, emerald1);
                delBase.setItem(10, emerald2);
                delBase.setItem(11, emerald3);
                delBase.setItem(12, emerald4);
                delBase.setItem(17, que);
                p.openInventory(delBase);
            }
        }
    }
}
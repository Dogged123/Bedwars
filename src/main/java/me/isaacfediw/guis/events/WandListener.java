package me.isaacfediw.guis.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WandListener implements Listener {
    @EventHandler
    public void wandClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == (Action.RIGHT_CLICK_BLOCK) || e.getAction() == (Action.RIGHT_CLICK_AIR)){
            if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§e§lBedwars Map Wand")){
                Inventory addBase = Bukkit.createInventory(p, 18, "AddBase");

                ItemStack base1 = new ItemStack(Material.RED_WOOL);
                ItemMeta base1Meta = base1.getItemMeta();
                base1Meta.setDisplayName("§cRed");
                base1.setItemMeta(base1Meta);

                ItemStack base2 = new ItemStack(Material.BLUE_WOOL);
                ItemMeta base2Meta = base2.getItemMeta();
                base2Meta.setDisplayName("§1Blue");
                base2.setItemMeta(base2Meta);

                ItemStack base3 = new ItemStack(Material.YELLOW_WOOL);
                ItemMeta base3Meta = base3.getItemMeta();
                base3Meta.setDisplayName("§eYellow");
                base3.setItemMeta(base3Meta);

                ItemStack base4 = new ItemStack(Material.BLACK_WOOL);
                ItemMeta base4Meta = base4.getItemMeta();
                base4Meta.setDisplayName("§0Black");
                base4.setItemMeta(base4Meta);

                ItemStack diamond1 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond1Meta = diamond1.getItemMeta();
                diamond1Meta.setDisplayName("§bDiamond 1");
                diamond1.setItemMeta(diamond1Meta);

                ItemStack diamond2 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond2Meta = diamond2.getItemMeta();
                diamond2Meta.setDisplayName("§bDiamond 2");
                diamond2.setItemMeta(diamond2Meta);

                ItemStack diamond3 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond3Meta = diamond3.getItemMeta();
                diamond3Meta.setDisplayName("§bDiamond 3");
                diamond3.setItemMeta(diamond3Meta);

                ItemStack diamond4 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond4Meta = diamond4.getItemMeta();
                diamond4Meta.setDisplayName("§bDiamond 4");
                diamond4.setItemMeta(diamond4Meta);

                ItemStack emerald1 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald1Meta = emerald1.getItemMeta();
                emerald1Meta.setDisplayName("§2Emerald 1");
                emerald1.setItemMeta(emerald1Meta);

                ItemStack emerald2 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald2Meta = emerald2.getItemMeta();
                emerald2Meta.setDisplayName("§2Emerald 2");
                emerald2.setItemMeta(emerald2Meta);

                ItemStack emerald3 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald3Meta = emerald3.getItemMeta();
                emerald3Meta.setDisplayName("§2Emerald 3");
                emerald3.setItemMeta(emerald3Meta);

                ItemStack emerald4 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald4Meta = emerald4.getItemMeta();
                emerald4Meta.setDisplayName("§2Emerald 4");
                emerald4.setItemMeta(emerald4Meta);

                ItemStack que = new ItemStack(Material.GLASS);
                ItemMeta queMeta = que.getItemMeta();
                queMeta.setDisplayName("§6Que");
                que.setItemMeta(queMeta);

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
        if (e.getAction() == (Action.LEFT_CLICK_BLOCK) || e.getAction() == (Action.LEFT_CLICK_AIR)){
            if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§e§lBedwars Map Wand")){
                Inventory delBase = Bukkit.createInventory(p, 18, "DeleteBase");

                ItemStack base1 = new ItemStack(Material.RED_WOOL);
                ItemMeta base1Meta = base1.getItemMeta();
                base1Meta.setDisplayName("§cRed");
                base1.setItemMeta(base1Meta);

                ItemStack base2 = new ItemStack(Material.BLUE_WOOL);
                ItemMeta base2Meta = base2.getItemMeta();
                base2Meta.setDisplayName("§1Blue");
                base2.setItemMeta(base2Meta);

                ItemStack base3 = new ItemStack(Material.YELLOW_WOOL);
                ItemMeta base3Meta = base3.getItemMeta();
                base3Meta.setDisplayName("§eYellow");
                base3.setItemMeta(base3Meta);

                ItemStack base4 = new ItemStack(Material.BLACK_WOOL);
                ItemMeta base4Meta = base4.getItemMeta();
                base4Meta.setDisplayName("§0Black");
                base4.setItemMeta(base4Meta);

                ItemStack diamond1 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond1Meta = diamond1.getItemMeta();
                diamond1Meta.setDisplayName("§bDiamond 1");
                diamond1.setItemMeta(diamond1Meta);

                ItemStack diamond2 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond2Meta = diamond2.getItemMeta();
                diamond2Meta.setDisplayName("§bDiamond 2");
                diamond2.setItemMeta(diamond2Meta);

                ItemStack diamond3 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond3Meta = diamond3.getItemMeta();
                diamond3Meta.setDisplayName("§bDiamond 3");
                diamond3.setItemMeta(diamond3Meta);

                ItemStack diamond4 = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta diamond4Meta = diamond4.getItemMeta();
                diamond4Meta.setDisplayName("§bDiamond 4");
                diamond4.setItemMeta(diamond4Meta);

                ItemStack emerald1 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald1Meta = emerald1.getItemMeta();
                emerald1Meta.setDisplayName("§2Emerald 1");
                emerald1.setItemMeta(emerald1Meta);

                ItemStack emerald2 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald2Meta = emerald2.getItemMeta();
                emerald2Meta.setDisplayName("§2Emerald 2");
                emerald2.setItemMeta(emerald2Meta);

                ItemStack emerald3 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald3Meta = emerald3.getItemMeta();
                emerald3Meta.setDisplayName("§2Emerald 3");
                emerald3.setItemMeta(emerald3Meta);

                ItemStack emerald4 = new ItemStack(Material.EMERALD_BLOCK);
                ItemMeta emerald4Meta = emerald4.getItemMeta();
                emerald4Meta.setDisplayName("§2Emerald 4");
                emerald4.setItemMeta(emerald4Meta);

                ItemStack que = new ItemStack(Material.GLASS);
                ItemMeta queMeta = que.getItemMeta();
                queMeta.setDisplayName("§6Que");
                que.setItemMeta(queMeta);

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

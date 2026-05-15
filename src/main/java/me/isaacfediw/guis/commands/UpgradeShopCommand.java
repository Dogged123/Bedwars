package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.utils.ItemMaker;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UpgradeShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.isOp()) {
                p.sendMessage("§cYou cannot use this command! Go to a vindicator to open the shop!");
                return true;
            }
            openUpgradesShop(p);
        } else {
            if (args.length == 0) {
                sender.sendMessage("Please specify a player to open the shop for!");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            openUpgradesShop(p);
        }
        return true;
    }

    public void openUpgradesShop(Player p) {
        Inventory upgrades_shop = Bukkit.createInventory(p, 27, Component.text("§6Upgrades Shop"));

        ItemStack sharp = ItemMaker.buildItem(Material.DIAMOND_SWORD,"§bSharpness 1", "§6Cost: 4 Diamonds");
        ItemStack prot  = ItemMaker.buildItem(Material.DIAMOND_CHESTPLATE, "§bProtection", "§6Cost: 2 Diamonds first time, 4 diamonds second time, 8 diamonds third time, 16 diamonds fourth time");
        ItemStack haste = ItemMaker.buildItem(Material.GOLDEN_PICKAXE, "§eHaste", "§6Cost: 2 Diamonds first time, 4 diamonds second time");

        upgrades_shop.setItem(11, sharp);
        upgrades_shop.setItem(13, prot);
        upgrades_shop.setItem(15, haste);

        p.openInventory(upgrades_shop);
    }
}

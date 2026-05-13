package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.utils.ItemMaker;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.GetWand")) {
                p.sendMessage("§cYou do not have permission to use this command!");
                return true;
            }

            ItemStack wand = ItemMaker.buildItem(Material.BLAZE_ROD, "§e§lBedwars Map Wand", "§6Right click to set up a base, Left click to remove a base");
            p.getInventory().addItem(wand);
        }
        return true;
    }
}

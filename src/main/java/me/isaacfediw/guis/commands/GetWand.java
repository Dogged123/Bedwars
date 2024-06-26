package me.isaacfediw.guis.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Collections;

public class GetWand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.GetWand")){
                p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }
            ItemStack wand = new ItemStack(Material.BLAZE_ROD);
            ItemMeta wandMeta = wand.getItemMeta();
            wandMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bedwars Map Wand");
            wandMeta.setLore(Collections.singletonList(ChatColor.GOLD + "Right click to set up a base, Left click to remove a base"));
            wand.setItemMeta(wandMeta);
            p.getInventory().addItem(wand);
        }
        return true;
    }
}

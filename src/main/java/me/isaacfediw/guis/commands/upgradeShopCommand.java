package me.isaacfediw.guis.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class upgradeShopCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (!p.isOp()){
                p.sendMessage(ChatColor.RED + "You cannot use this command! Go to a vindicator to open the shop!");
                return true;
            }
            openUpgradesShop(p);
        }else{
            if (args.length == 0){
                System.out.println("Please specify a player to open the shop for!");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            openUpgradesShop(p);
        }
        return true;
    }

    public void openUpgradesShop(Player p){
        Inventory upgrades_shop = Bukkit.createInventory(p, 27, ChatColor.GOLD + "Upgrades Shop");

        ItemStack sharp = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack prot = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack haste = new ItemStack(Material.GOLDEN_PICKAXE);

        ItemMeta sharp_meta = sharp.getItemMeta();
        sharp_meta.setDisplayName(ChatColor.AQUA + "Sharpness 1");
        ArrayList<String> sharp_lore = new ArrayList<>();
        sharp_lore.add(ChatColor.GOLD + "Cost: 4 Diamonds");
        sharp_meta.setLore(sharp_lore);
        sharp.setItemMeta(sharp_meta);

        ItemMeta prot_meta = prot.getItemMeta();
        prot_meta.setDisplayName(ChatColor.AQUA + "Protection");
        ArrayList<String> prot_lore = new ArrayList<>();
        prot_lore.add(ChatColor.GOLD + "Cost: 2 Diamonds first time, 4 diamonds second time, 8 diamonds third time, 16 diamonds fourth time");
        prot_meta.setLore(prot_lore);
        prot.setItemMeta(prot_meta);

        ItemMeta haste_meta = haste.getItemMeta();
        haste_meta.setDisplayName(ChatColor.YELLOW + "Haste");
        ArrayList<String> haste_lore = new ArrayList<>();
        haste_lore.add(ChatColor.GOLD + "Cost: 2 Diamonds first time, 4 diamonds second time");
        haste_meta.setLore(haste_lore);
        haste.setItemMeta(haste_meta);

        upgrades_shop.setItem(11, sharp);
        upgrades_shop.setItem(13, prot);
        upgrades_shop.setItem(15, haste);

        p.openInventory(upgrades_shop);
    }
}

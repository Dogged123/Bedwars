package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class ItemShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (!p.isOp()) {
                p.sendMessage("§cYou cannot use this command! Go to a villager to open the shop!");
                return true;
            }

            openItemShop(p);
        } else {
            if (args.length == 0) {
                sender.sendMessage("Please specify a player to open the shop for!");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            openItemShop(p);
        }
        return true;
    }

    public void openItemShop(Player p) {
        Inventory itemshop = Bukkit.createInventory(p, 9, Component.text("§6Item Shop"));

        ItemStack weapons = ItemMaker.buildItem(Material.GOLDEN_SWORD, "§6Combat");
        ItemStack tools   = ItemMaker.buildItem(Material.GOLDEN_PICKAXE, "§6Tools");
        ItemStack blocks  = ItemMaker.buildItem(Material.WHITE_WOOL, "§6Blocks");
        ItemStack pots    = ItemMaker.buildItem(Material.POTION, "§6Potions");
        ItemStack special = ItemMaker.buildItem(Material.ENDER_PEARL, "§6Special Items");

        itemshop.setItem(0, weapons);
        itemshop.setItem(2, tools);
        itemshop.setItem(4, blocks);
        itemshop.setItem(6, pots);
        itemshop.setItem(8, special);

        p.openInventory(itemshop);
    }

    public void openCombatShop(Player p) {
        Inventory combatShop = Bukkit.createInventory(p, 45, Component.text("§6Combat"));

        ItemStack back     = ItemMaker.buildItem(Material.SPECTRAL_ARROW, "§6Back");
        ItemStack ssword   = ItemMaker.buildItem(Material.STONE_SWORD, "§7Stone Sword", "§6Cost: 10 Iron");
        ItemStack isword   = ItemMaker.buildItem(Material.IRON_SWORD, "§fIron Sword", "§6Cost: 7 Gold");
        ItemStack dsword   = ItemMaker.buildItem(Material.DIAMOND_SWORD, "§bDiamond Sword", "§6Cost: 4 Emeralds");
        ItemStack kbStick  = ItemMaker.buildItem(Material.STICK, "§6KnockBack Stick", "§6Cost: 5 Gold");
        ItemStack iarmor   = ItemMaker.buildItem(Material.IRON_LEGGINGS, "§7Iron Armor", "§6Cost: 12 Gold");
        ItemStack darmor   = ItemMaker.buildItem(Material.DIAMOND_LEGGINGS, "§bDiamond Armor", "§6Cost: 6 Emeralds");

        ItemStack bow      = ItemMaker.buildItem(Material.BOW, "§6Bow", "§6Cost: 12 Gold");
        ItemStack powerBow = ItemMaker.buildItem(Material.BOW, "§6Power Bow", "§6Cost: 24 Gold");
        ItemStack punchBow = ItemMaker.buildItem(Material.BOW, "§6Punch Bow", "§6Cost: 6 Emeralds");
        ItemStack arrow    = ItemMaker.buildItem(Material.ARROW, "§6Arrows", "§6Cost: 2 Gold");

        combatShop.setItem(10, ssword);
        combatShop.setItem(12, isword);
        combatShop.setItem(14, dsword);
        combatShop.setItem(16, kbStick);
        combatShop.setItem(20, iarmor);
        combatShop.setItem(24, darmor);
        combatShop.setItem(28, bow);
        combatShop.setItem(30, powerBow);
        combatShop.setItem(32, punchBow);
        combatShop.setItem(34, arrow);
        combatShop.setItem(36, back);

        p.openInventory(combatShop);
    }

    public void openToolsShop(Player p) {
        Inventory toolsShop = Bukkit.createInventory(p, 36, Component.text("§6Tools"));

        ItemStack back     = ItemMaker.buildItem(Material.SPECTRAL_ARROW, "§6Back");
        ItemStack wpickaxe = ItemMaker.buildItem(Material.WOODEN_PICKAXE, "§6Wooden Pickaxe", "§6Cost: 10 Iron");
        ItemStack ipickaxe = ItemMaker.buildItem(Material.IRON_PICKAXE,"§fIron Pickaxe", "§6Cost: 10 Iron");
        ItemStack dpickaxe = ItemMaker.buildItem(Material.DIAMOND_PICKAXE,"§bDiamond Pickaxe", "§6Cost: 4 Gold");
        ItemStack shears   = ItemMaker.buildItem(Material.SHEARS, "§fShears", "§6Cost: 20 Iron");
        ItemStack wAxe     = ItemMaker.buildItem(Material.WOODEN_AXE, "§6Wooden Axe", "§6Cost: 10 Iron");
        ItemStack iAxe     = ItemMaker.buildItem(Material.IRON_AXE, "§fIron Axe", "§6Cost: 10 Iron");
        ItemStack dAxe     = ItemMaker.buildItem(Material.DIAMOND_AXE, "§bDiamond Axe", "§6Cost: 4 Gold");

        toolsShop.setItem(10, wpickaxe);
        toolsShop.setItem(12, ipickaxe);
        toolsShop.setItem(14, dpickaxe);
        toolsShop.setItem(16, shears);
        toolsShop.setItem(20, wAxe);
        toolsShop.setItem(22, iAxe);
        toolsShop.setItem(24, dAxe);
        toolsShop.setItem(27, back);

        p.openInventory(toolsShop);
    }

    public void openBlocksShop(Player p) {
        Inventory blocksShop = Bukkit.createInventory(p, 36, Component.text("§6Blocks"));

        PlayerData playerData;

        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        Material woolColour = !playerData.getPlayerTeam().equals("N/A") ? Material.getMaterial(playerData.getPlayerTeam().toUpperCase() + "_WOOL") : Material.WHITE_WOOL;
        Material clayColour = !playerData.getPlayerTeam().equals("N/A") ? Material.getMaterial(playerData.getPlayerTeam().toUpperCase() + "_TERRACOTTA") : Material.TERRACOTTA;

        ItemStack back     = ItemMaker.buildItem(Material.SPECTRAL_ARROW, "§6Back");
        ItemStack wool     = ItemMaker.buildItem(woolColour, "§fWool", "§6Cost: 4 Iron");
        ItemStack wood     = ItemMaker.buildItem(Material.OAK_PLANKS, "§eWood", "§6Cost: 4 Gold");
        ItemStack glass    = ItemMaker.buildItem(Material.TINTED_GLASS, "§8Blast Proof Glass", "§6Cost: 12 Iron");
        ItemStack clay     = ItemMaker.buildItem(clayColour, "§6Clay", "§6Cost: 8 Iron");
        ItemStack endstone = ItemMaker.buildItem(Material.END_STONE, "§fEndstone", "§6Cost: 24 Iron");
        ItemStack obsidian = ItemMaker.buildItem(Material.OBSIDIAN, "§5Obsidian", "§6Cost: 4 Emeralds");

        blocksShop.setItem(10, wool);
        blocksShop.setItem(12, wood);
        blocksShop.setItem(14, glass);
        blocksShop.setItem(16, clay);
        blocksShop.setItem(22, endstone);
        blocksShop.setItem(24, obsidian);
        blocksShop.setItem(27, back);

        p.openInventory(blocksShop);
    }

    public void openPotionsShop(Player p) {
        Inventory potionsShop = Bukkit.createInventory(p, 36, Component.text("§6Potions"));

        ItemStack back  = ItemMaker.buildItem(Material.SPECTRAL_ARROW, "§6Back");
        ItemStack speed = ItemMaker.buildItem(Material.POTION, "§bSpeed Potion", "§6Cost: 1 Emerald");
        ItemStack jump  = ItemMaker.buildItem(Material.POTION, "§aJump Boost Potion", "§6Cost: 1 Emerald");
        ItemStack invis = ItemMaker.buildItem(Material.POTION, "§dInvisibility Potion", "§6Cost: 2 Emerald");

        PotionMeta speed_meta = (PotionMeta) speed.getItemMeta();
        if (speed_meta != null) {
            speed_meta.setColor(Color.AQUA);
            speed_meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900, 4, false, false), true);
        }
        speed.setItemMeta(speed_meta);

        PotionMeta jump_meta = (PotionMeta) jump.getItemMeta();
        if (jump_meta != null) {
            jump_meta.setColor(Color.LIME);
            jump_meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900, 4, false, false), true);
        }
        jump.setItemMeta(jump_meta);

        PotionMeta invis_meta = (PotionMeta) invis.getItemMeta();
        if (invis_meta != null) {
            invis_meta.setColor(Color.PURPLE);
            invis_meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1, false, false), true);
        }
        invis.setItemMeta(invis_meta);

        potionsShop.setItem(9, speed);
        potionsShop.setItem(13, jump);
        potionsShop.setItem(17, invis);
        potionsShop.setItem(27, back);

        p.openInventory(potionsShop);
    }

    public void openSpecialShop(Player p) {
        Inventory specialShop = Bukkit.createInventory(p, 36, Component.text("§6Special Items"));

        ItemStack back      = ItemMaker.buildItem(Material.SPECTRAL_ARROW, "§6Back");
        ItemStack fireball  = ItemMaker.buildItem(Material.FIRE_CHARGE, "§eFireball", "§6Cost: 40 Iron");
        ItemStack ironGolem = ItemMaker.buildItem(Material.IRON_GOLEM_SPAWN_EGG, "§fIron Golem", "§6Cost: 120 Iron");
        ItemStack tnt       = ItemMaker.buildItem(Material.TNT, "§cTNT", "§6Cost: 4 Gold");
        ItemStack gapple    = ItemMaker.buildItem(Material.GOLDEN_APPLE, "§6Golden Apple", "§6Cost: 3 Gold");
        ItemStack water     = ItemMaker.buildItem(Material.WATER_BUCKET, "§1Water Bucket", "§6Cost: 6 Gold");
        ItemStack pearl     = ItemMaker.buildItem(Material.ENDER_PEARL,"§2Ender Pearl", "§6Cost: 4 Emeralds");

        specialShop.setItem(27, back);
        specialShop.setItem(10, fireball);
        specialShop.setItem(12, ironGolem);
        specialShop.setItem(14, tnt);
        specialShop.setItem(16, gapple);
        specialShop.setItem(20, water);
        specialShop.setItem(24, pearl);

        p.openInventory(specialShop);
    }
}
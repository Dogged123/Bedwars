package me.isaacfediw.guis.commands;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
import java.util.Collections;

import static me.isaacfediw.guis.commands.QueueCommand.team;

public class ItemShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.isOp()) {
                p.sendMessage("§cYou cannot use this command! Go to a villager to open the shop!");
                return true;
            }
            openItemShop(p);
        }else{
            if (args.length == 0){
                System.out.println("Please specify a player to open the shop for!");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            openItemShop(p);
        }
        return true;
    }

    public void openItemShop(Player p){
        Inventory itemshop = Bukkit.createInventory(p, 9, "§6Item Shop");

        ItemStack weapons = new ItemStack(Material.GOLDEN_SWORD);
        ItemStack tools = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemStack blocks = new ItemStack(Material.WHITE_WOOL);
        ItemStack pots = new ItemStack(Material.POTION);
        ItemStack special = new ItemStack(Material.ENDER_PEARL);

        ItemMeta weaponsMeta = weapons.getItemMeta();
        weaponsMeta.setDisplayName("§6Combat");
        weapons.setItemMeta(weaponsMeta);

        ItemMeta toolsMeta = tools.getItemMeta();
        toolsMeta.setDisplayName("§6Tools");
        tools.setItemMeta(toolsMeta);

        ItemMeta blocksMeta = blocks.getItemMeta();
        blocksMeta.setDisplayName("§6Blocks");
        blocks.setItemMeta(blocksMeta);

        ItemMeta potsMeta = pots.getItemMeta();
        potsMeta.setDisplayName("§6Potions");
        pots.setItemMeta(potsMeta);

        ItemMeta specialMeta = special.getItemMeta();
        specialMeta.setDisplayName("§6Special Items");
        special.setItemMeta(specialMeta);

        itemshop.setItem(0, weapons);
        itemshop.setItem(2, tools);
        itemshop.setItem(4, blocks);
        itemshop.setItem(6, pots);
        itemshop.setItem(8, special);

        p.openInventory(itemshop);
    }

    public void openCombatShop(Player p){
        Inventory combatShop = Bukkit.createInventory(p, 45, "§6Combat");

        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        ItemStack ssword = new ItemStack(Material.STONE_SWORD);
        ItemStack isword = new ItemStack(Material.IRON_SWORD);
        ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack kb_stick = new ItemStack(Material.STICK);
        ItemStack iarmor = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack darmor = new ItemStack(Material.DIAMOND_LEGGINGS);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack power_bow = new ItemStack(Material.BOW);
        ItemStack punch_bow = new ItemStack(Material.BOW);
        ItemStack arrow = new ItemStack(Material.ARROW);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName("§6Back");
        back.setItemMeta(back_meta);

        ItemMeta ssword_meta = ssword.getItemMeta();
        ssword_meta.setDisplayName("§7Stone Sword");
        ArrayList<String> ssword_lore = new ArrayList<>();
        ssword_lore.add("§6Cost: 10 Iron");
        ssword_meta.setLore(ssword_lore);
        ssword.setItemMeta(ssword_meta);

        ItemMeta isword_meta = isword.getItemMeta();
        isword_meta.setDisplayName("§fIron Sword");
        ArrayList<String> isword_lore = new ArrayList<>();
        isword_lore.add("§6Cost: 7 Gold");
        isword_meta.setLore(isword_lore);
        isword.setItemMeta(isword_meta);

        ItemMeta dsword_meta = dsword.getItemMeta();
        dsword_meta.setDisplayName("§bDiamond Sword");
        ArrayList<String> dsword_lore = new ArrayList<>();
        dsword_lore.add("§6Cost: 4 Emeralds");
        dsword_meta.setLore(dsword_lore);
        dsword.setItemMeta(dsword_meta);

        ItemMeta kb_stick_meta = kb_stick.getItemMeta();
        kb_stick_meta.setDisplayName("§6KnockBack Stick");
        ArrayList<String> kb_stick_lore = new ArrayList<>();
        kb_stick_lore.add("§6Cost: 5 Gold");
        kb_stick_meta.setLore(kb_stick_lore);
        kb_stick.setItemMeta(kb_stick_meta);

        ItemMeta iarmor_meta = iarmor.getItemMeta();
        iarmor_meta.setDisplayName("§7Iron Armor");
        ArrayList<String> iarmor_lore = new ArrayList<>();
        iarmor_lore.add("§6Cost: 12 Gold");
        iarmor_meta.setLore(iarmor_lore);
        iarmor.setItemMeta(iarmor_meta);

        ItemMeta darmor_meta = darmor.getItemMeta();
        darmor_meta.setDisplayName("§bDiamond Armor");
        ArrayList<String> darmor_lore = new ArrayList<>();
        darmor_lore.add("§6Cost: 6 Emeralds");
        darmor_meta.setLore(darmor_lore);
        darmor.setItemMeta(darmor_meta);

        ItemMeta bow_meta = bow.getItemMeta();
        bow_meta.setDisplayName("§6Bow");
        ArrayList<String> bow_lore = new ArrayList<>();
        bow_lore.add("§6Cost: 12 Gold");
        bow_meta.setLore(bow_lore);
        bow.setItemMeta(bow_meta);

        ItemMeta power_bow_meta = power_bow.getItemMeta();
        power_bow_meta.setDisplayName("§6Power Bow");
        ArrayList<String> power_bow_lore = new ArrayList<>();
        power_bow_lore.add("§6Cost: 24 Gold");
        power_bow_meta.setLore(power_bow_lore);
        power_bow.setItemMeta(power_bow_meta);

        ItemMeta punch_bow_meta = punch_bow.getItemMeta();
        punch_bow_meta.setDisplayName("§6Punch Bow");
        ArrayList<String> punch_bow_lore = new ArrayList<>();
        punch_bow_lore.add("§6Cost: 6 Emeralds");
        punch_bow_meta.setLore(punch_bow_lore);
        punch_bow.setItemMeta(punch_bow_meta);

        ItemMeta arrow_meta = arrow.getItemMeta();
        arrow_meta.setDisplayName("§6Arrows");
        ArrayList<String> arrow_lore = new ArrayList<>();
        arrow_lore.add("§6Cost: 2 Gold");
        arrow_meta.setLore(arrow_lore);
        arrow.setItemMeta(arrow_meta);

        combatShop.setItem(10, ssword);
        combatShop.setItem(12, isword);
        combatShop.setItem(14, dsword);
        combatShop.setItem(16, kb_stick);
        combatShop.setItem(20, iarmor);
        combatShop.setItem(24, darmor);
        combatShop.setItem(28, bow);
        combatShop.setItem(30, power_bow);
        combatShop.setItem(32, punch_bow);
        combatShop.setItem(34, arrow);
        combatShop.setItem(36, back);

        p.openInventory(combatShop);
    }

    public void openToolsShop(Player p){
        Inventory toolsShop = Bukkit.createInventory(p, 36, "§6Tools");

        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        ItemStack wpickaxe = new ItemStack(Material.WOODEN_PICKAXE);
        ItemStack ipickaxe = new ItemStack(Material.IRON_PICKAXE);
        ItemStack dpickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack shears = new ItemStack(Material.SHEARS);
        ItemStack wAxe = new ItemStack(Material.WOODEN_AXE);
        ItemStack iAxe = new ItemStack(Material.IRON_AXE);
        ItemStack dAxe = new ItemStack(Material.DIAMOND_AXE);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName("§6Back");
        back.setItemMeta(back_meta);

        ItemMeta wpickaxe_meta = wpickaxe.getItemMeta();
        wpickaxe_meta.setDisplayName("§6Wooden Pickaxe");
        ArrayList<String> wpickaxe_lore = new ArrayList<>();
        wpickaxe_lore.add("§6Cost: 10 Iron");
        wpickaxe_meta.setLore(wpickaxe_lore);
        wpickaxe.setItemMeta(wpickaxe_meta);

        ItemMeta ipickaxe_meta = ipickaxe.getItemMeta();
        ipickaxe_meta.setDisplayName("§fIron Pickaxe");
        ArrayList<String> ipickaxe_lore = new ArrayList<>();
        ipickaxe_lore.add("§6Cost: 10 Iron");
        ipickaxe_meta.setLore(ipickaxe_lore);
        ipickaxe.setItemMeta(ipickaxe_meta);

        ItemMeta dpickaxe_meta = dpickaxe.getItemMeta();
        dpickaxe_meta.setDisplayName("§bDiamond Pickaxe");
        ArrayList<String> dpickaxe_lore = new ArrayList<>();
        dpickaxe_lore.add("§6Cost: 4 Gold");
        dpickaxe_meta.setLore(dpickaxe_lore);
        dpickaxe.setItemMeta(dpickaxe_meta);


        ItemMeta shears_meta = shears.getItemMeta();
        shears_meta.setDisplayName("§fShears");
        ArrayList<String> shears_lore = new ArrayList<>();
        shears_lore.add("§6Cost: 20 Iron");
        shears_meta.setLore(shears_lore);
        shears.setItemMeta(shears_meta);

        ItemMeta wAxe_meta = wAxe.getItemMeta();
        wAxe_meta.setDisplayName("§6Wooden Axe");
        wAxe_meta.setLore(Collections.singletonList("§6Cost: 10 Iron"));
        wAxe.setItemMeta(wAxe_meta);

        ItemMeta iAxe_meta = iAxe.getItemMeta();
        iAxe_meta.setDisplayName("§fIron Axe");
        iAxe_meta.setLore(Collections.singletonList("§6Cost: 10 Iron"));
        iAxe.setItemMeta(iAxe_meta);

        ItemMeta dAxe_meta = dAxe.getItemMeta();
        dAxe_meta.setDisplayName("§bDiamond Axe");
        dAxe_meta.setLore(Collections.singletonList("§6Cost: 4 Gold"));
        dAxe.setItemMeta(dAxe_meta);

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
        Inventory blocksShop = Bukkit.createInventory(p, 36, "§6Blocks");

        Material woolColour = team.containsKey(p) ? Material.getMaterial(team.get(p).toUpperCase() + "_WOOL") : Material.WHITE_WOOL;

        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        ItemStack wool = new ItemStack(woolColour);
        ItemStack wood = new ItemStack(Material.OAK_PLANKS);
        ItemStack glass = new ItemStack(Material.TINTED_GLASS);
        ItemStack clay = new ItemStack(Material.TERRACOTTA);
        ItemStack endstone = new ItemStack(Material.END_STONE);
        ItemStack obsidian = new ItemStack(Material.OBSIDIAN);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName("§6Back");
        back.setItemMeta(back_meta);

        ItemMeta wool_meta = wool.getItemMeta();
        wool_meta.setDisplayName("§fWool");
        ArrayList<String> wool_lore = new ArrayList<>();
        wool_lore.add("§6Cost: 4 Iron");
        wool_meta.setLore(wool_lore);
        wool.setItemMeta(wool_meta);

        ItemMeta wood_meta = wood.getItemMeta();
        wood_meta.setDisplayName("§eWood");
        ArrayList<String> wood_lore = new ArrayList<>();
        wood_lore.add("§6Cost: 4 Gold");
        wood_meta.setLore(wood_lore);
        wood.setItemMeta(wood_meta);

        ItemMeta glass_meta = glass.getItemMeta();
        glass_meta.setDisplayName("§8Blast Proof Glass");
        ArrayList<String> glass_lore = new ArrayList<>();
        glass_lore.add("§6Cost: 12 Iron");
        glass_meta.setLore(glass_lore);
        glass.setItemMeta(glass_meta);

        ItemMeta clay_meta = clay.getItemMeta();
        clay_meta.setDisplayName("§6Clay");
        ArrayList<String> clay_lore = new ArrayList<>();
        clay_lore.add("§6Cost: 8 Iron");
        clay_meta.setLore(clay_lore);
        clay.setItemMeta(clay_meta);

        ItemMeta endstone_meta = endstone.getItemMeta();
        endstone_meta.setDisplayName("§fEndstone");
        ArrayList<String> endstone_lore = new ArrayList<>();
        endstone_lore.add("§6Cost: 24 Iron");
        endstone_meta.setLore(endstone_lore);
        endstone.setItemMeta(endstone_meta);

        ItemMeta obsidian_meta = obsidian.getItemMeta();
        obsidian_meta.setDisplayName("§5Obsidian");
        ArrayList<String> obsidian_lore = new ArrayList<>();
        obsidian_lore.add("§6Cost: 4 Emeralds");
        obsidian_meta.setLore(obsidian_lore);
        obsidian.setItemMeta(obsidian_meta);

        blocksShop.setItem(10, wool);
        blocksShop.setItem(12, wood);
        blocksShop.setItem(14, glass);
        blocksShop.setItem(16, clay);
        blocksShop.setItem(22, endstone);
        blocksShop.setItem(24, obsidian);
        blocksShop.setItem(27, back);

        p.openInventory(blocksShop);
    }

    public void openPotionsShop(Player p){

        Inventory potionsShop = Bukkit.createInventory(p, 36, "§6Potions");

        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        ItemStack speed = new ItemStack(Material.POTION);
        ItemStack jump = new ItemStack(Material.POTION);
        ItemStack invis = new ItemStack(Material.POTION);

        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName("§6Back");
        back.setItemMeta(back_meta);

        PotionMeta speed_meta = (PotionMeta) speed.getItemMeta();
        speed_meta.setDisplayName("§bSpeed Potion");
        speed_meta.setColor(Color.AQUA);
        speed_meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900,4, false, false), true);
        ArrayList<String> speed_lore = new ArrayList<>();
        speed_lore.add("§6Cost: 1 Emerald");
        speed_meta.setLore(speed_lore);
        speed.setItemMeta(speed_meta);

        PotionMeta jump_meta = (PotionMeta) jump.getItemMeta();
        jump_meta.setDisplayName("§aJump Boost Potion");
        jump_meta.setColor(Color.LIME);
        jump_meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900,4, false, false), true);
        ArrayList<String> jump_lore = new ArrayList<>();
        jump_lore.add("§6Cost: 1 Emerald");
        jump_meta.setLore(jump_lore);
        jump.setItemMeta(jump_meta);

        PotionMeta invis_meta = (PotionMeta) invis.getItemMeta();
        invis_meta.setDisplayName("§dInvisibility Potion");
        invis_meta.setColor(Color.PURPLE);
        invis_meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600,1, false, false), true);
        ArrayList<String> invis_lore = new ArrayList<>();
        invis_lore.add("§6Cost: 2 Emerald");
        invis_meta.setLore(invis_lore);
        invis.setItemMeta(invis_meta);

        potionsShop.setItem(9, speed);
        potionsShop.setItem(13, jump);
        potionsShop.setItem(17, invis);
        potionsShop.setItem(27, back);

        p.openInventory(potionsShop);
    }

    public void openSpecialShop(Player p) {
        Inventory specialShop = Bukkit.createInventory(p, 36, "§6Special Items");

        ItemStack back = new ItemStack(Material.SPECTRAL_ARROW);
        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE);
        ItemStack iron_golem = new ItemStack(Material.IRON_GOLEM_SPAWN_EGG);
        ItemStack tnt = new ItemStack(Material.TNT);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        ItemStack pearl = new ItemStack(Material.ENDER_PEARL);


        ItemMeta back_meta = back.getItemMeta();
        back_meta.setDisplayName("§6Back");
        back.setItemMeta(back_meta);

        ItemMeta fireball_meta = fireball.getItemMeta();
        fireball_meta.setDisplayName("§eFireball");
        ArrayList<String> fireball_lore = new ArrayList<>();
        fireball_lore.add("§6Cost: 40 Iron");
        fireball_meta.setLore(fireball_lore);
        fireball.setItemMeta(fireball_meta);

        ItemMeta iron_golem_meta = iron_golem.getItemMeta();
        iron_golem_meta.setDisplayName("§fIron Golem");
        ArrayList<String> iron_golem_lore = new ArrayList<>();
        iron_golem_lore.add("§6Cost: 120 Iron");
        iron_golem_meta.setLore(iron_golem_lore);
        iron_golem.setItemMeta(iron_golem_meta);

        ItemMeta tnt_meta = tnt.getItemMeta();
        tnt_meta.setDisplayName("§cTNT");
        ArrayList<String> tnt_lore = new ArrayList<>();
        tnt_lore.add("§6Cost: 4 Gold");
        tnt_meta.setLore(tnt_lore);
        tnt.setItemMeta(tnt_meta);

        ItemMeta gapple_meta = gapple.getItemMeta();
        gapple_meta.setDisplayName("§6Golden Apple");
        ArrayList<String> gapple_lore = new ArrayList<>();
        gapple_lore.add("§6Cost: 3 Gold");
        gapple_meta.setLore(gapple_lore);
        gapple.setItemMeta(gapple_meta);

        ItemMeta water_meta = water.getItemMeta();
        water_meta.setDisplayName("§1Water Bucket");
        ArrayList<String> water_lore = new ArrayList<>();
        water_lore.add("§6Cost: 6 Gold");
        water_meta.setLore(water_lore);
        water.setItemMeta(water_meta);

        ItemMeta pearl_meta = pearl.getItemMeta();
        pearl_meta.setDisplayName("§2Ender Pearl");
        ArrayList<String> pearl_lore = new ArrayList<>();
        pearl_lore.add("§6Cost: 4 Emeralds");
        pearl_meta.setLore(pearl_lore);
        pearl.setItemMeta(pearl_meta);

        specialShop.setItem(27, back);
        specialShop.setItem(10, fireball);
        specialShop.setItem(12, iron_golem);
        specialShop.setItem(14, tnt);
        specialShop.setItem(16, gapple);
        specialShop.setItem(20, water);
        specialShop.setItem(24, pearl);

        p.openInventory(specialShop);
    }
}

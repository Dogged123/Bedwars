package me.isaacfediw.guis.events.shops;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.shopCommand;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.ArrayList;
public class menuHandler implements Listener {

    GUIs plugin;

    shopCommand shop = new shopCommand();
    public menuHandler(GUIs p){
        plugin = p;
    }
    @EventHandler
    public void onWeaponClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
        NamespacedKey prot = new NamespacedKey(plugin, "prot");

        if (!container.has(sharp, PersistentDataType.STRING)){
            container.set(sharp, PersistentDataType.STRING, "None");
        }
        if (!container.has(prot, PersistentDataType.STRING)){
            container.set(prot, PersistentDataType.STRING, "None");
        }

        ItemStack ironLegs = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironLegsMeta = ironLegs.getItemMeta();
        ironLegsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        ironLegs.setItemMeta(ironLegsMeta);

        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironBootsMeta = ironBoots.getItemMeta();
        ironBootsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        ironBoots.setItemMeta(ironBootsMeta);

        ItemStack diamondLegs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta diamondLegsMeta = diamondLegs.getItemMeta();
        diamondLegsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        diamondLegs.setItemMeta(diamondLegsMeta);

        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();
        diamondBootsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        diamondBoots.setItemMeta(diamondBootsMeta);

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Combat")) {
            switch (e.getSlot()) {
                case 36:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case 10:
                    ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    if (container.get(sharp, PersistentDataType.STRING).equals("true")) {
                        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    }
                    sword.setItemMeta(swordMeta);
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(sword, 1)) {
                        p.getInventory().removeItem(sword);
                        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
                        ItemMeta stoneSwordMeta = stoneSword.getItemMeta();
                        stoneSwordMeta.setUnbreakable(true);
                        if (container.get(sharp, PersistentDataType.STRING).equals("true")){
                            stoneSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        }
                        stoneSword.setItemMeta(stoneSwordMeta);
                        p.getInventory().addItem(stoneSword);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Stone Sword");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase a stone sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 12:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 7)) {
                        if (p.getInventory().contains(Material.WOODEN_SWORD)){
                            p.getInventory().remove(Material.WOODEN_SWORD);
                        }
                        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
                        ItemMeta ironSwordMeta = ironSword.getItemMeta();
                        ironSwordMeta.setUnbreakable(true);
                        if (container.get(sharp, PersistentDataType.STRING).equals("true")){
                            ironSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        }
                        ironSword.setItemMeta(ironSwordMeta);
                        p.getInventory().addItem(ironSword);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 7));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Iron Sword");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase an iron sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 14:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)) {
                        if (p.getInventory().contains(Material.WOODEN_SWORD)){
                            p.getInventory().remove(Material.WOODEN_SWORD);
                        }
                        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
                        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
                        diamondSwordMeta.setUnbreakable(true);
                        if (container.get(sharp, PersistentDataType.STRING).equals("true")){
                            diamondSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        }
                        diamondSword.setItemMeta(diamondSwordMeta);
                        p.getInventory().addItem(diamondSword);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Diamond Sword");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase a diamond sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 16:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 5)) {
                        ItemStack kb_stick = new ItemStack(Material.STICK);
                        ItemMeta kb_stick_meta = kb_stick.getItemMeta();
                        kb_stick_meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
                        kb_stick.setItemMeta(kb_stick_meta);
                        p.getInventory().addItem(kb_stick);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 5));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased KnockBack Stick");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase a knockback stick!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 20:
                    if(p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 12)){
                        p.getInventory().setLeggings(ironLegs);
                        p.getInventory().setBoots(ironBoots);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Iron Armor");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase iron armor!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 24:
                    if(p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 6)){
                        p.getInventory().setLeggings(diamondLegs);
                        p.getInventory().setBoots(diamondBoots);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 6));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Diamond Armor");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase diamond armor!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 28:
                    if(p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 12)){
                        p.getInventory().addItem(new ItemStack(Material.BOW));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Bow");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 30:
                    if(p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 24)){
                        ItemStack power_bow = new ItemStack(Material.BOW);
                        ItemMeta power_bow_meta = power_bow.getItemMeta();
                        power_bow_meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
                        power_bow.setItemMeta(power_bow_meta);
                        p.getInventory().addItem(power_bow);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 24));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Power Bow");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase power bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 32:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 6)){
                        ItemStack punch_bow = new ItemStack(Material.BOW);
                        ItemMeta punch_bow_meta = punch_bow.getItemMeta();
                        punch_bow_meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
                        punch_bow_meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
                        punch_bow.setItemMeta(punch_bow_meta);
                        p.getInventory().addItem(punch_bow);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 6));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Punch Bow");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase punch bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 34:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2)){
                        p.getInventory().addItem(new ItemStack(Material.ARROW, 2));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 2));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Arrows");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase arrows!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onToolsClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Tools")) {
            if (e.getCurrentItem() == null){
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case SPECTRAL_ARROW:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case WOODEN_PICKAXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10)) {
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().addItem(new ItemStack(Material.WOODEN_PICKAXE));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Wooden Pickaxe");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase a wooden pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_PICKAXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(new ItemStack(Material.WOODEN_PICKAXE), 1)) {
                        ItemStack iPick = new ItemStack(Material.IRON_PICKAXE);
                        ItemMeta iPickMeta = iPick.getItemMeta();
                        iPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                        iPick.setItemMeta(iPickMeta);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().removeItem(new ItemStack(Material.WOODEN_PICKAXE, 1));
                        p.getInventory().addItem(iPick);
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Iron Pickaxe");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase an iron pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case DIAMOND_PICKAXE:
                    ItemStack iPick = new ItemStack(Material.IRON_PICKAXE);
                    ItemMeta iPickMeta = iPick.getItemMeta();
                    iPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    iPick.setItemMeta(iPickMeta);
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4) && p.getInventory().containsAtLeast(iPick, 1)) {
                        ItemStack dPick= new ItemStack(Material.DIAMOND_PICKAXE);
                        ItemMeta dPickMeta = dPick.getItemMeta();
                        dPickMeta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                        dPick.setItemMeta(dPickMeta);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.getInventory().removeItem(iPick);
                        p.getInventory().addItem(dPick);
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Diamond Pickaxe");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase a diamond pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case SHEARS:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 20)) {
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 20));
                        p.getInventory().addItem(new ItemStack(Material.SHEARS));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Shears");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase shears!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case WOODEN_AXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10)){
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Wooden Axe");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase a wooden axe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_AXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(new ItemStack(Material.WOODEN_AXE), 1)){
                        ItemStack iAxe = new ItemStack(Material.IRON_AXE);
                        ItemMeta iAxeMeta = iAxe.getItemMeta();
                        iAxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                        iAxe.setItemMeta(iAxeMeta);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().removeItem((new ItemStack(Material.WOODEN_AXE)));
                        p.getInventory().addItem(iAxe);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Iron Axe");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase an iron axe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case DIAMOND_AXE:
                    ItemStack iAxe = new ItemStack(Material.IRON_AXE);
                    ItemMeta iAxeMeta = iAxe.getItemMeta();
                    iAxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                    iAxe.setItemMeta(iAxeMeta);
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4) && p.getInventory().containsAtLeast(iAxe, 1)){
                        ItemStack dAxe = new ItemStack(Material.DIAMOND_AXE);
                        ItemMeta dAxeMeta = dAxe.getItemMeta();
                        dAxeMeta.addEnchant(Enchantment.DIG_SPEED, 2, true);
                        dAxe.setItemMeta(dAxeMeta);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.getInventory().removeItem(iAxe);
                        p.getInventory().addItem(dAxe);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Diamond Axe");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase a diamond axe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlocksClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Blocks")) {
            if (e.getCurrentItem() == null){
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case SPECTRAL_ARROW:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case WHITE_WOOL:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.WHITE_WOOL, 16));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Wool");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase wool!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case OAK_PLANKS:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 16));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Wood");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase wood!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case TINTED_GLASS:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 12)) {
                        p.getInventory().addItem(new ItemStack(Material.TINTED_GLASS, 4));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Blast Proof Glass");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase blast proof glass!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case TERRACOTTA:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 8)) {
                        p.getInventory().addItem(new ItemStack(Material.TERRACOTTA, 4));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 8));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Clay");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase clay!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case END_STONE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 24)) {
                        p.getInventory().addItem(new ItemStack(Material.END_STONE, 12));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 24));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Endstone");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase endstone!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case OBSIDIAN:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Obsidian");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase obsidian!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotionsClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Potions")) {
            switch (e.getSlot()) {
                case 27:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case 9:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 1)) {
                        ItemStack speed = new ItemStack(Material.POTION);
                        PotionMeta speed_meta = (PotionMeta) speed.getItemMeta();
                        speed_meta.setDisplayName(ChatColor.AQUA + "Speed Potion");
                        speed_meta.setColor(Color.AQUA);
                        speed_meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900, 4), true);
                        speed.setItemMeta(speed_meta);
                        p.getInventory().addItem(speed);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Speed Potion");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase speed potion!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 13:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 1)) {
                        ItemStack jump = new ItemStack(Material.POTION);
                        PotionMeta jump_meta = (PotionMeta) jump.getItemMeta();
                        jump_meta.setDisplayName(ChatColor.GREEN + "Jump Boost Potion");
                        jump_meta.setColor(Color.LIME);
                        jump_meta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900, 4), true);
                        jump.setItemMeta(jump_meta);
                        p.getInventory().addItem(jump);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Jump Boost Potion");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase jump boost potion!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 17:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 2)) {
                        ItemStack invis = new ItemStack(Material.POTION);
                        PotionMeta invis_meta = (PotionMeta) invis.getItemMeta();
                        invis_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Invisibility Potion");
                        invis_meta.setColor(Color.PURPLE);
                        invis_meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 4), true);
                        invis.setItemMeta(invis_meta);
                        p.getInventory().addItem(invis);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 2));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Invisibility Potion");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase invisibility potion!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSpecialClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Special Items")) {
            if (e.getCurrentItem() == null){
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case SPECTRAL_ARROW:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case FIRE_CHARGE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 40)) {
                        ItemStack fireball = new ItemStack(Material.FIRE_CHARGE);
                        ItemMeta fireball_meta = fireball.getItemMeta();
                        fireball_meta.setDisplayName(ChatColor.RED + "Fireball");
                        ArrayList<String> fireball_lore = new ArrayList<>();
                        fireball_lore.add(ChatColor.GOLD + "Throwable Fireball");
                        fireball_meta.setLore(fireball_lore);
                        fireball.setItemMeta(fireball_meta);
                        p.getInventory().addItem(fireball);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 40));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Fireball");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase fireball!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_GOLEM_SPAWN_EGG:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 120)) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_GOLEM_SPAWN_EGG));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 120));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Iron Golem");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough iron to purchase iron golem!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case TNT:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.TNT));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Tnt");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase tnt!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case GOLDEN_APPLE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3)) {
                        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased Golden Apple");
                    } else {
                        p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase golden apple!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case WATER_BUCKET:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 6)) {
                    p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                    p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 6));
                    p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                    p.sendMessage(ChatColor.GOLD + "Purchased Water Bucket");
                } else {
                    p.sendMessage(ChatColor.RED + "You do not have enough gold to purchase water bucket!");
                    p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                }
                break;
                case ENDER_PEARL:
                    if(p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)){
                        p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage(ChatColor.GOLD + "Purchased EnderPearl");
                    }else{
                        p.sendMessage(ChatColor.RED + "You do not have enough emeralds to purchase ender pearl!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }
}
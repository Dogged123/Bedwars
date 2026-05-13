package me.isaacfediw.guis.events.shops;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.ItemShopCommand;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
//import org.bukkit.persistence.PersistentDataContainer;
//import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class ItemShopListener implements Listener {

    GUIs plugin;

    ItemShopCommand shop = new ItemShopCommand();

    public ItemShopListener(GUIs p) {plugin = p;}

    @EventHandler
    public void onWeaponClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        PlayerData playerData;
        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        int sharpLevel = playerData.getEnchants().get("sharp");
        int protLevel = playerData.getEnchants().get("prot");

        if (e.getView().getTitle().equalsIgnoreCase("§6Combat")) {
            switch (e.getSlot()) {
                case 36:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case 10:
                    ItemStack sword = ItemMaker.buildItem(Material.WOODEN_SWORD, true);
                    ItemMeta swordMeta = sword.getItemMeta();

                    if (swordMeta != null && sharpLevel > 0)
                        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, sharpLevel, true);

                    sword.setItemMeta(swordMeta);
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(sword, 1)) {
                        p.getInventory().removeItem(sword);

                        ItemStack stoneSword = ItemMaker.buildItem(Material.STONE_SWORD, true);
                        ItemMeta stoneSwordMeta = stoneSword.getItemMeta();

                        if (stoneSwordMeta != null && sharpLevel > 0)
                            stoneSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, sharpLevel, true);

                        stoneSword.setItemMeta(stoneSwordMeta);
                        p.getInventory().addItem(stoneSword);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Stone Sword");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase a stone sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 12:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 7)) {
                        if (p.getInventory().contains(Material.WOODEN_SWORD)) {
                            p.getInventory().remove(Material.WOODEN_SWORD);
                        }

                        ItemStack ironSword = ItemMaker.buildItem(Material.IRON_SWORD, true);
                        ItemMeta ironSwordMeta = ironSword.getItemMeta();

                        if (ironSwordMeta != null && sharpLevel > 0)
                            ironSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, sharpLevel, true);

                        ironSword.setItemMeta(ironSwordMeta);
                        p.getInventory().addItem(ironSword);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 7));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Iron Sword");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase an iron sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 14:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)) {
                        if (p.getInventory().contains(Material.WOODEN_SWORD)) {
                            p.getInventory().remove(Material.WOODEN_SWORD);
                        }
                        ItemStack diamondSword = ItemMaker.buildItem(Material.DIAMOND_SWORD, true);
                        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();

                        if (diamondSwordMeta != null && sharpLevel > 0)
                            diamondSwordMeta.addEnchant(Enchantment.DAMAGE_ALL, sharpLevel, true);

                        diamondSword.setItemMeta(diamondSwordMeta);
                        p.getInventory().addItem(diamondSword);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Diamond Sword");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase a diamond sword!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 16:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 5)) {

                        ItemStack kbStick = ItemMaker.buildItem(Material.STICK, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.KNOCKBACK, 2);
                        }});

                        p.getInventory().addItem(kbStick);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 5));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased KnockBack Stick");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase a knockback stick!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 20:
                    ItemStack ironLegs = ItemMaker.buildItem(Material.IRON_LEGGINGS, true);
                    ItemMeta ironLegsMeta = ironLegs.getItemMeta();

                    if (protLevel > 0 && ironLegsMeta != null)
                        ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel, true);

                    ironLegs.setItemMeta(ironLegsMeta);

                    ItemStack ironBoots = ItemMaker.buildItem(Material.IRON_BOOTS, true);
                    ItemMeta ironBootsMeta = ironLegs.getItemMeta();

                    if (protLevel > 0 && ironLegsMeta != null)
                        ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel, true);

                    ironBoots.setItemMeta(ironBootsMeta);

                    for (ItemStack armourContent : p.getInventory().getArmorContents()) {
                        if (armourContent.getType().toString().contains("DIAMOND")) break;
                    }

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 12)) {
                        p.getInventory().setLeggings(ironLegs);
                        p.getInventory().setBoots(ironBoots);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Iron Armor");

                        playerData.setArmourItem(2, ironLegs);
                        playerData.setArmourItem(3, ironBoots);
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase iron armor!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 24:
                    ItemStack diamondLegs = ItemMaker.buildItem(Material.DIAMOND_LEGGINGS, true);
                    ItemMeta diamondLegsMeta = diamondLegs.getItemMeta();

                    if (protLevel > 0 && diamondLegsMeta != null)
                        diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel, true);

                    diamondLegs.setItemMeta(diamondLegsMeta);

                    ItemStack diamondBoots = ItemMaker.buildItem(Material.DIAMOND_BOOTS, true);
                    ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();

                    if (protLevel > 0 && diamondBootsMeta != null)
                        diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel, true);

                    diamondBoots.setItemMeta(diamondBootsMeta);

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 6)) {
                        p.getInventory().setLeggings(diamondLegs);
                        p.getInventory().setBoots(diamondBoots);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 6));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Diamond Armor");

                        playerData.setArmourItem(2, diamondLegs);
                        playerData.setArmourItem(3, diamondBoots);
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase diamond armor!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 28:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 12)) {
                        p.getInventory().addItem(new ItemStack(Material.BOW));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Bow");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 30:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 24)) {
                        ItemStack powerBow = ItemMaker.buildItem(Material.BOW, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.ARROW_DAMAGE, 2);
                        }});

                        p.getInventory().addItem(powerBow);
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 24));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Power Bow");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase power bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 32:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 6)) {
                        ItemStack punchBow = ItemMaker.buildItem(Material.BOW, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.ARROW_DAMAGE, 1);
                            put(Enchantment.ARROW_KNOCKBACK, 2);
                        }});

                        p.getInventory().addItem(punchBow);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 6));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Punch Bow");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase punch bow!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 34:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2)) {
                        p.getInventory().addItem(new ItemStack(Material.ARROW, 2));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 2));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Arrows");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase arrows!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onToolsClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        PlayerData pData;

        if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
        else pData = new PlayerData(p);

        Map<String, Boolean> permItems = pData.getPermItems();

        if (e.getView().getTitle().equalsIgnoreCase("§6Tools")) {
            if (e.getCurrentItem() == null) {
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
                        p.sendMessage("§6Purchased Wooden Pickaxe");

                        permItems.replace("pick", true);
                        pData.setPermItems(permItems);
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase a wooden pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_PICKAXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(new ItemStack(Material.WOODEN_PICKAXE), 1)) {
                        ItemStack iPick = ItemMaker.buildItem(Material.IRON_PICKAXE, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.DIG_SPEED, 1);
                        }});

                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().removeItem(new ItemStack(Material.WOODEN_PICKAXE, 1));
                        p.getInventory().addItem(iPick);
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Iron Pickaxe");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase an iron pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case DIAMOND_PICKAXE:
                    ItemStack iPick = ItemMaker.buildItem(Material.IRON_PICKAXE, true, new HashMap<Enchantment, Integer>(){{
                        put(Enchantment.DIG_SPEED, 1);
                    }});

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4) && p.getInventory().containsAtLeast(iPick, 1)) {
                        ItemStack dPick = ItemMaker.buildItem(Material.DIAMOND_PICKAXE, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.DIG_SPEED, 2);
                        }});

                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.getInventory().removeItem(iPick);
                        p.getInventory().addItem(dPick);
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Diamond Pickaxe");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase a diamond pickaxe!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case SHEARS:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 20)) {
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 20));
                        p.getInventory().addItem(new ItemStack(Material.SHEARS));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Shears");

                        permItems.replace("shears", true);
                        pData.setPermItems(permItems);
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase shears!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case WOODEN_AXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10)) {
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Wooden Axe");

                        permItems.replace("axe", true);
                        pData.setPermItems(permItems);
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase a wooden axe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_AXE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 10) && p.getInventory().containsAtLeast(new ItemStack(Material.WOODEN_AXE), 1)) {
                        ItemStack iAxe = ItemMaker.buildItem(Material.IRON_AXE, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.DIG_SPEED, 1);
                        }});

                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 10));
                        p.getInventory().removeItem((new ItemStack(Material.WOODEN_AXE)));
                        p.getInventory().addItem(iAxe);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Iron Axe");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase an iron axe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case DIAMOND_AXE:
                    ItemStack iAxe = ItemMaker.buildItem(Material.IRON_AXE, true, new HashMap<Enchantment, Integer>(){{
                        put(Enchantment.DIG_SPEED, 1);
                    }});

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4) && p.getInventory().containsAtLeast(iAxe, 1)) {
                        ItemStack dAxe = ItemMaker.buildItem(Material.DIAMOND_AXE, true, new HashMap<Enchantment, Integer>(){{
                            put(Enchantment.DIG_SPEED, 2);
                        }});

                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.getInventory().removeItem(iAxe);
                        p.getInventory().addItem(dAxe);
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Diamond Axe");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase a diamond axe!");
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

        PlayerData playerData;
        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        if (e.getView().getTitle().equalsIgnoreCase("§6Blocks")) {
            if (e.getCurrentItem() == null) {
                return;
            }
            switch (e.getSlot()) {
                case 27:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case 10:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 4)) {
                        Material woolColour = !playerData.getPlayerTeam().equals("N/A") ? Material.getMaterial(playerData.getPlayerTeam().toUpperCase() + "_WOOL") : Material.WHITE_WOOL;
                        if (woolColour == null) woolColour = Material.WHITE_WOOL;

                        p.getInventory().addItem(new ItemStack(woolColour, 16));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Wool");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase wool!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 12:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 16));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Wood");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase wood!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 14:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 12)) {
                        p.getInventory().addItem(new ItemStack(Material.TINTED_GLASS, 4));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 12));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Blast Proof Glass");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase blast proof glass!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 16:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 8)) {
                        Material clayColour = !playerData.getPlayerTeam().equals("N/A") ? Material.getMaterial(playerData.getPlayerTeam().toUpperCase() + "_TERRACOTTA") : Material.TERRACOTTA;
                        if (clayColour == null) clayColour = Material.TERRACOTTA;

                        p.getInventory().addItem(new ItemStack(clayColour, 4));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 8));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Clay");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase clay!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 22:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 24)) {
                        p.getInventory().addItem(new ItemStack(Material.END_STONE, 12));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 24));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Endstone");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase endstone!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 24:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Obsidian");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase obsidian!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotionsClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("§6Potions")) {
            switch (e.getSlot()) {
                case 27:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case 9:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 1)) {
                        ItemStack speed = ItemMaker.buildItem(Material.POTION, "§bSpeed Potion");

                        PotionMeta speedMeta = (PotionMeta) speed.getItemMeta();
                        if (speedMeta != null) {
                            speedMeta.setColor(Color.AQUA);
                            speedMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 900, 4, false, false), true);
                        }
                        speed.setItemMeta(speedMeta);

                        p.getInventory().addItem(speed);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Speed Potion");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase speed potion!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 13:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 1)) {
                        ItemStack jump = ItemMaker.buildItem(Material.POTION, "§aJump Boost Potion");

                        PotionMeta jumpMeta = (PotionMeta) jump.getItemMeta();
                        if (jumpMeta != null) {
                            jumpMeta.setColor(Color.LIME);
                            jumpMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 900, 4, false, false), true);
                        }
                        jump.setItemMeta(jumpMeta);

                        p.getInventory().addItem(jump);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 1));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Jump Boost Potion");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase jump boost potion!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 17:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 2)) {
                        ItemStack invis = ItemMaker.buildItem(Material.POTION, "§dInvisibility Potion");

                        PotionMeta invisMeta = (PotionMeta) invis.getItemMeta();
                        if (invisMeta != null) {
                            invisMeta.setColor(Color.PURPLE);
                            invisMeta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1, false, false), true);
                        }
                        invis.setItemMeta(invisMeta);

                        p.getInventory().addItem(invis);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 2));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Invisibility Potion");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase invisibility potion!");
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

        if (e.getView().getTitle().equalsIgnoreCase("§6Special Items")) {
            if (e.getCurrentItem() == null) {
                return;
            }
            switch (e.getCurrentItem().getType()) {
                case SPECTRAL_ARROW:
                    p.closeInventory();
                    shop.openItemShop(p);
                    break;
                case FIRE_CHARGE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 40)) {
                        ItemStack fireball  = ItemMaker.buildItem(Material.FIRE_CHARGE, "§eFireball", "§6Throwable Fireball");

                        p.getInventory().addItem(fireball);
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 40));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Fireball");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase fireball!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case IRON_GOLEM_SPAWN_EGG:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 120)) {
                        p.getInventory().addItem(new ItemStack(Material.IRON_GOLEM_SPAWN_EGG));
                        p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 120));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Iron Golem");
                    } else {
                        p.sendMessage("§cYou do not have enough iron to purchase iron golem!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case TNT:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.TNT));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Tnt");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase tnt!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case GOLDEN_APPLE:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3)) {
                        p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 3));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Golden Apple");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase golden apple!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case WATER_BUCKET:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 6)) {
                        p.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                        p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 6));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Water Bucket");
                    } else {
                        p.sendMessage("§cYou do not have enough gold to purchase water bucket!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case ENDER_PEARL:
                    if (p.getInventory().containsAtLeast(new ItemStack(Material.EMERALD), 4)) {
                        p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased EnderPearl");
                    } else {
                        p.sendMessage("§cYou do not have enough emeralds to purchase ender pearl!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }
}
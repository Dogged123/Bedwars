package me.isaacfediw.guis.events.shops;
import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.ItemShopCommand;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;
import static me.isaacfediw.guis.commands.QueueCommand.team;

public class ShopListeners implements Listener {

    GUIs plugin;

    public ShopListeners(GUIs p){
        plugin = p;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        ItemShopCommand shop = new ItemShopCommand();
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("§6Item Shop")){
            if (e.getCurrentItem() == null){
                return;
            }
            switch (e.getCurrentItem().getType()){
                case GOLDEN_SWORD:
                    p.closeInventory();
                    shop.openCombatShop(p);
                    break;
                case GOLDEN_PICKAXE:
                    p.closeInventory();
                    shop.openToolsShop(p);
                    break;
                case WHITE_WOOL:
                    p.closeInventory();
                    shop.openBlocksShop(p);
                    break;
                case POTION:
                    p.closeInventory();
                    shop.openPotionsShop(p);
                    break;
                case ENDER_PEARL:
                    p.closeInventory();
                    shop.openSpecialShop(p);
                    break;
            }
            e.setCancelled(true);

        }else if (e.getView().getTitle().equalsIgnoreCase("§6Upgrades Shop")) {
            PersistentDataContainer container = p.getPersistentDataContainer();
            NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
            NamespacedKey prot = new NamespacedKey(plugin, "prot");
            NamespacedKey haste = new NamespacedKey(plugin, "haste");

            if (!container.has(sharp, PersistentDataType.STRING)) {
                container.set(sharp, PersistentDataType.STRING, "None");
            }
            if (!container.has(prot, PersistentDataType.STRING)) {
                container.set(prot, PersistentDataType.STRING, "None");
            }if (!container.has(haste, PersistentDataType.STRING)) {
                container.set(haste, PersistentDataType.STRING, "None");
            }
            switch(e.getSlot()){
                case 11:
                    ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    sword.setItemMeta(swordMeta);
                    ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
                    ItemMeta stoneSwordMeta = stoneSword.getItemMeta();
                    stoneSwordMeta.setUnbreakable(true);
                    stoneSword.setItemMeta(stoneSwordMeta);
                    ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
                    ItemMeta ironSwordMeta = ironSword.getItemMeta();
                    ironSwordMeta.setUnbreakable(true);
                    ironSword.setItemMeta(ironSwordMeta);
                    ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
                    ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
                    diamondSwordMeta.setUnbreakable(true);
                    diamondSword.setItemMeta(diamondSwordMeta);

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4) && (p.getInventory().containsAtLeast(sword, 1) || p.getInventory().containsAtLeast(stoneSword, 1) || p.getInventory().containsAtLeast(ironSword, 1) || p.getInventory().containsAtLeast(diamondSword, 1))){
                        if (container.get(sharp, PersistentDataType.STRING).equals("true")){
                            p.sendMessage("§cYou already have sharpness!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                            return;
                        }
                        for (Player player : queuedPlayers){
                            PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                            if (team.get(player).equals(team.get(p))){
                                for (ItemStack i : player.getInventory().getContents()) {
                                    try {
                                        if (i.getType().equals(Material.WOODEN_SWORD) || i.getType().equals(Material.STONE_SWORD) || i.getType().equals(Material.IRON_SWORD) || i.getType().equals(Material.DIAMOND_SWORD)) {
                                            i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                                            playerContainer.set(sharp, PersistentDataType.STRING, "true");
                                            player.sendMessage("§6" + p.getName() + " purchased sharpness");
                                        }
                                    } catch (NullPointerException n) {
                                        System.out.print(" ");
                                    }
                                }
                            }
                        }
                        for(ItemStack i : p.getInventory().getContents()) {
                            try {
                                if (i.getType().equals(Material.WOODEN_SWORD) || i.getType().equals(Material.STONE_SWORD) || i.getType().equals(Material.IRON_SWORD) || i.getType().equals(Material.DIAMOND_SWORD)) {
                                    i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                                    container.set(sharp, PersistentDataType.STRING, "true");
                                }
                            } catch (NullPointerException n) {
                                System.out.print(" ");
                            }
                        }
                        p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Sharpness");
                    }else {
                        p.sendMessage("§cYou do not have enough diamonds to purchase sharpness!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 13:
                    ItemStack[] checkArmor = p.getInventory().getArmorContents();
                    if (p.getInventory().getHelmet() == null || p.getInventory().getChestplate() == null || p.getInventory().getLeggings() == null || p.getInventory().getBoots() == null){
                        p.sendMessage("§cYou must have a full set of armour on to purchase protection!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        break;
                    }
                    if (checkArmor[0].getEnchantments().isEmpty()){
                        if(p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2)){
                            for (Player player : queuedPlayers){
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))) {
                                    ItemStack[] playerArmor  = player.getInventory().getArmorContents();
                                    playerArmor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    playerArmor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    playerArmor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    playerArmor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                                    playerContainer.set(prot, PersistentDataType.STRING, "one");
                                    player.sendMessage("§6" + p.getName() + " purchased protection 1");
                                }
                            }
                            ItemStack[] armor  = p.getInventory().getArmorContents();
                            armor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            armor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 2));
                            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            container.set(prot, PersistentDataType.STRING, "one");
                            p.sendMessage("§6" + "Purchased Protection 1");
                        }else {
                            p.sendMessage("§cYou do not have enough diamonds to purchase protection 1!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else if (container.get(prot, PersistentDataType.STRING).equals("one")){
                        if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4)){
                            for (Player player : queuedPlayers){
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))) {
                                    ItemStack[] playerArmor  = player.getInventory().getArmorContents();
                                    playerArmor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                                    playerArmor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                                    playerArmor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                                    playerArmor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                                    playerContainer.set(prot, PersistentDataType.STRING, "two");
                                    player.sendMessage("§6" + p.getName() + " purchased protection 2");
                                }
                            }
                            ItemStack[] armor = p.getInventory().getArmorContents();
                            armor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                            armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                            armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                            armor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            container.set(prot, PersistentDataType.STRING, "two");
                            p.sendMessage("§6Purchased Protection 2");
                        }else{
                            p.sendMessage("§cYou do not have enough diamonds to purchase protection 2!");
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else if (container.get(prot, PersistentDataType.STRING).equals("two")) {
                        if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 8)) {
                            for (Player player : queuedPlayers){
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))) {
                                    ItemStack[] playerArmor  = player.getInventory().getArmorContents();
                                    playerArmor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                                    playerArmor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                                    playerArmor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                                    playerArmor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                                    playerContainer.set(prot, PersistentDataType.STRING, "three");
                                    player.sendMessage("§6" + p.getName() + " purchased protection 3");
                                }
                            }
                            ItemStack[] armor = p.getInventory().getArmorContents();
                            armor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                            armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                            armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                            armor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 8));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            container.set(prot, PersistentDataType.STRING, "three");
                            p.sendMessage("§6Purchased Protection 3");
                        } else {
                            p.sendMessage("§cYou do not have enough diamonds to purchase protection 3!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else if (container.get(prot, PersistentDataType.STRING).equals("three")) {
                        if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 16)) {
                            for (Player player : queuedPlayers) {
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))){
                                    ItemStack[] playerArmor  = player.getInventory().getArmorContents();
                                    playerArmor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                                    playerArmor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                                    playerArmor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                                    playerArmor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                                    playerContainer.set(prot, PersistentDataType.STRING, "four");
                                    player.sendMessage("§6" + p.getName() + " purchased protection 4");
                                }
                            }
                            ItemStack[] armor = p.getInventory().getArmorContents();
                            armor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                            armor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                            armor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                            armor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 16));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            container.set(prot, PersistentDataType.STRING, "four");
                            p.sendMessage("§6Purchased Protection 4");
                        } else {
                            p.sendMessage("§cYou do not have enough diamonds to purchase protection 4!");
                            p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else{
                        p.sendMessage("§cYou cannot buy anymore levels of protection!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 15:
                    if (container.get(haste, PersistentDataType.STRING).equals("None")){
                        if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2)){
                            for (Player player : queuedPlayers){
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 0, false, false));
                                    player.sendMessage("§6" + p.getName() + " purchased haste 1");
                                    playerContainer.set(haste, PersistentDataType.STRING, "one");
                                }
                            }
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 0, false, false));
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 2));
                            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            p.sendMessage("§6Purchased Haste 1");
                            container.set(haste, PersistentDataType.STRING, "one");
                        }else {
                            p.sendMessage("§cYou do not have enough diamonds to purchase haste 1!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else if (container.get(haste, PersistentDataType.STRING).equals("one")) {
                        if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4)) {
                            for (Player player : queuedPlayers){
                                PersistentDataContainer playerContainer = player.getPersistentDataContainer();
                                if (team.get(player).equals(team.get(p))) {
                                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 0, false, false));
                                    player.sendMessage("§6" + p.getName() + " purchased haste 2");
                                    playerContainer.set(haste, PersistentDataType.STRING, "two");
                                }
                            }
                            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 1, false, false));
                            p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                            p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            p.sendMessage("§6Purchased Haste 2");
                            container.set(haste, PersistentDataType.STRING, "two");
                        }else {
                            p.sendMessage("§cYou do not have enough diamonds to purchase haste 2!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        }
                    }else {
                        p.sendMessage("§cYou cannot buy anymore haste!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }
}

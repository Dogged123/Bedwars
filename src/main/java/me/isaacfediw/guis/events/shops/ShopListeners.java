package me.isaacfediw.guis.events.shops;

import me.isaacfediw.guis.commands.ItemShopCommand;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class ShopListeners implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        ItemShopCommand shop = new ItemShopCommand();
        Player p = (Player) e.getWhoClicked();

        Component titleComp = e.getView().title();
        String title = LegacyComponentSerializer.legacySection().serialize(titleComp);

        if (title.equalsIgnoreCase("§6Item Shop")) {
            if (e.getCurrentItem() == null) {
                return;
            }
            switch (e.getCurrentItem().getType()) {
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
        } else if (title.equalsIgnoreCase("§6Upgrades Shop")) {

            PlayerData pData;

            if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
            else pData = new PlayerData(p);

            switch (e.getSlot()) {
                case 11:
                    ItemStack sword        = ItemMaker.buildItem(Material.WOODEN_SWORD, true);
                    ItemStack stoneSword   = ItemMaker.buildItem(Material.STONE_SWORD, true);
                    ItemStack ironSword    = ItemMaker.buildItem(Material.IRON_SWORD, true);
                    ItemStack diamondSword = ItemMaker.buildItem(Material.DIAMOND_SWORD, true);

                    if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4) && (p.getInventory().containsAtLeast(sword, 1) || p.getInventory().containsAtLeast(stoneSword, 1) || p.getInventory().containsAtLeast(ironSword, 1) || p.getInventory().containsAtLeast(diamondSword, 1))) {
                        if (pData.getEnchants().get("sharp") > 0) {
                            p.sendMessage("§cYou already have sharpness!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                            return;
                        }

                        PlayerData playerData;

                        for (Player player : queuedPlayers) {
                            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                            else playerData = new PlayerData(player);

                            Map<String, Integer> playerEnchants = playerData.getEnchants();

                            if (playerData.getPlayerTeam().equals(pData.getPlayerTeam())) {
                                for (ItemStack i : player.getInventory().getContents()) {
                                    try {
                                        if (i == null) continue;
                                        if (i.getType().equals(Material.WOODEN_SWORD) || i.getType().equals(Material.STONE_SWORD) || i.getType().equals(Material.IRON_SWORD) || i.getType().equals(Material.DIAMOND_SWORD)) {
                                            i.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                                            playerEnchants.replace("sharp", 1);
                                            playerData.setEnchants(playerEnchants);
                                            if (p != player) player.sendMessage("§6" + p.getName() + " purchased sharpness");
                                        }
                                    } catch (NullPointerException n) {
                                        System.out.print(" ");
                                    }
                                }
                            }
                        }

                        p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 4));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6Purchased Sharpness");
                    } else {
                        p.sendMessage("§cYou do not have enough diamonds to purchase sharpness!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 13:
                    if (p.getInventory().getHelmet() == null || p.getInventory().getChestplate() == null || p.getInventory().getLeggings() == null || p.getInventory().getBoots() == null) {
                        p.sendMessage("§cYou must have a full set of armour on to purchase protection!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                        break;
                    }

                    int protLevel = pData.getEnchants().get("prot");
                    if (protLevel < 4) {
                        int reqDiamonds = (int) Math.pow(2, protLevel + 1);

                        if (!p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), reqDiamonds)) {
                            p.sendMessage("§cYou do not have enough diamonds to purchase protection " + (protLevel + 1) + "!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                            break;
                        }

                        PlayerData playerData;

                        for (Player player : queuedPlayers) {
                            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                            else playerData = new PlayerData(player);

                            Map<String, Integer> playerEnchants = playerData.getEnchants();

                            if (playerData.getPlayerTeam().equals(pData.getPlayerTeam())) {
                                ItemStack[] playerArmor = player.getInventory().getArmorContents();
                                playerArmor[0].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel + 1);
                                playerArmor[1].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel + 1);
                                playerArmor[2].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel + 1);
                                playerArmor[3].addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, protLevel + 1);

                                playerEnchants.replace("prot", protLevel + 1);
                                playerData.setEnchants(playerEnchants);

                                playerData.setArmourItem(0, player.getInventory().getHelmet());
                                playerData.setArmourItem(1, player.getInventory().getChestplate());
                                playerData.setArmourItem(2, player.getInventory().getLeggings());
                                playerData.setArmourItem(3, player.getInventory().getBoots());

                                if (p != player) player.sendMessage("§6" + p.getName() + " purchased protection " + (protLevel + 1));
                            }
                        }

                        p.getInventory().removeItem(new ItemStack(Material.DIAMOND, reqDiamonds));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6" + "Purchased Protection " + (protLevel + 1));

                    } else {
                        p.sendMessage("§cYou cannot buy anymore levels of protection!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
                case 15:
                    int hasteLevel = pData.getEnchants().get("haste");
                    if (hasteLevel < 2) {
                        int reqDiamonds = (int) Math.pow(2, hasteLevel + 1);

                        if (!p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), reqDiamonds)) {
                            p.sendMessage("§cYou do not have enough diamonds to purchase haste " + (hasteLevel + 1) + "!");
                            p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                            break;
                        }

                        PlayerData playerData;

                        for (Player player : queuedPlayers) {
                            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                            else playerData = new PlayerData(player);

                            Map<String, Integer> playerEnchants = playerData.getEnchants();

                            if (playerData.getPlayerTeam().equals(pData.getPlayerTeam())) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, hasteLevel, false, false));

                                playerEnchants.replace("haste", hasteLevel + 1);
                                playerData.setEnchants(playerEnchants);

                                if (p != player) player.sendMessage("§6" + p.getName() + " purchased haste " + (hasteLevel + 1));
                            }
                        }

                        p.getInventory().removeItem(new ItemStack(Material.DIAMOND, reqDiamonds));
                        p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                        p.sendMessage("§6" + "Purchased Haste " + (hasteLevel + 1));
                    } else {
                        p.sendMessage("§cYou cannot buy anymore haste!");
                        p.playSound(p, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 0);
                    }
                    break;
            }
            e.setCancelled(true);
        }
    }
}
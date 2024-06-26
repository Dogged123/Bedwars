package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.Vanish;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import static me.isaacfediw.guis.commands.queue.queuedPlayers;

public class itemManager implements Listener {

    GUIs plugin;
    private int length;
    private int vanishLength;
    public itemManager(GUIs plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        if (!queuedPlayers.contains(p)){
            return;
        }
        length = 2;
        if (p.getItemInUse().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Invisibility Potion")){
            vanishLength = 30;
            Vanish v = new Vanish(plugin);
            v.vanishPlayer(p);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (vanishLength <= 0){
                        v.vanishPlayer(p);
                        cancel();
                    }
                    vanishLength --;
                }
            }.runTaskTimer(plugin, 0, 20);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (length <= 0){
                    cancel();
                }
                if(p.getInventory().contains(Material.GLASS_BOTTLE)){
                    p.getInventory().remove(Material.GLASS_BOTTLE);
                }
                length --;
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    @EventHandler
    public void onToolThrow(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if (!queuedPlayers.contains(p)){
            return;
        }
        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
        ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setUnbreakable(true);
        if (container.get(sharp, PersistentDataType.STRING).equals("true")){
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
;        }
        sword.setItemMeta(swordMeta);
        if (e.getItemDrop().getItemStack().equals(sword)){
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType().equals(Material.STONE_SWORD) ||
                e.getItemDrop().getItemStack().getType().equals(Material.IRON_SWORD) ||
                e.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_SWORD)){
            if (p.getInventory().contains(Material.STONE_SWORD) ||
                    p.getInventory().contains(Material.IRON_SWORD) ||
                    p.getInventory().contains(Material.DIAMOND_SWORD)){
                return;
            }
            p.getInventory().addItem(sword);
        }

        //disables dropping of axe and pickaxe
        if (e.getItemDrop().getItemStack().getType().toString().contains("AXE")) {
            e.setCancelled(true);
        }

        //disables dropping of shears
        if (e.getItemDrop().getItemStack().getType().equals(Material.SHEARS)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwordPickup(EntityPickupItemEvent e){
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (!queuedPlayers.contains(p)) return;

        if (e.getItem().getItemStack().getType().equals(Material.STONE_SWORD) ||
                e.getItem().getItemStack().getType().equals(Material.IRON_SWORD) ||
                e.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)){
            if (p.getInventory().contains(Material.WOODEN_SWORD)){
                p.getInventory().remove(Material.WOODEN_SWORD);
            }
        }
    }
}

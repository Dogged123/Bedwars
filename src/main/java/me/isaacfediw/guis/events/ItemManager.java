package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.VanishCommand;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class ItemManager implements Listener {

    private final GUIs plugin;
    private int vanishLength;

    public ItemManager(GUIs plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (!queuedPlayers.contains(p)) return;

        if (p.getActiveItem().getItemMeta() == null) return;

        Component displayNameComp = p.getActiveItem().getItemMeta().displayName();
        if (displayNameComp == null) return;
        String displayName = LegacyComponentSerializer.legacySection().serialize(displayNameComp);

        if (displayName.equalsIgnoreCase("§dInvisibility Potion")) {
            vanishLength = 30;
            VanishCommand v = new VanishCommand(plugin);
            v.vanishPlayer(p);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (vanishLength <= 0) {
                        v.vanishPlayer(p);
                        cancel();
                    }
                    vanishLength--;
                }
            }.runTaskTimer(plugin, 0, 20);
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (p.getInventory().contains(Material.GLASS_BOTTLE)) {
                p.getInventory().remove(Material.GLASS_BOTTLE);
            }
        }, 2);
    }

    @EventHandler
    public void onTNTPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().equals(Material.TNT)) {
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation(), EntityType.PRIMED_TNT);
        }
    }

    @EventHandler
    public void onFireChargeRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null && e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6Throwable Fireball")){
                Fireball fireball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Fireball.class);
                fireball.setVelocity(e.getPlayer().getLocation().getDirection());
                fireball.setYield(3);
                e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onToolThrow(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!queuedPlayers.contains(p)) {
            return;
        }

        PlayerData pData;

        if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
        else pData = new PlayerData(p);

        ItemStack sword = ItemMaker.buildItem(Material.WOODEN_SWORD, true);
        ItemMeta swordMeta = sword.getItemMeta();

        if (swordMeta != null && pData.getEnchants().get("sharp") > 0)
            swordMeta.addEnchant(Enchantment.DAMAGE_ALL, pData.getEnchants().get("sharp"), true);

        sword.setItemMeta(swordMeta);

        if (e.getItemDrop().getItemStack().equals(sword)) {
            e.setCancelled(true);
        }
        if (e.getItemDrop().getItemStack().getType().equals(Material.STONE_SWORD) ||
                e.getItemDrop().getItemStack().getType().equals(Material.IRON_SWORD) ||
                e.getItemDrop().getItemStack().getType().equals(Material.DIAMOND_SWORD)) {
            if (p.getInventory().contains(Material.STONE_SWORD) ||
                    p.getInventory().contains(Material.IRON_SWORD) ||
                    p.getInventory().contains(Material.DIAMOND_SWORD)) {
                return;
            }
            p.getInventory().addItem(sword);
        }

        // disables dropping of axe and pickaxe
        if (e.getItemDrop().getItemStack().getType().toString().contains("AXE")) {
            e.setCancelled(true);
        }

        // disables dropping of shears
        if (e.getItemDrop().getItemStack().getType().equals(Material.SHEARS)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwordPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (!queuedPlayers.contains(p)) return;

        if (e.getItem().getItemStack().getType().equals(Material.STONE_SWORD) ||
                e.getItem().getItemStack().getType().equals(Material.IRON_SWORD) ||
                e.getItem().getItemStack().getType().equals(Material.DIAMOND_SWORD)) {
            if (p.getInventory().contains(Material.WOODEN_SWORD)) {
                p.getInventory().remove(Material.WOODEN_SWORD);
            }
        }
    }
}
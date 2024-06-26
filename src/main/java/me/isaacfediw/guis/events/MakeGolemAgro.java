package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Golem;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

import static me.isaacfediw.guis.commands.queue.queuedPlayers;

public class MakeGolemAgro implements Listener {

    GUIs plugin;
    private final HashMap<IronGolem, Player> playerGolems = new HashMap<>();
    public MakeGolemAgro(GUIs p){
        plugin = p;
    }
    @EventHandler
    public void onIronGolemSpawn(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (p.getInventory().getItemInMainHand().equals(new ItemStack(Material.IRON_GOLEM_SPAWN_EGG))) {
                e.setCancelled(true);
                IronGolem golem = (IronGolem) p.getWorld().spawnEntity(p.getLocation(), EntityType.IRON_GOLEM);
                playerGolems.put(golem, p);
                p.getInventory().removeItem(new ItemStack(Material.IRON_GOLEM_SPAWN_EGG));

                new BukkitRunnable() {
                    int life = 240;

                    @Override
                    public void run() {
                        if (life <= 0) {
                            playerGolems.remove(golem);
                            golem.setHealth(0);
                            cancel();
                        }
                        for (Player player : queuedPlayers) {
                            PersistentDataContainer container = player.getPersistentDataContainer();
                            PersistentDataContainer container2 = playerGolems.get(golem).getPersistentDataContainer();
                            NamespacedKey team = new NamespacedKey(plugin, "Team");
                            if (!(container.get(team, PersistentDataType.STRING).equals(container2.get(team, PersistentDataType.STRING))) && player.getLocation().distance(golem.getLocation()) <= 20) {
                                golem.setTarget(player);
                            }
                        }
                        life--;
                    }
                }.runTaskTimer(plugin, 0, 5);
            }
        }
    }

    @EventHandler
    public void playerHitGolem(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (e.getEntity() instanceof IronGolem) {
                IronGolem golem = (IronGolem) e.getEntity();
                PersistentDataContainer container = p.getPersistentDataContainer();
                PersistentDataContainer container2 = playerGolems.get(golem).getPersistentDataContainer();
                NamespacedKey team = new NamespacedKey(plugin, "Team");
                if (container.get(team, PersistentDataType.STRING).equals(container2.get(team, PersistentDataType.STRING))){
                    e.setCancelled(true);
                }
            }
        }
    }
}

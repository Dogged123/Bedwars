package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class MakeGolemAgro implements Listener {

    private final GUIs plugin;
    private final HashMap<IronGolem, Player> playerGolems = new HashMap<>();
    public MakeGolemAgro(GUIs p) {
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

                        PlayerData playerData;
                        PlayerData golemPlayerData;

                        for (Player player : queuedPlayers) {
                            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                            else playerData = new PlayerData(player);

                            if (PlayerData.playersData.containsKey(playerGolems.get(golem))) golemPlayerData = PlayerData.playersData.get(playerGolems.get(golem));
                            else golemPlayerData = new PlayerData(playerGolems.get(golem));

                            //if (!(team.get(p).equals(team.get(playerGolems.get(golem)))) && player.getLocation().distance(golem.getLocation()) <= 20) {
                            if (!playerData.getPlayerTeam().equals(golemPlayerData.getPlayerTeam()) && player.getLocation().distance(golem.getLocation()) <= 20) {
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

                PlayerData playerData;
                if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
                else playerData = new PlayerData(p);

                PlayerData golemPlayerData;
                if (PlayerData.playersData.containsKey(playerGolems.get(golem))) golemPlayerData = PlayerData.playersData.get(playerGolems.get(golem));
                else golemPlayerData = new PlayerData(playerGolems.get(golem));

                //if (team.get(p).equals(team.get(playerGolems.get(golem)))) {
                if (playerData.getPlayerTeam().equals(golemPlayerData.getPlayerTeam())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}

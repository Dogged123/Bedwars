package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.OpenScoreboard;
import me.isaacfediw.guis.commands.StopCommand;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

import static me.isaacfediw.guis.commands.QueueCommand.*;
import static me.isaacfediw.guis.events.GameEvents.aliveTeams;
import static me.isaacfediw.guis.events.GameEvents.teams;

public class PlayerDeathEvent implements Listener {

    GUIs plugin;

    public PlayerDeathEvent(GUIs p) {
        plugin = p;
    }

    @EventHandler
    public void playerDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (!queuedPlayers.contains(p)) return;

        if (p.getKiller() != null) {
            for (ItemStack item : p.getInventory()) {
                if (item == null) continue;
                if (item.getType().toString().contains("INGOT")) {
                    p.getKiller().getInventory().addItem(item);
                }
                if (item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.EMERALD)) {
                    p.getKiller().getInventory().addItem(item);
                }
            }
        }

        PlayerData playerData;

        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        if (playerData.getLifeStatus().equals("Bed_Broken") || playerData.getLifeStatus().equals("Dead")) {
            playerData.setLifeStatus("Dead");

            for (List<String> team : teams) {
                team.remove(p.getName());
                if (team.isEmpty()) aliveTeams--;
            }

            if (aliveTeams == 1) {
                StopCommand stop = new StopCommand(plugin);
                stop.stopGame();
            }
            p.sendMessage("§cEliminated!");
            p.setGameMode(GameMode.SPECTATOR);

            OpenScoreboard sb = new OpenScoreboard();

            switch (playerData.getPlayerTeam()) {
                case "Red":
                    OpenScoreboard.redStatus = ("✖");
                    break;
                case "Yellow":
                    OpenScoreboard.yellowStatus = ("✖");
                    break;
                case "Blue":
                    OpenScoreboard.blueStatus = ("✖");
                    break;
                case "Black":
                    OpenScoreboard.blackStatus = ("✖");
                    break;
            }

            sb.setInitialScoreboard(queuedPlayers);

            if (p.getLocation().getY() <= 0 && p.getKiller() != null) {
                e.setDeathMessage("§c" + p.getName() + " was hit into the void by " + p.getKiller().getName() + ". §c§lFINAL KILL!");
            } else if (p.getLocation().getY() <= 0) {
                e.setDeathMessage("§c" + p.getName() + " fell into the void. §c§lFINAL KILL!");
            } else if (p.getKiller() != null) {
                e.setDeathMessage("§c" + p.getName() + " was lethally slapped by " + p.getKiller().getName() + ". §c§lFINAL KILL!");
            } else {
                e.setDeathMessage("§c" + p.getName() + " was killed by Covid 19. §c§lFINAL KILL!");
            }
            return;
        }

        if (p.getLocation().getY() <= 0 && p.getKiller() != null) {
            e.setDeathMessage("§c" + p.getName() + " was hit into the void by " + p.getKiller().getName() + ". ");
        } else if (p.getLocation().getY() <= 0) {
            e.setDeathMessage("§c" + p.getName() + " fell into the void.");
        } else if (p.getKiller() != null) {
            e.setDeathMessage("§c" + p.getName() + " was lethally slapped by " + p.getKiller().getName());
        } else {
            e.setDeathMessage("§c" + p.getName() + " was killed by Covid 19");
        }
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (!queuedPlayers.contains(p)) {
            return;
        }

        PlayerData playerData;
        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        String team = playerData.getPlayerTeam();

        if (playerData.getLifeStatus().equals("Bed_Broken") || playerData.getLifeStatus().equals("Dead")) {
            return;
        }

        p.getInventory().clear();
        Location queLoc = plugin.getConfig().getLocation("Que");
        if (queLoc != null) p.teleport(queLoc);
        p.setGameMode(GameMode.SPECTATOR);

        new BukkitRunnable() {
            Integer deathTimeLeft = 5;

            @Override
            public void run() {
                if (deathTimeLeft == 0) {
                    Location redLoc = plugin.getConfig().getLocation("Red");
                    Location yellowLoc = plugin.getConfig().getLocation("Yellow");
                    Location blueLoc = plugin.getConfig().getLocation("Blue");
                    Location blackLoc = plugin.getConfig().getLocation("Black");

                    switch (team) {
                        case "Red":
                            if (redLoc != null) p.teleport(redLoc);
                            break;
                        case "Yellow":
                            if (yellowLoc != null) p.teleport(yellowLoc);
                            break;
                        case "Blue":
                            if (blueLoc != null) p.teleport(blueLoc);
                            break;
                        case "Black":
                            if (blackLoc != null)  p.teleport(blackLoc);
                            break;
                    }
                    p.setGameMode(GameMode.SURVIVAL);

                    //Restore wooden sword
                    ItemStack sword = ItemMaker.buildItem(Material.WOODEN_SWORD, true);
                    ItemMeta swordMeta = sword.getItemMeta();

                    if (swordMeta != null && playerData.getEnchants().get("sharp") > 0)
                        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, playerData.getEnchants().get("sharp"), true);

                    sword.setItemMeta(swordMeta);
                    p.getInventory().addItem(sword);

                    //Restore armour
                    p.getInventory().setHelmet(playerData.getArmour()[0]);
                    p.getInventory().setChestplate(playerData.getArmour()[1]);
                    p.getInventory().setLeggings(playerData.getArmour()[2]);
                    p.getInventory().setBoots(playerData.getArmour()[3]);

                    //Restore tools
                    Map<String, Boolean> tools = playerData.getPermItems();
                    if (tools.get("pick")) p.getInventory().addItem(new ItemStack(Material.WOODEN_PICKAXE));
                    if (tools.get("axe")) p.getInventory().addItem(new ItemStack(Material.WOODEN_AXE));
                    if (tools.get("shears")) p.getInventory().addItem(new ItemStack(Material.SHEARS));

                    p.sendTitle("§aRespawned!", "", 5, 20, 5);
                    cancel();
                    return;
                }
                p.sendTitle("§cYou died!", "§c" + deathTimeLeft, 0, 20, 0);
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_HAT, 10, 1);
                deathTimeLeft--;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}

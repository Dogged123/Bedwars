package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.bedwarsStop;
import me.isaacfediw.guis.commands.openScoreboard;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

import static me.isaacfediw.guis.commands.queue.queuedPlayers;
import static me.isaacfediw.guis.events.blockBreakControl.breakableBlocks;

public class gameLogicEvents implements Listener {

    public static List<List<String>> teams = new ArrayList<>();
    List<String> red = new ArrayList<>();
    List<String> yellow = new ArrayList<>();
    List<String> blue = new ArrayList<>();
    List<String> black = new ArrayList<>();

    Location redBedLoc;
    Location yellowBedLoc;
    Location blueBedLoc;
    Location blackBedLoc;
    public static int aliveTeams = 0;
    GUIs plugin;

    public gameLogicEvents(GUIs p){
        plugin = p;

        teams.add(red);
        teams.add(yellow);
        teams.add(blue);
        teams.add(black);
    }
    public void breakBed(Player p, String bed, Location bedLoc) {
        openScoreboard sb = new openScoreboard();

        Location queLoc = plugin.getConfig().getLocation("Que");
        for (Player player : queuedPlayers){
            PersistentDataContainer container = player.getPersistentDataContainer();
            NamespacedKey teamName = new NamespacedKey(plugin, "Team");
            NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
            if (player.isOnline()) {
                if (container.get(teamName, PersistentDataType.STRING).equals("Red") && bed.equalsIgnoreCase("Red")){
                    redBedLoc = bedLoc;
                    player.sendMessage(ChatColor.RED + "Your bed has been destroyed!");
                    player.setBedSpawnLocation(queLoc, true);
                    container.set(lifeStatus, PersistentDataType.STRING, "Bed_Broken");
                    sb.setRedScoreboard();
                }else if (container.get(teamName, PersistentDataType.STRING).equals("Yellow") && bed.equalsIgnoreCase("Yellow")){
                    yellowBedLoc = bedLoc;
                    player.sendMessage(ChatColor.RED + "Your bed has been destroyed!");
                    player.setBedSpawnLocation(queLoc, true);
                    container.set(lifeStatus, PersistentDataType.STRING, "Bed_Broken");
                    sb.setYellowScoreboard();
                }else if (container.get(teamName, PersistentDataType.STRING).equals("Blue") && bed.equalsIgnoreCase("Blue")){
                    blueBedLoc = bedLoc;
                    player.sendMessage(ChatColor.RED + "Your bed has been destroyed!");
                    player.setBedSpawnLocation(queLoc, true);
                    container.set(lifeStatus, PersistentDataType.STRING, "Bed_Broken");
                    sb.setBlueScoreboard();
                }else if (container.get(teamName, PersistentDataType.STRING).equals("Black") && bed.equalsIgnoreCase("Black")){
                    blackBedLoc = bedLoc;
                    player.sendMessage(ChatColor.RED + "Your bed has been destroyed!");
                    player.setBedSpawnLocation(queLoc, true);
                    container.set(lifeStatus, PersistentDataType.STRING, "Bed_Broken");
                    sb.setBlackScoreboard();
                }
            }
        }
        switch (bed) {
            case "Red":
                Bukkit.broadcastMessage(ChatColor.RED + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Yellow":
                Bukkit.broadcastMessage(ChatColor.YELLOW + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Blue":
                Bukkit.broadcastMessage(ChatColor.BLUE + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Black":
                Bukkit.broadcastMessage(ChatColor.BLACK + bed + " bed has been broken by " + p.getName() + "!");
                break;
        }
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);
        }
    }

    public void restoreBeds() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

        Bukkit.dispatchCommand(console, "setblock 71 71 68 red_bed[part=foot,facing=north]");
        Bukkit.dispatchCommand(console, "setblock 71 71 67 red_bed[part=head,facing=north]");

        Bukkit.dispatchCommand(console, "setblock 143 71 -4 yellow_bed[part=foot,facing=west]");
        Bukkit.dispatchCommand(console, "setblock 142 71 -4 yellow_bed[part=head,facing=west]");

        Bukkit.dispatchCommand(console, "setblock -1 71 -4 blue_bed[part=foot,facing=east]");
        Bukkit.dispatchCommand(console, "setblock 0 71 -4 blue_bed[part=head,facing=east]");

        Bukkit.dispatchCommand(console, "setblock 71 71 -76 black_bed[part=foot,facing=south]");
        Bukkit.dispatchCommand(console, "setblock 71 71 -75 black_bed[part=head,facing=south]");
    }

    public void addToTeam(int index, Player p) {
        teams.get(index).add(p.getName());
    }
    @EventHandler
    public void finalDeath(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
        if (container.get(lifeStatus, PersistentDataType.STRING).equals("Bed_Broken") || container.get(lifeStatus, PersistentDataType.STRING).equals("Dead")){
            container.set(lifeStatus, PersistentDataType.STRING, "Dead");
            for (List<String> team : teams) {
                if (team.size() == 0) continue;
                team.remove(p.getName());

                if (team.size() == 0) {
                    aliveTeams --;
                }
            }

            if (aliveTeams == 1){
                bedwarsStop stop = new bedwarsStop(plugin);
                stop.stopGame();
            }
            p.sendMessage(ChatColor.RED + "Eliminated!");
            p.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            openScoreboard sb = new openScoreboard();
            sb.setHealthScoreboard();
        }
    }

    public void startGame() {
        Location redLoc = plugin.getConfig().getLocation("Red");
        Location blueLoc = plugin.getConfig().getLocation("Blue");
        Location yellowLoc = plugin.getConfig().getLocation("Yellow");
        Location blackLoc = plugin.getConfig().getLocation("Black");
        Location diamond1Loc = plugin.getConfig().getLocation("Diamond1");
        Location diamond2Loc = plugin.getConfig().getLocation("Diamond2");
        Location diamond3Loc = plugin.getConfig().getLocation("Diamond3");
        Location diamond4Loc = plugin.getConfig().getLocation("Diamond4");
        Location emerald1Loc = plugin.getConfig().getLocation("Emerald1");
        Location emerald2Loc = plugin.getConfig().getLocation("Emerald2");
        Location emerald3Loc = plugin.getConfig().getLocation("Emerald3");
        Location emerald4Loc = plugin.getConfig().getLocation("Emerald4");

        plugin.generator(redLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(redLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(blueLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(blueLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(yellowLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(yellowLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(blackLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(blackLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(diamond1Loc, 800, "DIAMOND", 1000);
        plugin.generator(diamond2Loc, 800, "DIAMOND", 1000);
        plugin.generator(diamond3Loc, 800, "DIAMOND", 1000);
        plugin.generator(diamond4Loc, 800, "DIAMOND", 1000);
        plugin.generator(emerald1Loc, 2000, "EMERALD", 1000);
        plugin.generator(emerald2Loc, 2000, "EMERALD", 1000);
        plugin.generator(emerald3Loc, 2000, "EMERALD", 1000);
        plugin.generator(emerald4Loc, 2000, "EMERALD", 1000);

        restoreBeds();

        openScoreboard sb = new openScoreboard();

        for (Player player : queuedPlayers){
            PersistentDataContainer container = player.getPersistentDataContainer();
            NamespacedKey teamName = new NamespacedKey(plugin, "Team");
            NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
            if (container.get(teamName, PersistentDataType.STRING).equals("Red")){
                player.teleport(redLoc);
                player.setBedSpawnLocation(redLoc, true);
            }else if (container.get(teamName, PersistentDataType.STRING).equals("Yellow")){
                player.teleport(yellowLoc);
                player.setBedSpawnLocation(yellowLoc, true);
            }else if (container.get(teamName, PersistentDataType.STRING).equals("Blue")){
                player.teleport(blueLoc);
                player.setBedSpawnLocation(blueLoc, true);
            }else if (container.get(teamName, PersistentDataType.STRING).equals("Black")){
                player.teleport(blackLoc);
                player.setBedSpawnLocation(blackLoc, true);
            }
            container.set(lifeStatus, PersistentDataType.STRING, "Has_Bed");
            sb.setInitialScoreboard(player);
            sb.setHealthScoreboard();

            ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
            ItemMeta swordMeta = sword.getItemMeta();
            swordMeta.setUnbreakable(true);
            sword.setItemMeta(swordMeta);
            player.getInventory().addItem(sword);
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(20);
            player.setHealth(20);
        }
    }

    public void stop() {
        openScoreboard sb = new openScoreboard();
        openScoreboard.redStatus = "✔";
        openScoreboard.yellowStatus = "✔";
        openScoreboard.blueStatus = "✔";
        openScoreboard.blackStatus = "✔";
        aliveTeams = 0;

        for (Player player : queuedPlayers) {
            if (player != null && player.isOnline()) {
                PersistentDataContainer container = player.getPersistentDataContainer();
                NamespacedKey teamName = new NamespacedKey(plugin, "Team");
                NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
                NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
                NamespacedKey prot = new NamespacedKey(plugin, "prot");
                NamespacedKey haste = new NamespacedKey(plugin, "haste");

                Location queLoc = plugin.getConfig().getLocation("Que");
                player.setPlayerListName(ChatColor.WHITE + player.getName());
                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
                player.teleport(queLoc);
                player.setBedSpawnLocation(queLoc, true);
                player.getEnderChest().clear();
                sb.setInitialScoreboard(player);

                container.set(teamName, PersistentDataType.STRING, "None");
                container.set(lifeStatus, PersistentDataType.STRING, "N/A");
                container.set(sharp, PersistentDataType.STRING, "N/A");
                container.set(prot, PersistentDataType.STRING, "N/A");
                container.set(haste, PersistentDataType.STRING, "N/A");
            }
        }

        for (List<String> team : teams) {
            team.clear();
        }

        for (Block b : breakableBlocks){
            b.setType(Material.AIR);
        }
        queuedPlayers.clear();
        breakableBlocks.clear();

        for (Entity entity : Bukkit.getWorld("world").getEntities()){
            if (entity.getType().equals(EntityType.DROPPED_ITEM)){
                entity.remove();
            }
        }
    }
}

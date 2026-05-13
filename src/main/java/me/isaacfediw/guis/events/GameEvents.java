package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.OpenScoreboard;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static me.isaacfediw.guis.commands.QueueCommand.*;
import static me.isaacfediw.guis.events.BlockEvents.breakableBlocks;

public class GameEvents implements Listener {

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

    public GameEvents(GUIs p) {
        plugin = p;

        teams.add(red);
        teams.add(yellow);
        teams.add(blue);
        teams.add(black);
    }
    public void breakBed(Player p, String bed, Location bedLoc) {
        OpenScoreboard sb = new OpenScoreboard();
        PlayerData playerData;

        Location queLoc = plugin.getConfig().getLocation("Que");
        for (Player player : queuedPlayers) {
            if (player.isOnline()) {
                if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                else playerData = new PlayerData(player);

                if (playerData.getPlayerTeam().equals("Red") && bed.equalsIgnoreCase("Red")) {
                    redBedLoc = bedLoc;
                    sb.setRedScoreboard();
                } else if (playerData.getPlayerTeam().equals("Yellow") && bed.equalsIgnoreCase("Yellow")) {
                    yellowBedLoc = bedLoc;
                    sb.setYellowScoreboard();
                } else if (playerData.getPlayerTeam().equals("Blue") && bed.equalsIgnoreCase("Blue")) {
                    blueBedLoc = bedLoc;
                    sb.setBlueScoreboard();
                } else if (playerData.getPlayerTeam().equals("Black") && bed.equalsIgnoreCase("Black")) {
                    blackBedLoc = bedLoc;
                    sb.setBlackScoreboard();
                }

                if (playerData.getPlayerTeam().equalsIgnoreCase(bed)) {
                    player.sendMessage("§cYour bed has been destroyed!");
                    player.setBedSpawnLocation(queLoc, true);
                    playerData.setLifeStatus("Bed_Broken");
                }
            }
        }

        sb.setInitialScoreboard(queuedPlayers);

        switch (bed) {
            case "Red":
                Bukkit.broadcastMessage("§c" + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Yellow":
                Bukkit.broadcastMessage("§e" + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Blue":
                Bukkit.broadcastMessage("§1" + bed + " bed has been broken by " + p.getName() + "!");
                break;
            case "Black":
                Bukkit.broadcastMessage("§0" + bed + " bed has been broken by " + p.getName() + "!");
                break;
        }
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            onlinePlayers.playSound(onlinePlayers.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 10, 1);
        }
    }

    private void restoreBeds() {
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

    private void startGens(Location redLoc, Location yellowLoc, Location blueLoc, Location blackLoc) {
        Location diamond1Loc = plugin.getConfig().getLocation("Diamond1") == null ? null : plugin.getConfig().getLocation("Diamond1");
        Location diamond2Loc = plugin.getConfig().getLocation("Diamond2") == null ? null : plugin.getConfig().getLocation("Diamond2");
        Location diamond3Loc = plugin.getConfig().getLocation("Diamond3") == null ? null : plugin.getConfig().getLocation("Diamond3");
        Location diamond4Loc = plugin.getConfig().getLocation("Diamond4") == null ? null : plugin.getConfig().getLocation("Diamond4");
        Location emerald1Loc = plugin.getConfig().getLocation("Emerald1") == null ? null : plugin.getConfig().getLocation("Emerald1");
        Location emerald2Loc = plugin.getConfig().getLocation("Emerald2") == null ? null : plugin.getConfig().getLocation("Emerald2");
        Location emerald3Loc = plugin.getConfig().getLocation("Emerald3") == null ? null : plugin.getConfig().getLocation("Emerald3");
        Location emerald4Loc = plugin.getConfig().getLocation("Emerald4") == null ? null : plugin.getConfig().getLocation("Emerald4");

        plugin.generator(redLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(redLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(yellowLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(yellowLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(blueLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(blueLoc, 400, "GOLD_INGOT", 1000);
        plugin.generator(blackLoc, 40, "IRON_INGOT", 1000);
        plugin.generator(blackLoc, 400, "GOLD_INGOT", 1000);

        if (diamond1Loc != null) plugin.generator(diamond1Loc, 800, "DIAMOND", 1000);
        if (diamond2Loc != null) plugin.generator(diamond2Loc, 800, "DIAMOND", 1000);
        if (diamond3Loc != null) plugin.generator(diamond3Loc, 800, "DIAMOND", 1000);
        if (diamond4Loc != null) plugin.generator(diamond4Loc, 800, "DIAMOND", 1000);
        if (emerald1Loc != null) plugin.generator(emerald1Loc, 2000, "EMERALD", 1000);
        if (emerald2Loc != null) plugin.generator(emerald2Loc, 2000, "EMERALD", 1000);
        if (emerald3Loc != null) plugin.generator(emerald3Loc, 2000, "EMERALD", 1000);
        if (emerald4Loc != null) plugin.generator(emerald4Loc, 2000, "EMERALD", 1000);
    }

    public void addToTeam(int index, String team, Player p) {
        PlayerData playerData;

        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        playerData.setPlayerTeam(team);
        teams.get(index).add(p.getName());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (queuedPlayers.contains((Player) e.getEntity())) {
                OpenScoreboard sb = new OpenScoreboard();
                sb.updateHealthScoreboard();
            }
        }
    }

    public void startGame() {
        Location redLoc = plugin.getConfig().getLocation("Red");
        Location yellowLoc = plugin.getConfig().getLocation("Yellow");
        Location blueLoc = plugin.getConfig().getLocation("Blue");
        Location blackLoc = plugin.getConfig().getLocation("Black");

        if (redLoc == null) {
            Bukkit.broadcastMessage("§cRed base is not set up!");
            return;
        }

        if (yellowLoc == null) {
            Bukkit.broadcastMessage("§cYellow base is not set up!");
            return;
        }

        if (blueLoc == null) {
            Bukkit.broadcastMessage("§cBlue base is not set up!");
            return;
        }

        if (blackLoc == null) {
            Bukkit.broadcastMessage("§cBlack base is not set up!");
            return;
        }


        restoreBeds();
        startGens(redLoc, yellowLoc, blueLoc, blackLoc);

        OpenScoreboard sb = new OpenScoreboard();

        Location bedLoc = null;

        PlayerData playerData;

        for (Player player : queuedPlayers) {
            if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
            else playerData = new PlayerData(player);

            //switch (team.get(player))) {
            switch (playerData.getPlayerTeam()) {
                case "Red":
                    bedLoc = redLoc;
                    break;
                case "Yellow":
                    bedLoc = yellowLoc;
                    break;
                case "Blue":
                    bedLoc = blueLoc;
                    break;
                case "Black":
                    bedLoc = blackLoc;
                    break;
            }

            if (bedLoc != null) {
                player.teleport(bedLoc);
                player.setBedSpawnLocation(bedLoc, true);
            }

            playerData.setLifeStatus("Has_Bed");

            ItemStack sword = ItemMaker.buildItem(Material.WOODEN_SWORD, true);
            player.getInventory().addItem(sword);
            player.setGameMode(GameMode.SURVIVAL);
            player.setFoodLevel(20);
            player.setHealth(20);
        }

        sb.setInitialScoreboard(queuedPlayers);
    }

    public void stop() {
        OpenScoreboard sb = new OpenScoreboard();
        OpenScoreboard.redStatus = "✔";
        OpenScoreboard.yellowStatus = "✔";
        OpenScoreboard.blueStatus = "✔";
        OpenScoreboard.blackStatus = "✔";
        aliveTeams = 0;

        PlayerData playerData;

        for (Player player : queuedPlayers) {
            if (player != null && player.isOnline()) {
                if (PlayerData.playersData.containsKey(player)) playerData = PlayerData.playersData.get(player);
                else playerData = new PlayerData(player);

                Location queLoc = plugin.getConfig().getLocation("Que");

                player.setPlayerListName("§f" + player.getName());
                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
                if (queLoc != null) player.teleport(queLoc);
                player.setBedSpawnLocation(queLoc, true);
                player.getEnderChest().clear();

                playerData.setLifeStatus("N/A");
                playerData.setPlayerTeam("N/A");

                playerData.setEnchants(new HashMap<String, Integer>(){{
                    put("sharp", 0);
                    put("prot", 0);
                    put("haste", 0);
                }});
            }
        }

        sb.removeScoreboard(queuedPlayers);

        for (List<String> team : teams) {
            team.clear();
        }

        for (Block b : breakableBlocks) {
            b.setType(Material.AIR);
        }
        queuedPlayers.clear();
        breakableBlocks.clear();

        World world = Bukkit.getWorld("world");
        if (world == null) return;

        for (Entity entity : world.getEntities()) {
            if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                entity.remove();
            }
        }
    }
}

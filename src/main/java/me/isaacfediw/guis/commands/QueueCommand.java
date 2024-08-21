package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static me.isaacfediw.guis.events.GameEvents.aliveTeams;
import static me.isaacfediw.guis.events.GameEvents.teams;

public class QueueCommand implements CommandExecutor {
    public static ArrayList<Player> queuedPlayers = new ArrayList<>();
    public static Map<Player, String> lifeStatus = new HashMap<>();
    public static Map<Player, String> team = new HashMap<>();

    private int timeLeft;
    GUIs plugin;

    public QueueCommand(GUIs plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            PersistentDataContainer container = p.getPersistentDataContainer();
            NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
            NamespacedKey prot = new NamespacedKey(plugin, "prot");
            NamespacedKey haste = new NamespacedKey(plugin, "haste");

            if (!lifeStatus.containsKey(p)) lifeStatus.put(p, "N/A");
            if (!team.containsKey(p)) team.put(p, "N/A");

            if (queuedPlayers.contains(p) && args.length >= 1) {
                if (!lifeStatus.get(p).equals("In_Queue")) {
                    p.sendMessage("§cYou cannot leave the queue while in the game!");
                    return true;
                }

                queuedPlayers.remove(p);
                p.setPlayerListName("§f" + p.getName());
                p.getInventory().clear();
                p.sendMessage("§aYou left the queue!");

                switch (team.get(p)) {
                    case "Red":
                        teams.get(0).remove(p.getName());
                        break;
                    case "Yellow":
                        teams.get(1).remove(p.getName());
                        break;
                    case "Blue":
                        teams.get(2).remove(p.getName());
                        break;
                    case "Black":
                        teams.get(3).remove(p.getName());
                        break;
                }

                lifeStatus.replace(p, "N/A");
                team.replace(p, "N/A");
                container.set(sharp, PersistentDataType.STRING, "N/A");
                container.set(prot, PersistentDataType.STRING, "N/A");
                container.set(haste, PersistentDataType.STRING, "N/A");
                p.setLevel(0);

                if (queuedPlayers.isEmpty()) {
                    timeLeft = 20;
                }
                return true;
            }

            if (args.length == 0 && queuedPlayers.contains(p)) {
                p.sendMessage("§cYou are already in the queue! Leave with /queue leave!");
                return true;
            }

            queueProcedures(p);
        }else {
            if (args.length < 1) {
                sender.sendMessage("Please specify a player to add to the queue");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                queueProcedures(p);
            } else {
                sender.sendMessage("Player is not online");
            }
        }
        return true;
    }

    public void queueProcedures(Player p) {
        GameEvents gam = new GameEvents(plugin);
        TeamAdder teamAdding = new TeamAdder(plugin);
        int teamNum = (int) (Math.random() * 4);

        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
        NamespacedKey prot = new NamespacedKey(plugin, "prot");
        NamespacedKey haste = new NamespacedKey(plugin, "haste");

        if (plugin.getConfig().get("Que") == null) {
            p.sendMessage("§cThe que is not set up!");
            return;
        }
        if (plugin.getConfig().get("Red") == null) {
            p.sendMessage("§cRed base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Blue") == null) {
            p.sendMessage("§cBlue base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Yellow") == null) {
            p.sendMessage("§cYellow base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Black") == null) {
            p.sendMessage("§cBlack base is not set up!");
            return;
        }
        p.sendMessage("§aYou joined the queue!");
        p.getInventory().clear();

        teamAdding.addToTeam(p, teamNum);

        if (teams.get(teamNum).size() - 1 == 0) {
            aliveTeams ++;
        }

        queuedPlayers.add(p);

        if (teamNum == 0) {
            team.replace(p, "Red");
        }else if (teamNum == 1) {
            team.replace(p, "Yellow");
        }else if (teamNum == 2) {
            team.replace(p, "Blue");
        }else if (teamNum == 3) {
            team.replace(p, "Black");
        }

        lifeStatus.replace(p, "In_Queue");
        container.set(sharp, PersistentDataType.STRING, "None");
        container.set(prot, PersistentDataType.STRING, "None");
        container.set(haste, PersistentDataType.STRING, "None");

        Location queLoc = plugin.getConfig().getLocation("Que");
        p.setGameMode(GameMode.SURVIVAL);
        p.teleport(queLoc);

        //Change == 1 to == 2 for multiplayer
        if (queuedPlayers.size() == 2) {
            timeLeft = 20;
            new BukkitRunnable() {
                @Override
                public void run() {
                    //Change == 0 to == 1 for multiplayer
                    if (queuedPlayers.size() == 1){
                        timeLeft = 20;
                        cancel();
                    }

                    if (timeLeft <= 5) {
                        for (Player queuedPlayer : queuedPlayers) {
                            queuedPlayer.sendMessage("§a" + timeLeft + " seconds remaining!");
                        }
                    }

                    for (Player queuedPlayer : queuedPlayers) {
                        queuedPlayer.setLevel(timeLeft);
                    }
                    timeLeft --;

                    if (timeLeft == 0) {
                        for (Player queuedPlayer : queuedPlayers) {
                            queuedPlayer.setLevel(timeLeft);
                            queuedPlayer.sendMessage("§aGame Started!");
                        }
                        gam.startGame();
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }
    }
}

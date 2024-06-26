package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.gameLogicEvents;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import static me.isaacfediw.guis.events.gameLogicEvents.aliveTeams;
import static me.isaacfediw.guis.events.gameLogicEvents.teams;

public class queue implements CommandExecutor {
    public static ArrayList<Player> queuedPlayers = new ArrayList<>();
    private int timeLeft;
    GUIs plugin;

    public queue(GUIs plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            PersistentDataContainer container = p.getPersistentDataContainer();
            NamespacedKey teamName = new NamespacedKey(plugin, "Team");
            NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
            NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
            NamespacedKey prot = new NamespacedKey(plugin, "prot");
            NamespacedKey haste = new NamespacedKey(plugin, "haste");

            if (queuedPlayers.contains(p) && args.length >= 1) {
                if (!container.get(lifeStatus, PersistentDataType.STRING).equals("In_Queue")) {
                    p.sendMessage(ChatColor.RED + "You cannot leave the queue while in the game!");
                    return true;
                }

                queuedPlayers.remove(p);
                p.setPlayerListName(ChatColor.WHITE + p.getName());
                p.getInventory().clear();
                p.sendMessage(ChatColor.GREEN + "You left the queue!");

                switch (container.get(teamName, PersistentDataType.STRING)) {
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
                container.set(teamName, PersistentDataType.STRING, "None");
                container.set(lifeStatus, PersistentDataType.STRING, "N/A");
                container.set(sharp, PersistentDataType.STRING, "N/A");
                container.set(prot, PersistentDataType.STRING, "N/A");
                container.set(haste, PersistentDataType.STRING, "N/A");
                p.setLevel(0);

                if (queuedPlayers.size() == 0){
                    timeLeft = 20;
                }
                return true;
            }

            if (args.length == 0 && queuedPlayers.contains(p)){
                p.sendMessage(ChatColor.RED + "You are already in the queue! Leave with /queue leave!");
                return true;
            }

            queueProcedures(p);
        }else {
            if (args.length < 1) {
                sender.sendMessage("Please specify a player to add to the queue");
                return true;
            }
            if (Bukkit.getPlayer(args[0]) != null) {
                queueProcedures(Bukkit.getPlayer(args[0]));
            } else {
                sender.sendMessage("This player is not online");
            }
        }
        return true;
    }

    public void queueProcedures(Player p) {
        gameLogicEvents gam = new gameLogicEvents(plugin);
        teamAdder teamAdding = new teamAdder(plugin);
        int team = (int) (Math.random() * 4);

        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey teamName = new NamespacedKey(plugin, "Team");
        NamespacedKey lifeStatus = new NamespacedKey(plugin, "Life_Status");
        NamespacedKey sharp = new NamespacedKey(plugin, "sharp");
        NamespacedKey prot = new NamespacedKey(plugin, "prot");
        NamespacedKey haste = new NamespacedKey(plugin, "haste");

        if (plugin.getConfig().get("Que") == null){
            p.sendMessage(ChatColor.RED + "The que is not set up!");
            return;
        }
        if (plugin.getConfig().get("Red") == null){
            p.sendMessage(ChatColor.RED + "Red base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Blue") == null){
            p.sendMessage(ChatColor.RED + "Blue base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Yellow") == null){
            p.sendMessage(ChatColor.RED + "Yellow base is not set up!");
            return;
        }
        if (plugin.getConfig().get("Black") == null){
            p.sendMessage(ChatColor.RED + "Black base is not set up!");
            return;
        }
        p.sendMessage(ChatColor.GREEN + "You joined the queue!");
        p.getInventory().clear();

        teamAdding.addToTeam(p, team);

        if (teams.get(team).size() - 1 == 0) {
            aliveTeams ++;
        }

        queuedPlayers.add(p);

        if (team == 0){
            container.set(teamName, PersistentDataType.STRING, "Red");
        }else if (team == 1){
            container.set(teamName, PersistentDataType.STRING, "Yellow");
        }else if (team == 2){
            container.set(teamName, PersistentDataType.STRING, "Blue");
        }else if (team == 3){
            container.set(teamName, PersistentDataType.STRING, "Black");
        }
        container.set(lifeStatus, PersistentDataType.STRING, "In_Queue");
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

                    if (timeLeft <= 5){
                        for (Player queuedPlayer : queuedPlayers) {
                            queuedPlayer.sendMessage(ChatColor.GREEN + "" + timeLeft + " seconds remaining!");
                        }
                    }

                    for (Player queuedPlayer : queuedPlayers) {
                        queuedPlayer.setLevel(timeLeft);
                    }
                    timeLeft --;

                    if (timeLeft == 0){
                        for (Player queuedPlayer : queuedPlayers) {
                            queuedPlayer.setLevel(timeLeft);
                            queuedPlayer.sendMessage(ChatColor.GREEN + "Game Started!");
                        }
                        gam.startGame();
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 20);
        }
    }
}

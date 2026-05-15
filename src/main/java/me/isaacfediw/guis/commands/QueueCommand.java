package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import me.isaacfediw.guis.utils.ItemMaker;
import me.isaacfediw.guis.utils.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static me.isaacfediw.guis.events.GameEvents.aliveTeams;
import static me.isaacfediw.guis.events.GameEvents.teams;

public class QueueCommand implements CommandExecutor, Listener {
    public static ArrayList<Player> queuedPlayers = new ArrayList<>();

    private int timeLeft;
    private final GUIs plugin;

    public QueueCommand(GUIs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            PlayerData playerData;

            if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
            else playerData = new PlayerData(p);

            if (queuedPlayers.contains(p) && args.length >= 1) {
                if (!playerData.getLifeStatus().equals("In_Queue")) {
                    p.sendMessage("§cYou cannot leave the queue while in the game!");
                    return true;
                }

                queuedPlayers.remove(p);
                p.playerListName(Component.text("§f" + p.getName()));
                p.getInventory().clear();
                p.sendMessage("§aYou left the queue!");

                switch (playerData.getPlayerTeam()) {
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

                playerData.setLifeStatus("N/A");
                playerData.setPlayerTeam("N/A");

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

            Inventory queueGui = Bukkit.createInventory(p, 9, Component.text("§6Select Team"));

            ItemStack red    = ItemMaker.buildItem(Material.RED_WOOL, "§cRed Team");
            ItemStack yellow = ItemMaker.buildItem(Material.YELLOW_WOOL, "§eYellow Team");
            ItemStack blue   = ItemMaker.buildItem(Material.BLUE_WOOL, "§1Blue Team");
            ItemStack black  = ItemMaker.buildItem(Material.BLACK_WOOL, "§0Black Team");
            ItemStack random = ItemMaker.buildItem(Material.CHORUS_FRUIT,"§6Random");

            queueGui.setItem(0, red);
            queueGui.setItem(2, yellow);
            queueGui.setItem(4, blue);
            queueGui.setItem(6, black);
            queueGui.setItem(8, random);

            p.openInventory(queueGui);

        } else {
            if (args.length < 1) {
                sender.sendMessage("Please specify a player to add to the queue");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                queueProcedures(p, (int) (Math.random() * 4));
            } else {
                sender.sendMessage("Player is not online");
            }
        }
        return true;
    }

    @EventHandler
    public void onQueueGuiClick(InventoryClickEvent e) {
        Component titleComp = e.getView().title();
        String title = LegacyComponentSerializer.legacySection().serialize(titleComp);

        Player p = (Player) e.getWhoClicked();

        if (title.equalsIgnoreCase("§6Select Team")) {
            if (e.getSlot() %2 == 0 && e.getSlot() != 8) {
                queueProcedures(p, e.getSlot() / 2);
            } else if (e.getSlot() == 8) {
                queueProcedures(p, (int) (Math.random() * 4));
            }
        }

        p.closeInventory();
        e.setCancelled(true);
    }

    public void queueProcedures(Player p, int teamNum) {
        GameEvents gam = new GameEvents(plugin);
        TeamAdder teamAdding = new TeamAdder(plugin);

        PlayerData playerData;

        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        Location queLoc = plugin.getConfig().getLocation("Que");
        if (queLoc == null) {
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
            aliveTeams++;
        }

        queuedPlayers.add(p);

        if (teamNum == 0) {
            playerData.setPlayerTeam("Red");
        } else if (teamNum == 1) {
            playerData.setPlayerTeam("Yellow");
        } else if (teamNum == 2) {
            playerData.setPlayerTeam("Blue");
        } else if (teamNum == 3) {
            playerData.setPlayerTeam("Black");
        }

        playerData.setLifeStatus("In_Queue");

        p.setGameMode(GameMode.SURVIVAL);
        p.teleport(queLoc);

        if (queuedPlayers.size() == 2) {
            timeLeft = 20;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (queuedPlayers.size() == 1) {
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
                    timeLeft--;

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
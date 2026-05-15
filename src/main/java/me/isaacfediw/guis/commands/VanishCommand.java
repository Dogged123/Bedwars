package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Level;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class VanishCommand implements CommandExecutor {

    private final GUIs plugin;
    private final ArrayList<Player> vanishedPlayers = new ArrayList<>();

    public VanishCommand(GUIs p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage("Please enter the name of the player you would like to vanish");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                sender.sendMessage("That player is not online! Choose another player!");
                return true;
            }
            vanishPlayer(p);
        }
        return true;
    }

    public void vanishPlayer(Player p) {
        if (!vanishedPlayers.contains(p)) {
            vanishedPlayers.add(p);
            for (Player player : queuedPlayers) {
                if (player.isOnline()) {
                    player.hidePlayer(plugin, p);
                }
            }
            plugin.getLogger().log(Level.INFO, p.getName() + " is now vanished!");
        } else {
            vanishedPlayers.remove(p);
            for (Player player : queuedPlayers) {
                if (player.isOnline()) {
                    player.showPlayer(plugin, p);
                }
            }
            plugin.getLogger().log(Level.INFO, p.getName() + " is now visible!");
        }
    }
}

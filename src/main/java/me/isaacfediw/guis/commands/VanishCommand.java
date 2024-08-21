package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class VanishCommand implements CommandExecutor {

    GUIs plugin;
    private ArrayList<Player> vanishedPlayers = new ArrayList<>();
    public VanishCommand(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            if (args.length == 0){
                System.out.println("Please enter the name of the player you would like to vanish");
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null){
                System.out.println("That player is not online! Choose another player!");
                return true;
            }
            vanishPlayer(p);
        }
        return true;
    }

    public void vanishPlayer(Player p) {
        if (!vanishedPlayers.contains(p)) {
            vanishedPlayers.add(p);
            for (Player player : queuedPlayers){
                if (player.isOnline()){
                    player.hidePlayer(plugin, p);
                }
            }
            System.out.println((p.getName() + " is now vanished!"));
        } else {
            vanishedPlayers.remove(p);
            for (Player player : queuedPlayers){
                if (player.isOnline()){
                    player.showPlayer(plugin, p);
                }
            }
            System.out.println(p.getName() + " is now visible!");
        }
    }
}

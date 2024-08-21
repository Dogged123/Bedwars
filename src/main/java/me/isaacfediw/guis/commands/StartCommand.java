package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class StartCommand implements CommandExecutor {
    GUIs plugin;
    public StartCommand(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("GUIs.startgame")){
            p.sendMessage("§cYou do not have permission to run this command");
            return true;
        }

        if (queuedPlayers.isEmpty()) {
            p.sendMessage("§cThere are no players in the queue!");
            return true;
        }

        p.sendMessage("§aGame Started");

        GameEvents gam = new GameEvents(plugin);
        gam.startGame();
        return true;
    }
}

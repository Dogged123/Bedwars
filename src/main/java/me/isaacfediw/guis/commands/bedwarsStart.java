package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.gameLogicEvents;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bedwarsStart implements CommandExecutor {
    GUIs plugin;
    public bedwarsStart(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("GUIs.startgame")){
            p.sendMessage(ChatColor.RED + "You do not have permission to run this command");
            return true;
        }
        gameLogicEvents gam = new gameLogicEvents(plugin);
        gam.startGame();
        return true;
    }
}

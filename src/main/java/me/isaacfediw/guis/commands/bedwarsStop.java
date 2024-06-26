package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.gameLogicEvents;
import me.isaacfediw.guis.events.blockBreakControl;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bedwarsStop implements CommandExecutor {

    GUIs plugin;
    public bedwarsStop(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.stopgame")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                return true;
            }
            stopGame();
        }
        return true;
    }

    public void stopGame() {
        gameLogicEvents gam = new gameLogicEvents(plugin);

        for (Block block : blockBreakControl.breakableBlocks) {
            block.setType(Material.AIR);
        }
        blockBreakControl.breakableBlocks.clear();

        GUIs.stopGame();
        gam.stop();
    }
}

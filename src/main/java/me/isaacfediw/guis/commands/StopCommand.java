package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import me.isaacfediw.guis.events.BlockEvents;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand implements CommandExecutor {

    GUIs plugin;
    public StopCommand(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.stopgame")) {
                p.sendMessage("§cYou do not have permission to run this command");
                return true;
            }
            stopGame();
        }
        return true;
    }

    public void stopGame() {
        GameEvents gam = new GameEvents(plugin);

        for (Block block : BlockEvents.breakableBlocks) {
            block.setType(Material.AIR);
        }
        BlockEvents.breakableBlocks.clear();

        GUIs.stopGame();
        gam.stop();
    }
}

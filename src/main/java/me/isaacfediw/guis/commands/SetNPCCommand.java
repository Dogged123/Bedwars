package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SetNPCCommand implements CommandExecutor {

    private final GUIs plugin;
    public SetNPCCommand(GUIs p) {plugin = p;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return true;

        if (args.length == 0) return false;
        if (!args[0].equals("item") && !args[0].equals("upgrades")) return false;

        Player p = (Player) sender;

        if (!p.isOp()) return true;

        if (p.getTargetEntity(6) == null) {
            p.sendMessage(Component.text("§cYou are not looking at an entity"));
            return true;
        }

        Entity target = p.getTargetEntity(6);
        if (target == null) return true;

        List<String> shopkeepers = plugin.getConfig().getStringList("npc.entity." + args[0] + "_shop");
        shopkeepers.add(target.getType().toString());

        plugin.getConfig().set("npc.entity." + args[0] + "_shop", shopkeepers);
        plugin.saveConfig();

        LivingEntity npc = (LivingEntity) p.getWorld().spawnEntity(target.getLocation(), target.getType());
        npc.setPersistent(true);
        npc.setRemoveWhenFarAway(false);
        npc.setSilent(true);
        npc.setInvulnerable(true);
        npc.setAI(false);
        npc.customName(Component.text("§6" + args[0].substring(0, 1).toUpperCase() + args[0].substring(1) + " Shop"));
        npc.setCustomNameVisible(true);

        float yaw = (float) Math.toDegrees(Math.atan2(p.getFacing().getDirection().getX(), -p.getFacing().getDirection().getZ()));
        Location newLoc = target.getLocation().getBlock().getLocation().clone().add(0.5, 0, 0.5);
        newLoc.setYaw(yaw);
        npc.teleport(newLoc);

        target.remove();

        p.sendMessage("§aSet " + args[0].substring(0, 1).toUpperCase() + args[0].substring(1) + " Shop NPC");

        return true;
    }
}








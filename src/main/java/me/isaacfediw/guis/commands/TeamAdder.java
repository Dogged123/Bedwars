package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import me.isaacfediw.guis.utils.PlayerData;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

public class TeamAdder implements CommandExecutor {
    String playerName;
    Player target;
    private final GUIs plugin;

    public TeamAdder(GUIs p) {
        plugin = p;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player p = (Player) sender;

        if (!p.hasPermission("GUIs.bedwarsteam")) {
            p.sendMessage("§cYou do not have permission to run this command");
            return true;
        }

        if (args.length != 2) {
            p.sendMessage("Usage: /bt <team> <playerName>");
        } else {
            playerName = args[1];
            target = Bukkit.getPlayerExact(playerName);
            if (target == null) {
                p.sendMessage("§cThat player is not online!");
                return true;
            }
            addToTeam(target, args);
        }
        return true;
    }

    public void addToTeam(Player p, String[] args) {
        PlayerData playerData;

        if (PlayerData.playersData.containsKey(p)) playerData = PlayerData.playersData.get(p);
        else playerData = new PlayerData(p);

        if (args[0].equalsIgnoreCase("Red")) {
            addToTeam(p, 0);
            playerData.setPlayerTeam("Red");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        } else if (args[0].equalsIgnoreCase("Yellow")) {
            addToTeam(p, 1);
            playerData.setPlayerTeam("Yellow");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        } else if (args[0].equalsIgnoreCase("Blue")) {
            addToTeam(p, 2);
            playerData.setPlayerTeam("Blue");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        } else if (args[0].equalsIgnoreCase("Black")) {
            addToTeam(p, 3);
            playerData.setPlayerTeam("Black");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        } else {
            p.sendMessage("§cPlease enter a valid team (Red, Yellow, Blue, or Black)");
        }
    }

    public void addToTeam(Player p, int index) {
        GameEvents gam = new GameEvents(plugin);

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta helmMeta = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta legMeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta bootMeta = (LeatherArmorMeta) boots.getItemMeta();

        if (index > 3) return;

        Color armourColour = Color.WHITE;

        if (index == 0) {
            gam.addToTeam(0, "Red", p);
            p.playerListName(Component.text("§c" + p.getName()));
            armourColour = Color.RED;
        } else if (index == 1) {
            gam.addToTeam(1, "Yellow", p);
            p.playerListName(Component.text("§e" + p.getName()));
            armourColour = Color.YELLOW;
        } else if (index == 2) {
            gam.addToTeam(2, "Blue", p);
            p.playerListName(Component.text("§1" + p.getName()));
            armourColour = Color.BLUE;
        } else if (index == 3) {
            gam.addToTeam(3, "Black", p);
            p.playerListName(Component.text("§0" + p.getName()));
            armourColour = Color.BLACK;
        }

        if (helmMeta != null) {
            helmMeta.setColor(armourColour);
            helmMeta.setUnbreakable(true);
        }

        if (chestMeta != null) {
            chestMeta.setColor(armourColour);
            chestMeta.setUnbreakable(true);
        }

        if (legMeta != null) {
            legMeta.setColor(armourColour);
            legMeta.setUnbreakable(true);
        }

        if (bootMeta != null) {
            bootMeta.setColor(armourColour);
            bootMeta.setUnbreakable(true);
        }

        helmet.setItemMeta(helmMeta);
        chestplate.setItemMeta(chestMeta);
        leggings.setItemMeta(legMeta);
        boots.setItemMeta(bootMeta);

        p.getInventory().setHelmet(helmet);
        p.getInventory().setChestplate(chestplate);
        p.getInventory().setLeggings(leggings);
        p.getInventory().setBoots(boots);
    }
}
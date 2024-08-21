package me.isaacfediw.guis.commands;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.events.GameEvents;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import static me.isaacfediw.guis.commands.QueueCommand.team;

public class TeamAdder implements CommandExecutor {

    String playerName;
    Player target;
    GUIs plugin;

    public TeamAdder(GUIs p){
        plugin = p;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

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
            if (target == null){
                p.sendMessage("§cThat player is not online!");
                return true;
            }
            addToTeam(target, args);
        }
        return true;
    }

    public void addToTeam(Player p, String[] args) {
        if (!team.containsKey(p)) team.put(p, "N/A");

        if (args[0].equalsIgnoreCase("Red")) {
            addToTeam(p, 0);
            team.replace(p, "Red");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        } else if (args[0].equalsIgnoreCase("Yellow")) {
            addToTeam(p, 1);
            team.replace(p, "Yellow");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        }else if (args[0].equalsIgnoreCase("Blue")) {
            addToTeam(p, 2);
            team.replace(p, "Blue");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        }else if (args[0].equalsIgnoreCase("Black")) {
            addToTeam(p, 3);
            team.replace(p, "Black");
            p.sendMessage("§aSuccessfully added " + playerName + " to " + args[0] + " team!");
        }else {
            p.sendMessage("§cPlease enter a valid team (Red, Yellow, Blue, or Black)");
        }
    }

    public void addToTeam(Player p, int index) {
        GameEvents gam = new GameEvents(plugin);
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        ItemMeta helmMeta = helmet.getItemMeta();
        ItemMeta chestMeta = chestplate.getItemMeta();
        ItemMeta legMeta = leggings.getItemMeta();
        ItemMeta bootMeta = boots.getItemMeta();
        LeatherArmorMeta helmMeta1 = (LeatherArmorMeta) helmMeta;
        LeatherArmorMeta chestMeta1 = (LeatherArmorMeta) chestMeta;
        LeatherArmorMeta legMeta1 = (LeatherArmorMeta) legMeta;
        LeatherArmorMeta bootMeta1 = (LeatherArmorMeta) bootMeta;

        if (index == 0) {
            gam.addToTeam(0, p);
            p.setPlayerListName("§c" + p.getName());
            helmMeta1.setColor(Color.RED);
            chestMeta1.setColor(Color.RED);
            legMeta1.setColor(Color.RED);
            bootMeta1.setColor(Color.RED);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
        } else if (index == 1) {
            gam.addToTeam(1, p);
            p.setPlayerListName("§e" + p.getName());
            helmMeta1.setColor(Color.YELLOW);
            chestMeta1.setColor(Color.YELLOW);
            legMeta1.setColor(Color.YELLOW);
            bootMeta1.setColor(Color.YELLOW);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
        } else if (index == 2) {
            gam.addToTeam(2, p);
            p.setPlayerListName("§1" + p.getName());
            helmMeta1.setColor(Color.BLUE);
            chestMeta1.setColor(Color.BLUE);
            legMeta1.setColor(Color.BLUE);
            bootMeta1.setColor(Color.BLUE);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
        } else if (index == 3) {
            gam.addToTeam(3, p);
            p.setPlayerListName("§0" + p.getName());
            helmMeta1.setColor(Color.BLACK);
            chestMeta1.setColor(Color.BLACK);
            legMeta1.setColor(Color.BLACK);
            bootMeta1.setColor(Color.BLACK);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
            p.getInventory().setHelmet(helmet);
            p.getInventory().setChestplate(chestplate);
            p.getInventory().setLeggings(leggings);
            p.getInventory().setBoots(boots);
        }
    }
}

package me.isaacfediw.guis;

import me.isaacfediw.guis.commands.*;
import me.isaacfediw.guis.events.*;
import me.isaacfediw.guis.events.shops.menuHandler;
import me.isaacfediw.guis.events.shops.onShopClick;
import me.isaacfediw.guis.events.shops.villagerCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class GUIs extends JavaPlugin {

    private static boolean stop;
    @Override
    public void onEnable() {

        getCommand("itemshop").setExecutor(new shopCommand());
        getCommand("upgrades").setExecutor(new upgradeShopCommand());
        getCommand("generator").setExecutor(this);
        getCommand("bedwarsteam").setExecutor(new teamAdder(this));
        getCommand("startgame").setExecutor(new bedwarsStart(this));
        getCommand("bwWand").setExecutor(new GetWand());
        getCommand("stopgame").setExecutor(new bedwarsStop(this));
        getCommand("queue").setExecutor(new queue(this));
        getCommand("openScoreboard").setExecutor(new openScoreboard());
        getCommand("vanish").setExecutor(new Vanish(this));
        getServer().getPluginManager().registerEvents(new onShopClick(this), this);
        getServer().getPluginManager().registerEvents(new villagerCommandExecutor(), this);
        getServer().getPluginManager().registerEvents(new armorInteractionPrevention(), this);
        getServer().getPluginManager().registerEvents(new preventExplosions(), this);
        getServer().getPluginManager().registerEvents(new gameLogicEvents(this), this);
        getServer().getPluginManager().registerEvents(new autoTnt(), this);
        getServer().getPluginManager().registerEvents(new menuHandler(this), this);
        getServer().getPluginManager().registerEvents(new throwableFireballs(), this);
        getServer().getPluginManager().registerEvents(new blockBreakControl(this), this);
        getServer().getPluginManager().registerEvents(new itemManager(this), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(this), this);
        getServer().getPluginManager().registerEvents(new UseWand(), this);
        getServer().getPluginManager().registerEvents(new AddOrDelBaseHandler(this), this);
        getServer().getPluginManager().registerEvents(new MakeGolemAgro(this), this);
        getServer().getPluginManager().registerEvents(new HasteOnMove(this), this);
        getServer().getPluginManager().registerEvents(new VoidInstaDeath(), this);
        getServer().getPluginManager().registerEvents(new PreventFriendlyFire(this), this);
        getServer().getPluginManager().registerEvents(new generatorSplitting(this), this);
        getServer().getPluginManager().registerEvents(new NoFoodDepletion(), this);
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.generator")) {
                p.sendMessage(ChatColor.RED + "You do not have permission to run this command");
                return true;
            }
            try {
                if (args.length < 5) {
                    p.sendMessage(ChatColor.RED + "Please specify a location, spawn frequency, spawn length, and resource");
                    p.sendMessage(ChatColor.RED + "Example: 0 64 0 40 150 IRON_INGOT");
                } else {
                    Location loc = new Location(p.getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                    int frequency = Integer.parseInt(args[3]);
                    int length = Integer.parseInt(args[4]);
                    String resource = args[5];

                    generator(loc, frequency, resource, length);

                    stop = false;
                }
            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }
        }
        return true;
    }

    public void generator(Location loc, int frequency, String resource, int l){
        stop = false;
        new BukkitRunnable() {
            int length = l;
            @Override
            public void run() {
                if (length == 0 || stop) {
                    cancel();
                } else {
                    Bukkit.getWorld("world").dropItem(loc, new ItemStack(Material.getMaterial(resource)));
                    length--;
                }
            }
        }.runTaskTimer(this, 0, frequency);
    }

    public static void stopGame() {
        stop = true;
    }
}

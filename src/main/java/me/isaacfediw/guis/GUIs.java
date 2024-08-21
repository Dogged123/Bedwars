package me.isaacfediw.guis;

import me.isaacfediw.guis.commands.*;
import me.isaacfediw.guis.events.*;
import me.isaacfediw.guis.events.shops.ItemShopListener;
import me.isaacfediw.guis.events.shops.ShopListeners;
import me.isaacfediw.guis.events.shops.ShopKeeperListeners;
import org.bukkit.Bukkit;
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

        getCommand("itemshop").setExecutor(new ItemShopCommand());
        getCommand("upgrades").setExecutor(new UpgradeShopCommand());
        getCommand("generator").setExecutor(this);
        getCommand("bedwarsteam").setExecutor(new TeamAdder(this));
        getCommand("startgame").setExecutor(new StartCommand(this));
        getCommand("bwWand").setExecutor(new WandCommand());
        getCommand("stopgame").setExecutor(new StopCommand(this));
        getCommand("queue").setExecutor(new QueueCommand(this));
        getCommand("openScoreboard").setExecutor(new OpenScoreboard());
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getServer().getPluginManager().registerEvents(new ShopListeners(this), this);
        getServer().getPluginManager().registerEvents(new ShopKeeperListeners(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getServer().getPluginManager().registerEvents(new GameEvents(this), this);
        getServer().getPluginManager().registerEvents(new TNTListener(), this);
        getServer().getPluginManager().registerEvents(new ItemShopListener(this), this);
        getServer().getPluginManager().registerEvents(new FireballListener(), this);
        getServer().getPluginManager().registerEvents(new BlockEvents(this), this);
        getServer().getPluginManager().registerEvents(new ItemManager(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new WandMenuListener(this), this);
        getServer().getPluginManager().registerEvents(new MakeGolemAgro(this), this);
        getServer().getPluginManager().registerEvents(new HasteOnMove(this), this);
        getServer().getPluginManager().registerEvents(new VoidListener(), this);
        getServer().getPluginManager().registerEvents(new PreventFriendlyFire(this), this);
        getServer().getPluginManager().registerEvents(new SplitGens(this), this);
        getServer().getPluginManager().registerEvents(new NoFoodDepletion(), this);
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("GUIs.generator")) {
                p.sendMessage("§cYou do not have permission to run this command");
                return true;
            }
            try {
                if (args.length < 5) {
                    p.sendMessage("§cPlease specify a location, spawn frequency, spawn length, and resource");
                    p.sendMessage("§cExample: 0 64 0 40 150 IRON_INGOT");
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

    public void generator(Location loc, int frequency, String resource, int l) {
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

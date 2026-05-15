package me.isaacfediw.guis;

import me.isaacfediw.guis.commands.*;
import me.isaacfediw.guis.events.*;
import me.isaacfediw.guis.events.shops.ItemShopListener;
import me.isaacfediw.guis.events.shops.ShopListeners;
import me.isaacfediw.guis.events.shops.ShopKeeperListeners;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class GUIs extends JavaPlugin {

    private static boolean stop;
    public static final List<ArmorStand> genMarkers = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("itemshop").setExecutor(new ItemShopCommand());
        getCommand("upgrades").setExecutor(new UpgradeShopCommand());
        getCommand("generator").setExecutor(this);
        getCommand("bedwarsteam").setExecutor(new TeamAdder(this));
        getCommand("startgame").setExecutor(new StartCommand(this));
        getCommand("bwWand").setExecutor(new WandCommand());
        getCommand("stopgame").setExecutor(new StopCommand(this));
        getCommand("setnpc").setExecutor(new SetNPCCommand(this));
        getCommand("removenpc").setExecutor(new RemoveNPCCommand(this));
        getCommand("queue").setExecutor(new QueueCommand(this));
        getCommand("openScoreboard").setExecutor(new OpenScoreboard());
        getCommand("vanish").setExecutor(new VanishCommand(this));

        getServer().getPluginManager().registerEvents(new BlockEvents(this), this);
        getServer().getPluginManager().registerEvents(new ExplosionListener(), this);
        getServer().getPluginManager().registerEvents(new FireworkDamageListener(), this);
        getServer().getPluginManager().registerEvents(new GameEvents(this), this);
        getServer().getPluginManager().registerEvents(new ItemManager(this), this);
        getServer().getPluginManager().registerEvents(new ItemShopListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new NoFoodDepletion(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(this), this);
        getServer().getPluginManager().registerEvents(new PreventFriendlyFire(), this);
        getServer().getPluginManager().registerEvents(new QueueCommand(this), this);
        getServer().getPluginManager().registerEvents(new ShopListeners(), this);
        getServer().getPluginManager().registerEvents(new ShopKeeperListeners(this), this);
        getServer().getPluginManager().registerEvents(new SplitGens(this), this);
        getServer().getPluginManager().registerEvents(new VoidListener(), this);
        getServer().getPluginManager().registerEvents(new WandListener(), this);
        getServer().getPluginManager().registerEvents(new WandMenuListener(this), this);

        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
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
                getServer().getConsoleSender().sendMessage("§cException: " + exception);
            }
        }
        return true;
    }

    public void generator(Location loc, int frequency, String resource, int length) {
        stop = false;

        final World world = loc.getWorld();
        final Material resourceMat = Material.getMaterial(resource);

        if (resourceMat == null) return;

        ArmorStand genMarker;
        if (resource.equals("EMERALD") || resource.equals("DIAMOND")) {
            genMarker = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);
            genMarker.setPersistent(true);
            genMarker.setInvulnerable(true);
            genMarker.setAI(false);
            genMarker.setGravity(false);
            genMarker.setInvisible(true);

            Material headMarker = Material.getMaterial(resource + "_BLOCK");
            if (headMarker != null) genMarker.getEquipment().setHelmet(new ItemStack(headMarker));

            genMarker.setCustomNameVisible(true);
            genMarker.setMarker(true);
            genMarker.teleport(loc.getBlock().getLocation().clone().add(0.5, 1, 0.5));
        } else genMarker = null;

        if (genMarker != null) genMarkers.add(genMarker);

        new BukkitRunnable() {
            int elapsedTicks = 0;

            @Override
            public void run() {
                if ((elapsedTicks >= length*frequency) || stop) {
                    cancel();
                    return;
                }

                if (elapsedTicks % frequency == 0)
                    world.dropItem(loc, new ItemStack(resourceMat));

                if (resource.equals("EMERALD") || resource.equals("DIAMOND")) {
                    Location nextLoc = genMarker.getLocation().clone();
                    nextLoc.setYaw(elapsedTicks);
                    genMarker.teleport(nextLoc);

                    genMarker.customName(Component.text(
                            "§6Next " + resource.charAt(0) + resource.substring(1).toLowerCase() +
                                    " Spawn : " + (frequency - elapsedTicks%frequency)/20 + "s"
                    ));
                }

                elapsedTicks++;
            }
        }.runTaskTimer(this, 0, 1L);
    }

    public static void stopGame() {
        stop = true;
    }
}

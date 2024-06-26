package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class blockBreakControl implements Listener {
    public static ArrayList<Block> breakableBlocks = new ArrayList<>();

    GUIs plugin;
    public blockBreakControl(GUIs p){
        plugin = p;
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        breakableBlocks.add(e.getBlock());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        gameLogicEvents gam = new gameLogicEvents(plugin);
        ArrayList<Material> beds = new ArrayList<>();
        beds.add(Material.RED_BED);
        beds.add(Material.YELLOW_BED);
        beds.add(Material.BLUE_BED);
        beds.add(Material.BLACK_BED);
        if (!p.getGameMode().equals(GameMode.CREATIVE)){
            if (beds.contains(e.getBlock().getType())) {
                e.setDropItems(false);
                PersistentDataContainer container = p.getPersistentDataContainer();
                NamespacedKey teamName = new NamespacedKey(plugin, "Team");
                if (e.getBlock().getType().equals(Material.RED_BED)){
                    if (container.get(teamName, PersistentDataType.STRING).equals("Red")){
                        e.setCancelled(true);
                        return;
                    }else {
                        gam.breakBed(p, "Red", e.getBlock().getLocation());
                    }
                }else if (e.getBlock().getType().equals(Material.YELLOW_BED)){
                    if (container.get(teamName, PersistentDataType.STRING).equals("Yellow")){
                        e.setCancelled(true);
                        return;
                    }else{
                        gam.breakBed(p, "Yellow", e.getBlock().getLocation());
                    }
                }else if (e.getBlock().getType().equals(Material.BLUE_BED)){
                    if (container.get(teamName, PersistentDataType.STRING).equals("Blue")){
                        e.setCancelled(true);
                        return;
                    }else{
                        gam.breakBed(p, "Blue", e.getBlock().getLocation());
                    }
                }else if (e.getBlock().getType().equals(Material.BLACK_BED)){
                    if (container.get(teamName, PersistentDataType.STRING).equals("Black")){
                        e.setCancelled(true);
                        return;
                    }else{
                        gam.breakBed(p, "Black", e.getBlock().getLocation());
                    }
                }
            }
            if (!breakableBlocks.contains(e.getBlock()) && !beds.contains(e.getBlock().getType())){
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You can only break blocks placed by a player!");
            }
        }
    }

    @EventHandler
    public void onBedClick(PlayerInteractEvent e){
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            ArrayList<Material> beds = new ArrayList<>();
            beds.add(Material.RED_BED);
            beds.add(Material.YELLOW_BED);
            beds.add(Material.BLUE_BED);
            beds.add(Material.BLACK_BED);

            if (beds.contains(e.getClickedBlock().getType())){
                Player player = e.getPlayer();
                if (player.getPose().equals(Pose.SNEAKING)) {
                    ArrayList<Material> blocks = new ArrayList<>();
                    blocks.add(Material.WHITE_WOOL);
                    blocks.add(Material.OAK_PLANKS);
                    blocks.add(Material.TINTED_GLASS);
                    blocks.add(Material.TERRACOTTA);
                    blocks.add(Material.END_STONE);
                    blocks.add(Material.OBSIDIAN);

                    if (!blocks.contains(player.getInventory().getItemInMainHand().getType())) {
                        e.setCancelled(true);
                    }
                    return;
                }
                e.setCancelled(true);
            }
        }
    }
}

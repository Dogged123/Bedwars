package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.utils.PlayerData;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

import static me.isaacfediw.guis.commands.QueueCommand.queuedPlayers;

public class BlockEvents implements Listener {
    public static ArrayList<Block> breakableBlocks = new ArrayList<>();

    private final GUIs plugin;
    public BlockEvents(GUIs p){
        plugin = p;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE) || !queuedPlayers.contains(p)) return;
        breakableBlocks.add(e.getBlock());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        GameEvents gam = new GameEvents(plugin);

        PlayerData pData;

        if (PlayerData.playersData.containsKey(p)) pData = PlayerData.playersData.get(p);
        else pData = new PlayerData(p);

        String team = pData.getPlayerTeam();

        if (!p.getGameMode().equals(GameMode.CREATIVE)) {
            if (e.getBlock().getType().toString().contains("BED")) {
                e.setDropItems(false);

                if (e.getBlock().getType().equals(Material.RED_BED)) {
                    if (team.equals("Red")) {
                        e.setCancelled(true);
                        return;
                    } else {
                        gam.breakBed(p, "Red", e.getBlock().getLocation());
                    }
                } else if (e.getBlock().getType().equals(Material.YELLOW_BED)) {
                    if (team.equals("Yellow")) {
                        e.setCancelled(true);
                        return;
                    } else {
                        gam.breakBed(p, "Yellow", e.getBlock().getLocation());
                    }
                } else if (e.getBlock().getType().equals(Material.BLUE_BED)) {
                    if (team.equals("Blue")) {
                        e.setCancelled(true);
                        return;
                    } else {
                        gam.breakBed(p, "Blue", e.getBlock().getLocation());
                    }
                } else if (e.getBlock().getType().equals(Material.BLACK_BED)) {
                    if (team.equals("Black")) {
                        e.setCancelled(true);
                        return;
                    } else {
                        gam.breakBed(p, "Black", e.getBlock().getLocation());
                    }
                }
            }

            if (!breakableBlocks.contains(e.getBlock()) && !e.getBlock().getType().toString().contains("BED") && queuedPlayers.contains(p)){
                e.setCancelled(true);
                p.sendMessage("§cYou can only break blocks placed by a player!");
            }
        }
    }

    @EventHandler
    public void onBedClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (!queuedPlayers.contains(p)) return;

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock() == null) return;
            if (e.getClickedBlock().getType().toString().contains("BED") && !e.getClickedBlock().getType().equals(Material.BEDROCK)) {
                if (p.getPose().equals(Pose.SNEAKING)) return;
                e.setCancelled(true);
            }
        }
    }
}

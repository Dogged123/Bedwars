package me.isaacfediw.guis.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {

    @EventHandler
    public void onExplode(BlockExplodeEvent event) {
        event.blockList().removeIf(block -> block.getType().name().contains("BED"));
        event.blockList().removeIf(block -> block.getType().name().contains("ANDESITE"));
        event.blockList().removeIf(block -> block.getType().name().contains("BRICK"));
        event.blockList().removeIf(block -> block.getType().name().contains("COBBLE"));
        event.blockList().removeIf(block -> block.getType().name().contains("GRASS"));
        event.blockList().removeIf(block -> block.getType().name().contains("EMERALD"));
        event.blockList().removeIf(block -> block.getType().name().contains("DIAMOND"));
        event.blockList().removeIf(block -> block.getType().name().contains("IRON"));
        event.blockList().removeIf(block -> block.getType().name().contains("GLASS"));
        event.blockList().removeIf(block -> block.getType().name().contains("DIRT"));
        event.blockList().removeIf(block -> block.getType().name().contains("OAK_FENCE"));
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(block -> block.getType().name().contains("BED"));
        event.blockList().removeIf(block -> block.getType().name().contains("ANDESITE"));
        event.blockList().removeIf(block -> block.getType().name().contains("BRICK"));
        event.blockList().removeIf(block -> block.getType().name().contains("COBBLE"));
        event.blockList().removeIf(block -> block.getType().name().contains("GRASS"));
        event.blockList().removeIf(block -> block.getType().name().contains("EMERALD"));
        event.blockList().removeIf(block -> block.getType().name().contains("DIAMOND"));
        event.blockList().removeIf(block -> block.getType().name().contains("IRON"));
        event.blockList().removeIf(block -> block.getType().name().contains("GLASS"));
        event.blockList().removeIf(block -> block.getType().name().contains("DIRT"));
        event.blockList().removeIf(block -> block.getType().name().contains("OAK_FENCE"));
    }
}

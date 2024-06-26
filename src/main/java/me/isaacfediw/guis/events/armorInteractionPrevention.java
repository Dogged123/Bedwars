package me.isaacfediw.guis.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import static me.isaacfediw.guis.commands.queue.queuedPlayers;

public class armorInteractionPrevention implements Listener {
    @EventHandler
    public void onArmorSlot(InventoryClickEvent event) {
        if (event.getSlotType().equals(InventoryType.SlotType.ARMOR)) {
            if (queuedPlayers.contains((Player) event.getWhoClicked())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCraftingSlot(InventoryClickEvent e){
        if (e.getSlotType().equals(InventoryType.SlotType.CRAFTING)){
            e.setCancelled(true);
        }
    }
}

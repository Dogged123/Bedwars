package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HasteOnMove implements Listener {

    GUIs plugin;
    public HasteOnMove(GUIs p){
        plugin = p;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        Player p = e.getPlayer();

        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey haste = new NamespacedKey(plugin, "haste");

        if (!container.has(haste, PersistentDataType.STRING)){
            container.set(haste, PersistentDataType.STRING, "N/A");
        }

        if (container.get(haste, PersistentDataType.STRING).equals("one")){
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 0, false, false));
        }else if (container.get(haste, PersistentDataType.STRING).equals("two")){
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000000, 1, false, false));
        }else{
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
        }
    }
}

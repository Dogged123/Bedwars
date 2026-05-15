package me.isaacfediw.guis.utils;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class Constants {
    public static final String[] ENCHANTS = new String[]{"sharp", "prot", "haste"};
    public static final String[] PERM_ITEMS = new String[]{"axe", "pick", "shears"};
    public static final String[] KILL_EFFECTS = new String[]{"lighting", "blood", "explosion", "firework"};

    public static void playRandomKillEffect(Player killer, Player victim) {
        World world = killer.getWorld();
        Location victimLoc = victim.getLocation().clone();

        int index = (int) (Math.random() * KILL_EFFECTS.length);

        switch (KILL_EFFECTS[index]) {
            case "lighting":
                for (int i = 0; i < 5; i++) world.strikeLightningEffect(victimLoc);
                break;
            case "blood":
                BlockData redstoneBlockData = new ItemStack(Material.REDSTONE_BLOCK).getType().createBlockData();

                world.spawnParticle(
                        Particle.BLOCK_DUST,
                        victimLoc,
                        150,
                        0.2, 0.4, 0.2,
                        0,
                        redstoneBlockData
                );

                world.playSound(killer, Sound.BLOCK_STONE_BREAK, 10.0f, 0.8f);
                break;
            case "explosion":
                world.playSound(killer, Sound.ENTITY_GENERIC_EXPLODE, 10.0f, 1.0f);
                world.spawnParticle(Particle.EXPLOSION_LARGE, victimLoc, 1);
                break;
            case "firework":
                Firework firework = world.spawn(victimLoc, Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();

                FireworkEffect effect = FireworkEffect.builder()
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .withColor(Color.RED, Color.ORANGE)
                        .withFade(Color.YELLOW)
                        .trail(true)
                        .flicker(true)
                        .build();

                meta.addEffect(effect);
                meta.setPower(1);

                firework.setFireworkMeta(meta);

                break;
        }
    }
}
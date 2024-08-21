package me.isaacfediw.guis.events;

import me.isaacfediw.guis.GUIs;
import me.isaacfediw.guis.commands.OpenScoreboard;
import me.isaacfediw.guis.commands.StopCommand;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

import static me.isaacfediw.guis.commands.QueueCommand.*;
import static me.isaacfediw.guis.events.GameEvents.aliveTeams;
import static me.isaacfediw.guis.events.GameEvents.teams;

public class PlayerDeathEvent implements Listener {

    private final ArrayList<ItemStack> playerInventory = new ArrayList<>();
    private boolean hasPick;
    private boolean hasAxe;
    GUIs plugin;

    public PlayerDeathEvent(GUIs p){
        plugin = p;
    }

    @EventHandler
    public void playerDeath(org.bukkit.event.entity.PlayerDeathEvent e) {
        Player p = e.getEntity();
        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey prot = new NamespacedKey(plugin, "prot");

        if (!queuedPlayers.contains(p)){
            return;
        }

        if (p.getKiller() != null) {
            for (ItemStack item : p.getInventory()) {
                if (item == null) continue;
                if (item.getType().toString().contains("INGOT")) {
                    p.getKiller().getInventory().addItem(item);
                }
                if (item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.EMERALD)) {
                    p.getKiller().getInventory().addItem(item);
                }
            }
        }

        if (lifeStatus.get(p).equals("Bed_Broken") || lifeStatus.get(p).equals("Dead")) {
            lifeStatus.replace(p, "Dead");

            for (List<String> team : teams) {
                team.remove(p.getName());
                if (team.isEmpty()) aliveTeams--;
            }

            if (aliveTeams == 1) {
                StopCommand stop = new StopCommand(plugin);
                stop.stopGame();
            }
            p.sendMessage("§cEliminated!");
            p.setGameMode(GameMode.SPECTATOR);

            OpenScoreboard sb = new OpenScoreboard();
            if (team.get(p).equals("Red")) {
                OpenScoreboard.redStatus = ("✖");
            }else if (team.get(p).equals("Yellow")) {
                OpenScoreboard.yellowStatus = ("✖");
            }else if (team.get(p).equals("Blue")) {
                OpenScoreboard.blueStatus = ("✖");
            }else if (team.get(p).equals("Black")) {
                OpenScoreboard.blackStatus = ("✖");
            }

            for (Player player : queuedPlayers) {
                sb.setInitialScoreboard(player);
            }

            if (p.getLocation().getY() <= 0 && p.getKiller() != null) {
                e.setDeathMessage("§c" + p.getName() + " was hit into the void by " + p.getKiller().getName() + ". §c§lFINAL KILL!");
            } else if (p.getLocation().getY() <= 0) {
                e.setDeathMessage("§c" + p.getName() + " fell into the void. §c§lFINAL KILL!");
            } else if (p.getKiller() != null) {
                e.setDeathMessage("§c" + p.getName() + " was lethally slapped by " + p.getKiller().getName() + ". §c§lFINAL KILL!");
            } else {
                e.setDeathMessage("§c" + p.getName() + " was killed by Covid 19. §c§lFINAL KILL!");
            }
            return;
        }

        hasAxe = false;
        hasPick = false;
        playerInventory.clear();

        if (p.getLocation().getY() <= 0 && p.getKiller() != null) {
            e.setDeathMessage("§c" + p.getName() + " was hit into the void by " + p.getKiller().getName() + ". ");
        } else if (p.getLocation().getY() <= 0) {
            e.setDeathMessage("§c" + p.getName() + " fell into the void.");
        } else if (p.getKiller() != null) {
            e.setDeathMessage("§c" + p.getName() + " was lethally slapped by " + p.getKiller().getName());
        } else {
            e.setDeathMessage("§c" + p.getName() + " was killed by Covid 19");
        }

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

        ItemStack ironLegs = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironLegsMeta = ironLegs.getItemMeta();
        ironLegsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            ironLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        ironLegs.setItemMeta(ironLegsMeta);

        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironBootsMeta = ironBoots.getItemMeta();
        ironBootsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            ironBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        ironBoots.setItemMeta(ironBootsMeta);

        ItemStack diamondLegs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta diamondLegsMeta = diamondLegs.getItemMeta();
        diamondLegsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            diamondLegsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        diamondLegs.setItemMeta(diamondLegsMeta);

        ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();
        diamondBootsMeta.setUnbreakable(true);
        if (container.get(prot, PersistentDataType.STRING).equals("one")) {
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")) {
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")) {
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")) {
            diamondBootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
        }
        diamondBoots.setItemMeta(diamondBootsMeta);

        if (team.get(p).equals("Red")){
            helmMeta1.setColor(Color.RED);
            chestMeta1.setColor(Color.RED);
            legMeta1.setColor(Color.RED);
            bootMeta1.setColor(Color.RED);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
        } else if (team.get(p).equals("Yellow")) {
            helmMeta1.setColor(Color.YELLOW);
            chestMeta1.setColor(Color.YELLOW);
            legMeta1.setColor(Color.YELLOW);
            bootMeta1.setColor(Color.YELLOW);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
        } else if (team.get(p).equals("Blue")) {
            helmMeta1.setColor(Color.BLUE);
            chestMeta1.setColor(Color.BLUE);
            legMeta1.setColor(Color.BLUE);
            bootMeta1.setColor(Color.BLUE);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
        } else if (team.get(p).equals("Black")) {
            helmMeta1.setColor(Color.BLACK);
            chestMeta1.setColor(Color.BLACK);
            legMeta1.setColor(Color.BLACK);
            bootMeta1.setColor(Color.BLACK);
            helmet.setItemMeta(helmMeta1);
            chestplate.setItemMeta(chestMeta1);
            leggings.setItemMeta(legMeta1);
            boots.setItemMeta(bootMeta1);
        }

        if (container.get(prot, PersistentDataType.STRING).equals("one")){
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            helmet.setItemMeta(helmMeta);
            chestplate.setItemMeta(chestMeta);
            leggings.setItemMeta(legMeta);
            boots.setItemMeta(bootMeta);
        }else if (container.get(prot, PersistentDataType.STRING).equals("two")){
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
            helmet.setItemMeta(helmMeta);
            chestplate.setItemMeta(chestMeta);
            leggings.setItemMeta(legMeta);
            boots.setItemMeta(bootMeta);
        }else if (container.get(prot, PersistentDataType.STRING).equals("three")){
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            helmet.setItemMeta(helmMeta);
            chestplate.setItemMeta(chestMeta);
            leggings.setItemMeta(legMeta);
            boots.setItemMeta(bootMeta);
        }else if (container.get(prot, PersistentDataType.STRING).equals("four")){
            helmMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            chestMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            legMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            bootMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
            helmet.setItemMeta(helmMeta);
            chestplate.setItemMeta(chestMeta);
            leggings.setItemMeta(legMeta);
            boots.setItemMeta(bootMeta);
        }

        if (p.getInventory().getLeggings().equals(ironLegs)){
            playerInventory.add(helmet);
            playerInventory.add(chestplate);
            playerInventory.add(ironLegs);
            playerInventory.add(ironBoots);
        }else if (p.getInventory().getLeggings().equals(diamondLegs)){
            playerInventory.add(helmet);
            playerInventory.add(chestplate);
            playerInventory.add(diamondLegs);
            playerInventory.add(diamondBoots);
        }else{
            playerInventory.add(helmet);
            playerInventory.add(chestplate);
            playerInventory.add(leggings);
            playerInventory.add(boots);
        }

        if (p.getInventory().contains(Material.WOODEN_PICKAXE) || p.getInventory().contains(Material.IRON_PICKAXE)){
            playerInventory.add(new ItemStack(Material.WOODEN_PICKAXE));
            hasPick = true;
        }else if (p.getInventory().contains(Material.DIAMOND_PICKAXE)){
            ItemStack iPick = new ItemStack(Material.IRON_PICKAXE);
            ItemMeta iPickMeta = iPick.getItemMeta();
            iPickMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
            iPick.setItemMeta(iPickMeta);
            playerInventory.add(iPick);
            hasPick = true;
        }
        if (p.getInventory().contains(Material.WOODEN_AXE) || p.getInventory().contains(Material.IRON_AXE)){
            playerInventory.add(new ItemStack(Material.WOODEN_AXE));
            hasAxe = true;
        }else if (p.getInventory().contains(Material.DIAMOND_AXE)){
            ItemStack iAxe = new ItemStack(Material.IRON_AXE);
            ItemMeta iAxeMeta = iAxe.getItemMeta();
            iAxeMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
            iAxe.setItemMeta(iAxeMeta);
            playerInventory.add(iAxe);
            hasAxe = true;
        }
        if (p.getInventory().contains(Material.SHEARS)){
            playerInventory.add(new ItemStack(Material.SHEARS));
        }
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        PersistentDataContainer container = p.getPersistentDataContainer();

        if (!queuedPlayers.contains(p)) {
            return;
        }

        if (lifeStatus.get(p).equals("Bed_Broken") || lifeStatus.get(p).equals("Dead")) {
            return;
        }

        p.getInventory().clear();
        Location queLoc = plugin.getConfig().getLocation("Que");
        p.teleport(queLoc);
        p.setGameMode(GameMode.SPECTATOR);

        new BukkitRunnable() {
            Integer deathTimeLeft = 5;
            @Override
            public void run() {
                if (deathTimeLeft == 0) {
                    NamespacedKey sharp = new NamespacedKey(plugin, "sharp");

                    Location redLoc = plugin.getConfig().getLocation("Red");
                    Location yellowLoc = plugin.getConfig().getLocation("Yellow");
                    Location blueLoc = plugin.getConfig().getLocation("Blue");
                    Location blackLoc = plugin.getConfig().getLocation("Black");

                    if (team.get(p).equals("Red")) {
                        p.teleport(redLoc);
                    }else if (team.get(p).equals("Yellow")){
                        p.teleport(yellowLoc);
                    }else if (team.get(p).equals("Blue")){
                        p.teleport(blueLoc);
                    }else if (team.get(p).equals("Black")){
                        p.teleport(blackLoc);
                    }
                    p.setGameMode(GameMode.SURVIVAL);

                    //Restore wooden sword
                    ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.setUnbreakable(true);
                    if (container.get(sharp, PersistentDataType.STRING).equals("true")){
                        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    }
                    sword.setItemMeta(swordMeta);
                    p.getInventory().addItem(sword);

                    //Restore armour
                    p.getInventory().setHelmet(playerInventory.get(0));
                    p.getInventory().setChestplate(playerInventory.get(1));
                    p.getInventory().setLeggings(playerInventory.get(2));
                    p.getInventory().setBoots(playerInventory.get(3));

                    //Restore tools
                    if (hasPick){
                        if (playerInventory.size() > 4) p.getInventory().addItem(playerInventory.get(4));
                    }
                    if (hasPick && hasAxe){
                        if (playerInventory.size() > 5) p.getInventory().addItem(playerInventory.get(5));
                    }else if (!hasPick && hasAxe){
                        if (playerInventory.size() > 4) p.getInventory().addItem(playerInventory.get(4));
                    }
                    if (playerInventory.contains(new ItemStack(Material.SHEARS))){
                        p.getInventory().addItem(new ItemStack(Material.SHEARS));
                    }
                    p.sendTitle("§aRespawned!", "", 5, 20, 5);
                    cancel();
                    return;
                }
                p.sendTitle("§cYou died!", "§c" + deathTimeLeft.toString(), 0, 20, 0);
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_HAT,10, 1);
                deathTimeLeft --;
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}

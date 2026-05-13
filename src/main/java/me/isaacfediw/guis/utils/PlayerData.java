package me.isaacfediw.guis.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.*;

public class PlayerData {
    public static final Map<Player, PlayerData> playersData = new HashMap<>();

    private String lifeStatus;
    private String team;

    private Map<String, Integer> enchants = new HashMap<>();
    private Map<String, Boolean> permItems = new HashMap<>();
    private ItemStack[] armour;

    public PlayerData(Player p) {
        lifeStatus = "N/A";
        team = "N/A";

        for (String enchant : Constants.ENCHANTS) {
            enchants.put(enchant, 0);
        }

        for (String permItem : Constants.PERM_ITEMS) {
            permItems.put(permItem, false);
        }

        if (playersData.containsKey(p)) playersData.replace(p, this);
        else playersData.put(p, this);
    }

    public void setLifeStatus(String newLifeStatus) {lifeStatus = newLifeStatus;}
    public String getLifeStatus() {return lifeStatus;}

    public void setPlayerTeam(String newTeam) {
        team = newTeam;

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta helmMeta = (LeatherArmorMeta) helmet.getItemMeta();
        LeatherArmorMeta chestMeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta legMeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta bootMeta = (LeatherArmorMeta) boots.getItemMeta();

        Color armourColour = Color.WHITE;

        switch (team) {
            case "Red":
                armourColour = Color.RED;
                break;
            case "Yellow":
                armourColour = Color.YELLOW;
                break;
            case "Blue":
                armourColour = Color.BLUE;
                break;
            case "Black":
                armourColour = Color.BLACK;
                break;
        }

        if (helmMeta != null) {
            helmMeta.setColor(armourColour);
            helmMeta.setUnbreakable(true);
        }

        if (chestMeta != null) {
            chestMeta.setColor(armourColour);
            chestMeta.setUnbreakable(true);
        }

        if (legMeta != null) {
            legMeta.setColor(armourColour);
            legMeta.setUnbreakable(true);
        }

        if (bootMeta != null) {
            bootMeta.setColor(armourColour);
            bootMeta.setUnbreakable(true);
        }

        helmet.setItemMeta(helmMeta);
        chestplate.setItemMeta(chestMeta);
        leggings.setItemMeta(legMeta);
        boots.setItemMeta(bootMeta);

        armour = new ItemStack[]{helmet, chestplate, leggings, boots};
    }

    public String getPlayerTeam() {return team;}

    public void setEnchants(Map<String, Integer> newEnchants) {enchants = newEnchants;}
    public Map<String, Integer> getEnchants() {return enchants;}

    public void setPermItems(Map<String, Boolean> newPermItems) {permItems = newPermItems;}
    public Map<String, Boolean> getPermItems() {return permItems;}

    public void setArmour(ItemStack[] newArmour) {armour = newArmour;}
    public void setArmourItem(int index, ItemStack armourPiece) {
        if (armour.length < 4) {
            for (int i = 0; i < 4; i++) {
                armour[i] = null;
            }
        }

        armour[index] = armourPiece;
    }
    public ItemStack[] getArmour() {return armour;}
}

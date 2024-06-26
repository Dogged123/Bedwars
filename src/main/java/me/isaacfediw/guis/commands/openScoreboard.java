package me.isaacfediw.guis.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class openScoreboard implements CommandExecutor {

    public static Score red;
    public static Score yellow;
    public static Score blue;
    public static Score black;
    private Score pHealth;

    public static String redStatus = "✔";
    public static String yellowStatus = "✔";
    public static String blueStatus = "✔";
    public static String blackStatus = "✔";
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard scoreboard = manager.getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("bedwars", "dummy", ChatColor.YELLOW + "BEDWARS");
    Objective health = scoreboard.registerNewObjective("showHealth", "dummy", ChatColor.RED + "♥");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            setInitialScoreboard(p);
        }
        return true;
    }

    public void setInitialScoreboard(Player p){
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        pHealth = health.getScore(ChatColor.YELLOW + "20");
        pHealth.setScore(20);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        p.setScoreboard(scoreboard);
    }

    public void setRedScoreboard(){
        redStatus = "(1)";
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers()){
            pHealth = health.getScore(ChatColor.YELLOW + String.valueOf(p.getHealth()));
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }

    public void setYellowScoreboard(){
        yellowStatus = "(1)";
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers()){
            pHealth = health.getScore(ChatColor.YELLOW + String.valueOf(p.getHealth()));
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }

    public void setBlueScoreboard(){
        blueStatus = "(1)";
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers()){
            pHealth = health.getScore(ChatColor.YELLOW + String.valueOf(p.getHealth()));
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }

    public void setBlackScoreboard() {
        blackStatus = "(1)";
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers()) {
            pHealth = health.getScore(ChatColor.YELLOW + String.valueOf(p.getHealth()));
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }

    public void setHealthScoreboard() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore(ChatColor.RED + "Red " + redStatus);
        red.setScore(5);

        yellow = objective.getScore(ChatColor.YELLOW + "Yellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore(ChatColor.BLUE + "Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore(ChatColor.BLACK + "Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("_______");
        line.setScore(1);

        for (Player p : Bukkit.getOnlinePlayers()) {
            pHealth = health.getScore(ChatColor.YELLOW + String.valueOf(p.getHealth()));
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }
}

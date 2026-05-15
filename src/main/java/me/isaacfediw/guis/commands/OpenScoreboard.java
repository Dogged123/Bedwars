package me.isaacfediw.guis.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static me.isaacfediw.guis.events.GameEvents.teams;

public class OpenScoreboard implements CommandExecutor {

    public static Score red;
    public static Score yellow;
    public static Score blue;
    public static Score black;

    public static String redStatus = "✔";
    public static String yellowStatus = "✔";
    public static String blueStatus = "✔";
    public static String blackStatus = "✔";

    private static final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static Scoreboard scoreboard;
    private static Objective health;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            setInitialScoreboard(Collections.singletonList(p));
        }
        return true;
    }

    public void setInitialScoreboard(List<Player> players) {
        scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("bedwars", Criteria.DUMMY, Component.text("§eBEDWARS"));
        health = scoreboard.registerNewObjective("showHealth", Criteria.DUMMY, Component.text("§c♥"));

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        health.setDisplaySlot(DisplaySlot.PLAYER_LIST);

        Score pHealth = health.getScore("§e20");
        pHealth.setScore(20);

        Score empty = objective.getScore("");
        empty.setScore(6);

        red = objective.getScore("§cRed " + redStatus);
        red.setScore(5);

        yellow = objective.getScore("§eYellow " + yellowStatus);
        yellow.setScore(4);

        blue = objective.getScore("§1Blue " + blueStatus);
        blue.setScore(3);

        black = objective.getScore("§0Black " + blackStatus);
        black.setScore(2);

        Score line = objective.getScore("§7_______");
        line.setScore(1);

        updateHealthScoreboard();
        for (Player p : players) p.setScoreboard(scoreboard);
    }

    public void removeScoreboard(List<Player> players) {
        for (Player p : players) {
            p.setScoreboard(manager.getNewScoreboard());
        }
    }

    public void setRedScoreboard() {
        redStatus = "(" + teams.get(0).size() + ")";
    }

    public void setYellowScoreboard() {
        yellowStatus = "(" + teams.get(1).size() + ")";
    }

    public void setBlueScoreboard() {
        blueStatus = "(" + teams.get(2).size() + ")";
    }

    public void setBlackScoreboard() {
        blackStatus = "(" + teams.get(3).size() + ")";
    }

    public void updateHealthScoreboard() {
        if (scoreboard == null || health == null) return;

        Score pHealth;
        for (Player p : Bukkit.getOnlinePlayers()) {
            pHealth = health.getScore("§e" + p.getHealth());
            pHealth.setScore((int) p.getHealth());
            p.setScoreboard(scoreboard);
        }
    }
}

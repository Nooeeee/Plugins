package me.nooeee.FirstPlugin.listeners;

import me.nooeee.FirstPlugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinLeaveListener implements Listener {

    private final Plugin plugin;

    public JoinLeaveListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        // Envoyer à tous les joueurs connectés
        e.setJoinMessage("[" + ChatColor.GREEN + "+" + ChatColor.RESET + "] " + ChatColor.BOLD + player.getDisplayName());

        new BukkitRunnable() {
            @Override
            public void run() {
                if(player.hasPlayedBefore()) {
                    // Envoyer seulement au joueur concerné
                    player.sendMessage(ChatColor.GREEN + "Bon retour parmis nous " + ChatColor.RESET + ChatColor.BOLD + "" + ChatColor.GOLD + player.getDisplayName() + ChatColor.RESET + "" + ChatColor.GREEN + ", tu nous avais manqué.");
                } else {
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Bienvenue sur le serveur " + ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + "" + ChatColor.BLUE + "" + ChatColor.BOLD + ", en espérant que tu t'y plaises !" );
                }
            }
        }.runTaskLater(plugin, 10L); // 10 Ticks = 0.5sec
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        Player player = e.getPlayer();
        // Envoyer à tous les joueurs connectés
        e.setQuitMessage("[" + ChatColor.RED + "-" + ChatColor.RESET + "] " + ChatColor.BOLD + player.getDisplayName());
    }
}

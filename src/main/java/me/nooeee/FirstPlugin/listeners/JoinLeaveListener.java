package me.nooeee.FirstPlugin.listeners;

import me.nooeee.FirstPlugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinLeaveListener implements Listener {

    private final Main plugin;

    public JoinLeaveListener(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        // Message global
        Player player = e.getPlayer();
        e.setJoinMessage("[" + ChatColor.GREEN + "+" + ChatColor.RESET + "] " + ChatColor.BOLD + player.getDisplayName());

        // 0.5 sec plus tard =>
        new BukkitRunnable() {
            @Override
            public void run() {
                String joinMessage = plugin.getConfig().getString("join-message");
                if(joinMessage != null) {
                    joinMessage = joinMessage.replace("%player%", e.getPlayer().getDisplayName());
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', joinMessage));
                }
            }
        }.runTaskLater(plugin, 10L);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

        Player player = e.getPlayer();
        e.setQuitMessage("[" + ChatColor.RED + "-" + ChatColor.RESET + "] " + ChatColor.BOLD + player.getDisplayName());
    }
}

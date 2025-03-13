package me.nooeee.FirstPlugin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String welcomeMessage = ChatColor.GOLD + "Bienvenue" + ChatColor.RESET + " sur le serveur " + event.getPlayer().getName() + " !";
        event.setJoinMessage(welcomeMessage);
    }
}

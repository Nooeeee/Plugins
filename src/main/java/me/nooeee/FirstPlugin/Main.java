package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.commands.CommandBroadcast;
import me.nooeee.FirstPlugin.commands.CommandFeed;
import me.nooeee.FirstPlugin.commands.CommandGodMode;
import me.nooeee.FirstPlugin.commands.CommandSpawn;
import me.nooeee.FirstPlugin.listeners.JoinLeaveListener;
import me.nooeee.FirstPlugin.utils.commands.CommandGamemode;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Plugin loading... !");

        // Implements EventsListener
        registerListener();
        System.out.println("Events enregistrés !");

        // Implements Commands
        registerCommand();
        System.out.println("Commandes enregistrées !");
    }

    private void createListener(Listener listener) {
        CraftServer server = (CraftServer) getServer();
        server.getPluginManager().registerEvents(listener, this);
    }

    // Listener
    private void registerListener() {
        createListener(new JoinLeaveListener(this));
    }

    // Commands
    private void registerCommand() {

        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CommandSpawn());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new CommandFeed());

        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new CommandBroadcast());
        Objects.requireNonNull(getCommand("god")).setExecutor(new CommandGodMode());

        // GameMode
        Objects.requireNonNull(getCommand("gma")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gms")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gmc")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gmsp")).setExecutor(new CommandGamemode());

    }
}

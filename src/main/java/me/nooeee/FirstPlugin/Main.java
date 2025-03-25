package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.commands.*;
import me.nooeee.FirstPlugin.listeners.JoinLeaveListener;
import me.nooeee.FirstPlugin.listeners.MenuListener;
import me.nooeee.FirstPlugin.utils.commands.CommandGamemode;
import me.nooeee.FirstPlugin.utils.commands.CooldownCommandManager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    private CooldownCommandManager cooldownCommandManager;

    @Override
    public void onEnable() {
        System.out.println("Plugin loading... !");

        // config.yml
        saveDefaultConfig(); // vérif si config.yml existe déjà et copie fichier par défaut
        getConfig().options().copyDefaults(); // copie le contenu du fichier par défaut dans config.yml

        // Implements CooldownManager
        cooldownCommandManager = new CooldownCommandManager(getConfig());
        System.out.println("Cooldowns enregistrés !");

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
        createListener(new MenuListener(this));
    }

    // Commands
    private void registerCommand() {
        // Commandes système
        Objects.requireNonNull(getCommand("setmessage")).setExecutor(new CommandSetMessage(this));

        // Commandes essentielles
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new CommandSpawn());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new CommandFeed(cooldownCommandManager));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new CommandHeal(cooldownCommandManager));

        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new CommandBroadcast());
        Objects.requireNonNull(getCommand("god")).setExecutor(new CommandGodMode());

        // Commandes GameModes
        Objects.requireNonNull(getCommand("gma")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gms")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gmc")).setExecutor(new CommandGamemode());
        Objects.requireNonNull(getCommand("gmsp")).setExecutor(new CommandGamemode());

        // Commandes facultatives
        Objects.requireNonNull(getCommand("menu")).setExecutor(new CommandMenu());
        Objects.requireNonNull(getCommand("flower")).setExecutor(new CommandFlower(cooldownCommandManager));

    }
}

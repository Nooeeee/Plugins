package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.commands.CommandBroadcast;
import me.nooeee.FirstPlugin.commands.CommandFeed;
import me.nooeee.FirstPlugin.commands.CommandGodMode;
import me.nooeee.FirstPlugin.commands.CommandSpawn;
import me.nooeee.FirstPlugin.listeners.JoinLeaveListener;
import me.nooeee.FirstPlugin.utils.commands.CommandGamemode;
import me.nooeee.FirstPlugin.utils.commands.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
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
        getCommand("god").setExecutor(new CommandGodMode());
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

    private void createCommand(SimpleCommand command, String permission) {
        CraftServer server = (CraftServer) getServer(); // Récupérer le serveur où est effectué la commande
        PluginCommand pluginCommand = (PluginCommand) server.getCommandMap().getCommand(command.getName());

        if(pluginCommand != null) {
            if(permission != null && !permission.isEmpty()) {
                pluginCommand.setPermission(permission);
                pluginCommand.setPermissionMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande");
            } else {
                // Si aucune permission n'est définie, la commande est inaccessible par défaut
                pluginCommand.setPermission("myplugins.command.default.denied"); // Permission inexistante
                pluginCommand.setPermissionMessage(ChatColor.RED + "Cette commande est réservée aux administrateurs.");
            }
        }

        server.getCommandMap().register(getName(), command);
        // getName() : nom du plugin, permet de déterminer quelle commande appartient à quel plugin
    }

    // Commands
    private void registerCommand() {
        createCommand(new SimpleCommand(
                "broadcast",
                "Envoyer un message pour tous les joueurs du serveur.",
                new CommandBroadcast(),
                "bc"),
                "myplugins.command.broadcast"
        );

        createCommand(new SimpleCommand(
                "gma",
                "Change le mode de jeu en Adventure.",
                new CommandGamemode(),
                ""),
                ""
        );
        createCommand(new SimpleCommand(
                "gms",
                "Change le mode de jeu en Survie.",
                new CommandGamemode(),
                ""),
                "myplugins.command.gamemode.survival"
        );
        createCommand(new SimpleCommand(
                "gmc",
                "Change le mode de jeu en Créatif.",
                new CommandGamemode(),
                ""),
                "myplugins.command.gamemode.creative"
        );
        createCommand(new SimpleCommand(
                "gmsp",
                "Change le mode de jeu en Spectateur.",
                new CommandGamemode(),
                ""),
                "myplugins.command.gamemode.spectator"
        );

        createCommand(new SimpleCommand(
                "feed",
                "Nourrit entièrement le joueur.",
                new CommandFeed(),
                ""),
                ""
        );

        createCommand(new SimpleCommand(
                "feed",
                "Nourrit entièrement le joueur.",
                new CommandSpawn(),
                ""),
                ""
        );
    }
}

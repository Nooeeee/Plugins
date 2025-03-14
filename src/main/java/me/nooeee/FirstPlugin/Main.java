package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.commands.CommandBroadcast;
import me.nooeee.FirstPlugin.listeners.JoinLeaveListener;
import me.nooeee.FirstPlugin.utils.commands.CommandGamemode;
import me.nooeee.FirstPlugin.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("It works !");

        // Implements EventListener
        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
        System.out.println("Events registered !");

        // Implements Commands
        createCommand(new SimpleCommand(
                "broadcast",
                "Envoyer un message pour tous les joueurs du serveur.",
                new CommandBroadcast(),
                "bc"
        ));

        createCommand(new SimpleCommand(
                "gma",
                "Change le mode de jeu en Adventure.",
                new CommandGamemode(),
                ""
        ));
        createCommand(new SimpleCommand(
                "gms",
                "Change le mode de jeu en Survie.",
                new CommandGamemode(),
                ""
        ));
        createCommand(new SimpleCommand(
                "gmc",
                "Change le mode de jeu en Créatif.",
                new CommandGamemode(),
                ""
        ));
        createCommand(new SimpleCommand(
                "gmsp",
                "Change le mode de jeu en Spectateur.",
                new CommandGamemode(),
                ""
        ));

    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer(); // Récupérer le serveur où est effectué la commande
        server.getCommandMap().register(getName(), command);
        // getName() : nom du plugin, permet de déterminer quelle commande appartient à quel plugin
    }
}

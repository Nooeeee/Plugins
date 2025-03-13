package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.EventListener;
import me.nooeee.FirstPlugin.commands.CommandBroadcast;
import me.nooeee.FirstPlugin.utils.commands.SimpleCommand;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("It works !");

        createCommand(new SimpleCommand(
                "broadcast",
                "Envoyer un message pour tous les joueurs du serveur",
                new CommandBroadcast(),
                "bc"
        ));

        // Implements EventListener
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        System.out.println("Events registered !");
    }

    private void createCommand(SimpleCommand command) {
        CraftServer server = (CraftServer) getServer(); // Récupérer le serveur où est effectué la commande
        server.getCommandMap().register(getName(), command);
        // getName() : nom du plugin, permet de déterminer quelle commande appartient à quel plugin
    }
}

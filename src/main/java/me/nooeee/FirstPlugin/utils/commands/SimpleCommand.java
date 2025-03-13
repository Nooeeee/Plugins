package me.nooeee.FirstPlugin.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class SimpleCommand extends Command {

    private final CommandExecutor executor; // type de classe pour crée commande broadcast

    public SimpleCommand(
            String name,
            String description,
            CommandExecutor executor,
            String... aliases // liste de String
    ){
        super(name, description, "", Arrays.asList(aliases));

        this.executor = executor;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        // Pour que la commande soit executer dans le commandExecutor
        return executor.onCommand(sender, this, commandLabel, args);
    }
}

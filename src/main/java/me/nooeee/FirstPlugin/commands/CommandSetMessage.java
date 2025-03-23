package me.nooeee.FirstPlugin.commands;

import me.nooeee.FirstPlugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSetMessage implements CommandExecutor {

    private final Main plugin;

    public CommandSetMessage(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0) {

            StringBuilder message = new StringBuilder();
            for(int i = 0; i < args.length; i++){
                message.append(args[i]);

                // Ajoute un espace entre chaque arg, sauf pour le dernier
                if(i < args.length - 1) {
                    message.append(" ");
                }
            }

            String finalMessage = message.toString();

            // Récuperer message du joueur (via args de la cmd)
            this.plugin.getConfig().set("join-message", finalMessage);
            // Save le nouveau message dans le config.yml
            this.plugin.saveConfig();

            finalMessage = ChatColor.translateAlternateColorCodes('&', finalMessage);
            sender.sendMessage("Le message a bien été modifié : " + finalMessage);
        } else {
            sender.sendMessage("Vous devez mettre un nouveau message.");
        }

        return true;
    }
}

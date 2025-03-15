package me.nooeee.FirstPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFeed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p) && args.length == 0) {

            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur ou avec un joueur spécifié.");
            return false;
        }

        Player targetPlayer;
        if(args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "" + "Le joueur " + args[0] + " n'est pas connecté ou n'existe pas.");
                return false;
            }
        } else {
            targetPlayer = (Player) sender;
        }

        targetPlayer.setFoodLevel(20);
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + targetPlayer.getName() + " a été rassasié.");



        return true;
    }
}

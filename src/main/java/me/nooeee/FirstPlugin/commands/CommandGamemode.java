package me.nooeee.FirstPlugin.utils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player) && args.length == 0) {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur ou avec un joueur spécifié.");
            return false;
        }

        GameMode targetGameMode = getGameModeFromLabel(label);
        if(targetGameMode == null) {
            sender.sendMessage(ChatColor.RED + "Mode de jeu invalide. Veuillez utiliser : /gma, /gms, /gmc, /gmsp <Joueur>");
            return false;
        }

        // Déterminer le joueur spécifié
        Player targetPlayer;
        if(args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "" + "Le joueur " + args[0] + " n'est pas connecté ou n'existe pas.");

                return false;
            }
        } else {
            // Cible le joueur qui exécute la commande
            targetPlayer = (Player) sender;
        }

        if(targetPlayer.getGameMode() == targetGameMode) {
            sender.sendMessage(ChatColor.YELLOW + targetPlayer.getName() + " est déjà en mode " + targetGameMode.name().toLowerCase() + ".");
            return true;
        }

        if(!targetPlayer.equals(sender)) {
            targetPlayer.sendMessage("Votre mode de jeu a été changé en " + targetGameMode.name().toLowerCase() + " par " + sender.getName());
        }

        // Changer mode de jeu du joueur spécifié
        targetPlayer.setGameMode(targetGameMode);
        sender.sendMessage(ChatColor.GREEN + "" +  ChatColor.ITALIC + targetPlayer.getName() + " a été changé en " + targetGameMode.name().toLowerCase() + " .");

        return true;
    }

    private GameMode getGameModeFromLabel(String label) {
        switch(label.toLowerCase()) {
            case "gma":
                return GameMode.ADVENTURE;
            case "gms":
                return GameMode.SURVIVAL;
            case "gmc":
                return GameMode.CREATIVE;
            case "gmsp":
                return GameMode.SPECTATOR;
            default:
                return null;
        }
    }
}

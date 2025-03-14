package me.nooeee.FirstPlugin.utils.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class CommandGamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur.");
            return false;
        }

        Player player = (Player) sender;

        switch (label.toLowerCase()) {
            case "gma":
                if(player.getGameMode() == GameMode.ADVENTURE) {
                    player.sendMessage("Vous êtes déjà en mode adventure.");
                } else {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("Vous êtes désormais en mode adventure.");
                }
                break;
            case "gms":
                if(player.getGameMode() == GameMode.SURVIVAL) {
                    player.sendMessage("Vous êtes déjà en mode survie.");
                } else {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("Vous êtes désormais en mode survie.");
                }
                break;
            case "gmc":
                if(player.getGameMode() == GameMode.CREATIVE) {
                    player.sendMessage("Vous êtes déjà en mode créatif.");
                } else {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("Vous êtes désormais en créatif.");
                }
                break;
            case "gmsp":
                if(player.getGameMode() == GameMode.SPECTATOR) {
                    player.sendMessage("Vous êtes déjà en mode spectateur.");
                } else {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("Vous êtes désormais en mode spectateur.");
                }
                break;
        }

        return true;
    }
}

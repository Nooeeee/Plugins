package me.nooeee.FirstPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGodMode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player) {
            if(player.isInvulnerable()) {

                player.setInvulnerable(false);
                player.sendMessage(ChatColor.RED + "Vous n'êtes plus invulnérable !");
                return true;

            } else {

                player.setInvulnerable(true);
                player.sendMessage(ChatColor.GREEN + "Vous êtes désormais invulnérable !");
                return true;
            }
        }

        return true;
    }
}

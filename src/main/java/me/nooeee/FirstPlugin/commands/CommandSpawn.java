package me.nooeee.FirstPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player) {
            Location spawn = new Location(player.getWorld(), 35, -60, 0.5, -180, -1.9f);
            player.teleport(spawn);
            player.sendMessage(ChatColor.YELLOW + "Vous avez été téléporté au spawn !");

            return true;
        }

        return true;
    }
}

package me.nooeee.FirstPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandFly implements CommandExecutor {

    private ArrayList<Player> list_of_fly_players = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player p) && args.length == 0) {

            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur ou avec un joueur spécifié.");
            return false;
        }

        Player player = (Player) sender;
        Player targetPlayer;

        if(args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                player.sendMessage("Le joueur " + args[0] + " n'est pas connecté ou n'existe pas.");
                return true;
            } else {
                flyMethod(targetPlayer);
            }
        } else {
            flyMethod(player);
        }

        return true;
    }

    private void flyMethod(Player player) {
        if(list_of_fly_players.contains(player)) {
            list_of_fly_players.remove(player);
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.YELLOW + "Mode fly"  + ChatColor.RED + " désactivé" + ChatColor.YELLOW + ".");
        } else if(!(list_of_fly_players.contains(player))) {
            list_of_fly_players.add(player);
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.YELLOW + "Mode fly"  + ChatColor.GREEN + " activé" + ChatColor.YELLOW + ".");
        }
    }
}

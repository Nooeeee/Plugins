package me.nooeee.FirstPlugin.commands;

import me.nooeee.FirstPlugin.utils.commands.CooldownCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {

    private final CooldownCommandManager cooldownManager;

    public CommandHeal(CooldownCommandManager cooldownManager) {
        this.cooldownManager = cooldownManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player p) && args.length == 0) {

            sender.sendMessage("Cette commande ne peut être utilisée que par un joueur ou avec un joueur spécifié.");
            return false;
        }

        Player player = (Player) sender;
        Player targetPlayer;

        if(cooldownManager.isOnCooldown(player, "heal")){
            int timeLeft = cooldownManager.getTimeLeft(player, "heal");
            player.sendMessage(ChatColor.RED + "Veuillez patienter " + timeLeft + " secondes avant de réutiliser cette commande.");
            return true;
        }

        if(args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                sender.sendMessage(ChatColor.RED + "Le joueur " + args[0] + " n'est pas connecté ou n'existe pas.");
                return false;
            }
        } else {
            targetPlayer = player;
        }

        targetPlayer.setHealth(20);
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + targetPlayer.getDisplayName() + ChatColor.GREEN + " a été complètement soigné.");

        cooldownManager.setCooldowns(player, "heal");
        return true;
    }
}

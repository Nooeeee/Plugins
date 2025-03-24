package me.nooeee.FirstPlugin.commands;

import me.nooeee.FirstPlugin.utils.commands.CooldownCommandManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommandFlower implements CommandExecutor {

    private final CooldownCommandManager cooldownManager;

    public CommandFlower(CooldownCommandManager cooldownManager) {
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

        if(cooldownManager.isOnCooldown(player, "flower")) {
            int timeLeft = cooldownManager.getTimeLeft(player, "flower");
            player.sendMessage(org.bukkit.ChatColor.RED + "Veuillez patienter " + timeLeft + " secondes avant de réutiliser cette commande.");
            return true;
        }

        if(args.length > 0) {
            targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null) {
                player.sendMessage(ChatColor.RED + "Le joueur " + args[0] + " n'est pas connecté ou n'existe pas.");
                return false;
            }
        } else {
            targetPlayer = player;
        }

        // Création de l'item
        ItemStack flower = new ItemStack(Material.WHITE_TULIP, 1);
        ItemMeta flowerMeta = flower.getItemMeta();
        flowerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Magnifique tulipe");

        List<String> flowerLore = new ArrayList<>();
        flowerLore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Voici une magnifique tulipe d'un blanc étincelant");
        flowerLore.add(ChatColor.GRAY + "" + ChatColor.ITALIC+ "Offerte car vous êtes une magnifique personne !");
        flowerMeta.setLore(flowerLore);

        // Ajout d'un enchantement
        flowerMeta.addEnchant(Enchantment.LUCK, 1000, true);

        // Add Meta
        flower.setItemMeta(flowerMeta);

        // Check du sender/targetPlayer
        if(targetPlayer.equals(player)) {
            targetPlayer.sendMessage(ChatColor.ITALIC + "" + ChatColor.GREEN + "Tenez, votre magnifique petite tulipe !");
        } else {
            targetPlayer.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + player.getDisplayName() + " vous a offert une magnifique tulipe !");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + targetPlayer.getDisplayName() + " a bien reçu la fleur que vous lui avez offerte !");
        }

        // .setItem(1,flower) ajoute l'item dans la premiere case de la hotbar
        targetPlayer.getInventory().addItem(flower);

        cooldownManager.setCooldowns(player, "flower");
        return true;
    }
}

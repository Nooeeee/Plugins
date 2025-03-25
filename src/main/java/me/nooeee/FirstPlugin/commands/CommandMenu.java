package me.nooeee.FirstPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CommandMenu implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("Seul les joueurs peuvent utiliser cette commande.");
        }

        Player player = (Player) sender;

        // 9, 18, 27, 36, 45, 54 slots
        Inventory menu = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Custom GUI");

        // Buttons
        ItemStack fight = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack death = new ItemStack(Material.TNT);
        ItemStack feed = new ItemStack(Material.BREAD);

        //Item data
        ItemMeta fightMeta = fight.getItemMeta();
        fightMeta.setDisplayName(ChatColor.BLUE + "Fight");
        fightMeta.setLore(List.of(ChatColor.ITALIC + "Du fight et rien de plus."));
        fight.setItemMeta(fightMeta);

        ItemMeta deathMeta = death.getItemMeta();
        deathMeta.setDisplayName(ChatColor.DARK_RED + "Kill");
        deathMeta.setLore(List.of(ChatColor.ITALIC + "La fin est proche."));
        death.setItemMeta(deathMeta);

        ItemMeta feedMeta = feed.getItemMeta();
        feedMeta.setDisplayName(ChatColor.YELLOW + "Feed");
        feedMeta.setLore(List.of(ChatColor.ITALIC + "Rassasiez-vous un peu."));
        feed.setItemMeta(feedMeta);


        // addItem() --> le 1er slot dispo ; setContents() --> dans une array ; setItem()  --> avec un index
        menu.setItem(0, feed);
        menu.setItem(4, death);
        menu.setItem(8, fight);

        // Open menu
        player.openInventory(menu);

        return true;
    }
}

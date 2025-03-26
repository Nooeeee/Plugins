package me.nooeee.FirstPlugin.listeners;

import me.nooeee.FirstPlugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EventListener;

public class MenuListener implements Listener {

    private final Main plugin;

    public MenuListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        /*
            equals(ChatColor.AQUA + "Custom Gui")
                ou
            ChaColor.stripColor(e.getView().getTitle().equals("Custom Gui"))
        */

        if(e.getView().getTitle().equals(ChatColor.AQUA + "Custom GUI")) {

            if(e.isRightClick()) return;

            // Cancel le click pour ne pas récup l'item
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            switch(e.getCurrentItem().getType()) {
                case BREAD:
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.YELLOW + "Vous avez rassasié !");
                    break;
                case TNT:
                    player.setHealth(0);
                    player.sendMessage(ChatColor.YELLOW + "Force, courage, et honneur, voila l'humble mort d'un vrai guerrier !");
                    break;
                case DIAMOND_SWORD:
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "Ne souille pas ton honneur, guerrier !");
                    break;
            }

            // Close le GUI une fois cliquée
            player.closeInventory();
        }
    }
}

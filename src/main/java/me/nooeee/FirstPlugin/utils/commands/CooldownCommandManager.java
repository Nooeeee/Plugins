package me.nooeee.FirstPlugin.utils.commands;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CooldownCommandManager {

    // Stocke timestamps d'expiration des cd (via key: UUID & value: command)
    private final HashMap<String, Long> cooldowns = new HashMap<>();
    // Stocke durée des cd pour chaque commande depuis config.yml
    private final HashMap<String, Integer> cooldownTimes = new HashMap<>(); //

    // Load les cooldowns depuis config.yml
    public CooldownCommandManager(FileConfiguration config) {
        loadCooldowns(config);
    }

    // Load les cooldowns depuis config.yml et stocke dans le cooldownTimes
    public void loadCooldowns(FileConfiguration config) {
        cooldownTimes.clear();

        if(config.contains("cooldown") || config.getConfigurationSection("cooldowns") == null) {
            System.out.println("Aucune section 'cooldown' trouvée dans le config.yml !");
            return;
        }

        for (String command : config.getConfigurationSection("cooldowns").getKeys(false)) {
            cooldownTimes.put(command, config.getInt("cooldowns" + command ));
        }
    }

    // Vérifie si le joueur a un cooldown sur la commande
    public boolean isOnCooldown(Player player, String command) {
        String key = player.getUniqueId() + "_" + command;
        if(!cooldowns.containsKey(key)) return false;

        long expireTime = cooldowns.get(key);
        return System.currentTimeMillis() < expireTime;
    }

    // Retourne le temps restant avant la fin du cooldown
    public int getTimeLeft(Player player, String command) {
        String key = player.getUniqueId() + "_" + command;
        if(!cooldowns.containsKey(key)) return 0;

        long expireTime = cooldowns.get(key);
        return (int) ((expireTime - System.currentTimeMillis()) / 1000);
    }

    // Met un cooldown sur la commande & stocke le temps d'expiration
    public void setCooldowns(Player player, String command) {
        if(!cooldownTimes.containsKey(command)) return;

        String key = player.getUniqueId() + "_" + command;
        long expireTime = System.currentTimeMillis() + (cooldownTimes.get(command) * 1000L);
        cooldowns.put(key, expireTime);
    }
}

package me.nooeee.FirstPlugin;

import me.nooeee.FirstPlugin.commands.CommandBroadcast;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("It works !");

        getCommand("broadcast").setExecutor(new CommandBroadcast());
    }
}

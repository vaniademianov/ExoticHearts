package net.remote.access.exohearts;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class ExoHearts extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger l = Bukkit.getLogger();
        l.info("[EH] Here I am!");
        getCommand("vers").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                sender.sendMessage("Версія плагіну: 0.1.1 DEV BUILD");
                return true;
            }
        });
        Listener lst = new Events();
        org.bukkit.Bukkit.getServer().getPluginManager().registerEvents(lst, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

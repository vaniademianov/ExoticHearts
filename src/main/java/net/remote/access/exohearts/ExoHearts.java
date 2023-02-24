package net.remote.access.exohearts;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;
//Лазуритове Серце
//        В 1.5 Більше досвіду
//        Удача 1 Нескінченна
//        64 блоків лазуриту 32 книжок серце
//        Редстоун Серце
//        В 2 рази більше редстоуну
//        Подвоїний крафт для всіх редстоун компонентів типу компораторів
//        32 Блоків редстоуну 16 повторювачів серце
//        Залізне
//        Пасивні големи
//        Ремонт інструментів (злиток заліза 30 міцності)
//        >8 блоків заліза 2 Мака серце
//        Золоте серце
//        Пасивні пігліни
//        Швидкість копання 1.5
//        16 блоків золота 2 золотих меча серце
//        Діамантове Серце
//        В 1.5 більше Діамантів
//        в 1.5 більше захисту
//        16 блоків діамантів 2 діамантових нагрудника серце
//        Ендер Серце
//        Захист від ендер менів
//        Телепортує як хорус коли 1 хп
//        16 перлів 16 очей енду нагрудника серце
//        Шалкер Серце
//        Урон від шалкерів 1/2
//        x2 луту з шалкерів
//        6 шалкер блоків 16 стержню енду серце
//        Візер Серце
//        Імунітет до візеру
//        шанс 1/6 накласти ефект візерів на противника
//        6 зірок незеру  32 Кістки
public final class ExoHearts extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger l = Bukkit.getLogger();
        l.info("[EH] Here I am!");
        getCommand("vers").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                sender.sendMessage("Версія плагіну: 0.0.3 DEV BUILD");
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

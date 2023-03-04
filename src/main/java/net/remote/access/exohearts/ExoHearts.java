package net.remote.access.exohearts;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import de.tr7zw.nbtapi.NBTItem;
import me.darkolythe.customrecipeapi.CustomRecipeAPI;
import net.remote.access.exohearts.items.DefaultItem;
import net.remote.access.exohearts.items.LapisHeart;
import net.remote.access.exohearts.items.RedstoneHeart;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
//import com.onarandombox.MultiverseCore.api.
import java.util.Collection;
import java.util.Objects;
import java.util.logging.Logger;

import static org.bukkit.Material.AIR;

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
    private Utils u = new Utils();


    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger l = Bukkit.getLogger();
        l.info("[EH] Here I am!");

        getCommand("vers").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                sender.sendMessage("Версія плагіну: 0.0.4 DEV BUILD");
                return true;
            }
        });
        Listener lst = new Events();
        org.bukkit.Bukkit.getServer().getPluginManager().registerEvents(lst, this);
        getCommand("equip").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                NBTItem nbt;

                Player p = Bukkit.getPlayer(sender.getName());
                if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                NBTItem hand = new NBTItem(p.getItemInHand());

                    for (int i = 9; i < 18; i++) {
                        nbt = new NBTItem(p.getInventory().getItem(i));

                        if (!nbt.getBoolean("is_heart") && hand.getBoolean("is_heart")) {
                            p.getInventory().setItem(i, p.getItemInHand());
                            p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                            if (u.hearts_equepied(p) == 1) {
                                p.setMaxHealth(p.getMaxHealth()+20);
                            }
                            else {
                                p.setMaxHealth(p.getMaxHealth()+4);
                            }
                            return true;
                        }
                    }
                }
                sender.sendMessage("Дія не вдалася, оскільки інвентар сердець повний та/або у вас в руці не серце");
                return false;
            }
        });
        getCommand("texturepack").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                Bukkit.getPlayer(sender.getName()).setTexturePack("https://www.dropbox.com/s/k3hei7c3dlru32f/exoticpack.4.zip?dl=1");
                return true;
            }
        });
        getCommand("unequip").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
                if (args.length == 0) {
                    sender.sendMessage("Правильне використання команди: unequip <номер слоту від 1 до 9>");
                    return false;
                }
                Player p = Bukkit.getPlayer(sender.getName());
                ItemStack it = p.getInventory().getItem(8+Integer.parseInt(args[0]));
                NBTItem nb = new NBTItem(it);
                ItemStack dye = new ItemStack(Material.LIGHT_GRAY_DYE);
                ItemMeta meta = dye.getItemMeta();
                meta.setDisplayName("§f§4§lТут може бути ваше сердечко <3");
                meta.setCustomModelData(1717);
                dye.setItemMeta(meta);
                NBTItem it_nbt = new NBTItem(dye);
                it_nbt.setString("heart_type", "null_heart");

                dye = it_nbt.getItem();
                if (!nb.getBoolean("is_heart") || Integer.parseInt(args[0]) == 0 || Integer.parseInt(args[0]) > 9) {
                    sender.sendMessage("У вказаному слоті не має серця!");
                    return false;
                }

                p.getInventory().setItem(8+Integer.parseInt(args[0]), dye);
                p.getInventory().addItem(it);
                if (u.hearts_equepied(p) == 0) {
                    p.setMaxHealth(p.getMaxHealth()-20);
                }
                else {
                    p.setMaxHealth(p.getMaxHealth()-4);
                }
                return true;
            }
        });
        getCommand("give_heart").setExecutor(new CommandExecutor() {

            @Override
            public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
                if (args.length == 0) {
                    sender.sendMessage("/give_heart <тип серця>");
                    return false;
                }
                DefaultItem nt = null;
                if (Objects.equals(args[0], "lapis")) {
                    nt = new LapisHeart();
                }
                if (Objects.equals(args[0], "redstone")) {
                    nt = new RedstoneHeart();
                }
                if (nt == null) {
                    sender.sendMessage("Невідомий тип серця, ви можете видати собі наступні серця: \n\tЛазуритове (lapis) \n\tРедстоунове (redstone)");
                    return false;
                }
//                if (sender instanceof Player) {
//
//                }
                Bukkit.getPlayer(sender.getName()).getInventory().addItem(nt.selfeee());
                sender.sendMessage("Успішно видано серце " + args[0]);
                return true;
            }
        });
        new BukkitRunnable()
        {
            @Override
            public void run()
            {

                Collection<? extends Player> online_p = Bukkit.getOnlinePlayers();
                PotionEffect potion_l = new PotionEffect(PotionEffectType.SPEED, 20*3, 1);
                PotionEffect potion_r = new PotionEffect(PotionEffectType.FAST_DIGGING, 20*3, 2);
                for (Player p : online_p) {
                    if (u.isEquipped("lapis",p)) {


                        p.addPotionEffect(potion_l);


                    }
                    if (u.isEquipped("redstone",p)) {


                        p.addPotionEffect(potion_r);


                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L*3);
    }

    @Override
    public void onDisable() {
        Logger l = Bukkit.getLogger();
        l.info("[EH] Bye!");
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();
        worldManager.unloadWorld("structures");
        // Plugin shutdown logic
    }
}

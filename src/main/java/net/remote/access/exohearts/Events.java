package net.remote.access.exohearts;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import de.tr7zw.nbtapi.NBTItem;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import net.remote.access.exohearts.items.DefaultItem;
import net.remote.access.exohearts.items.LapisHeart;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.logging.Logger;

import static org.bukkit.Material.*;
import static org.bukkit.enchantments.Enchantment.LUCK;
import static org.bukkit.util.NumberConversions.round;

public class Events implements Listener {
    private Utils u = new Utils();


    @EventHandler
    public void MobKillEvent(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        if (dead.getLastDamageCause() instanceof Player) {
            Player p = ((Player) dead.getLastDamageCause()).getPlayer();
            if (u.isEquipped("lapis", p)) {

                e.setDroppedExp(round(e.getDroppedExp()*1.5));
            }
        }
    }
    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e) {
        ItemStack is;
        if (u.isEquipped("redstone", e.getPlayer())) {
            if (e.getBlock().getType() == REDSTONE_ORE) {
                Collection<ItemStack> cl = e.getBlock().getDrops(e.getPlayer().getItemInHand());

                for (ItemStack a : cl) {
                    if (u.is_ore(e.getBlock())) {
                        a.setAmount(a.getAmount() * 2);
                    }

                }
            }
        }
        if (u.isEquipped("lapis", e.getPlayer())) {
            Collection<ItemStack> cl = e.getBlock().getDrops(e.getPlayer().getItemInHand());

            for (ItemStack a : cl) {
                if (u.is_ore(e.getBlock())) {
                    a.setAmount(a.getAmount() * 2);
                }

            }
            if (!e.isCancelled()) {
                e.setDropItems(false);
                e.setExpToDrop(round(e.getExpToDrop()*1.5));
                for (ItemStack a : cl) {

                    e.getPlayer().getWorld().dropItemNaturally(e.getBlock().getLocation(), a);
                }
            }
        }
    }
//    @EventHandler
//    public void HotBarSlotChangeEvent(PlayerItemHeldEvent e) {
//        NBTItem it = new NBTItem(e.getPlayer().getItemInHand());
//        Logger l = Bukkit.getLogger();
//        e.getPlayer().sendMessage(it.asNBTString());
//    }
    @EventHandler
    public void PlayerInventorySlotChangeEvent(PlayerInventorySlotChangeEvent e) {


//        if (isEquipped("lapis", e.getPlayer())) {
//
//            e.getNewItemStack().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
//        }
    }

    @EventHandler
//    public void OnPlayerMove(PlayerMoveEvent e) {
//        e.getPlayer().sendMessage("Ви порухалися");
//    }
    public void OnPlayerJoin(PlayerJoinEvent e) {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
        MVWorldManager worldManager = core.getMVWorldManager();

        if (Bukkit.getWorld("structures") == null) {
            worldManager.addWorld(
                    "structures", // The worldname
                    World.Environment.NORMAL, // The overworld environment type.
                    null, // The world seed. Any seed is fine for me, so we just pass null.
                    WorldType.FLAT, // Nothing special. If you want something like a flat world, change this.
                    false, // This means we want to structures like villages to generator, Change to false if you don't want this.
                    null // Specifies a custom generator. We are not using any so we just pass null.
            );
        }
        worldManager.loadWorld("structures");
//        e.getPlayer().setTexturePack("https://www.dropbox.com/s/k3hei7c3dlru32f/exoticpack.4.zip?dl=1");
        ItemStack dye = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName("§f§4§lТут може бути ваше сердечко <3");
        meta.setCustomModelData(1717);
        dye.setItemMeta(meta);
        NBTItem it_nbt = new NBTItem(dye);
        it_nbt.setString("heart_type", "null_heart");
        dye = it_nbt.getItem();
        NBTItem ir;
        for (int i = 9; i<18; i++) {
            if (e.getPlayer().getInventory().getItem(i) != null && e.getPlayer().getInventory().getItem(i).getType() != AIR) {
                ir = new NBTItem(e.getPlayer().getInventory().getItem(i));
                if (!ir.getBoolean("is_heart")) {
                    e.getPlayer().getInventory().setItem(i, dye);
                }
            }
            else {
                e.getPlayer().getInventory().setItem(i, dye);
            }
        }
    }
    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
        e.setKeepInventory(true);
        List<ItemStack> drops = e.getDrops();
        ItemStack dye = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName("§f§4§lТут може бути ваше сердечко <3");
        meta.setCustomModelData(1717);
        dye.setItemMeta(meta);
        NBTItem it_nbt = new NBTItem(dye);
        it_nbt.setString("heart_type", "null_heart");

        dye = it_nbt.getItem();
        for (int i = 0; i<45; i++) {
            if (e.getPlayer().getInventory().getItem(i) != null && e.getPlayer().getInventory().getItem(i).getType() != AIR){
                NBTItem itm = new NBTItem(e.getPlayer().getInventory().getItem(i));
                if (!Objects.equals(itm.getString("heart_type"), "null_heart")) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), e.getPlayer().getInventory().getItem(i));

                }
                if (i > 8 && i < 18) {
                    e.getPlayer().getInventory().setItem(i,dye);
                }
                else {
                    e.getPlayer().getInventory().setItem(i, new ItemStack(AIR));
                }
            }
        }
    }
//    @EventHandler
//    public void PrepareCraftEvent(PrepareItemCraftEvent e) {
//        if (e.getInventory().getMatrix()[1].getType() == LAPIS_BLOCK && e.getInventory().getMatrix()[1].getAmount() == 64 && e.getInventory().getMatrix()[4].getType() == BOOK && e.getInventory().getMatrix()[4].getAmount() == 32) {
//            DefaultItem it = new LapisHeart();
//            e.getInventory().setResult(it.selfeee());
//        }
//        Logger l = Bukkit.getLogger();
//        l.info(Arrays.toString(e.getInventory().getMatrix()));
//    }
    @EventHandler
    public void ItemCraftevent(CraftItemEvent e) {
        Logger l = Bukkit.getLogger();
        ArrayList<Material> red = new ArrayList<>();
        red.add(COMPARATOR);
        red.add(DROPPER);
        red.add(DAYLIGHT_DETECTOR);
        red.add(PISTON);
        red.add(STICKY_PISTON);
        red.add(HOPPER);
        red.add(LECTERN);
        red.add(TARGET);
        red.add(TRIPWIRE_HOOK);
        red.add(OBSERVER);
        red.add(LEVER);
        red.add(REPEATER);
        red.add(DISPENSER);
        red.add(REDSTONE_TORCH);
        red.add(LIGHTNING_ROD);
        red.add(TNT);
        red.add(REDSTONE_LAMP);
        red.add(NOTE_BLOCK);
        if (u.isEquipped("redstone", Bukkit.getPlayer(e.getWhoClicked().getName()))) {
            for (Material a : red) {
                if (a == e.getRecipe().getResult().getType()) {

                    ItemStack r = e.getRecipe().getResult();
                    r.setAmount(round(r.getAmount() * 1.1));
                    e.setCursor(r);
                }
            }
        };
    }
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {

        if (e.getClickedInventory().getType() == InventoryType.CRAFTING) {

            for (int i = 0; i<9; i++) {
                e.getClickedInventory().setItem(i, new ItemStack(AIR));
            }
        }
        if (e.getSlot() < 18 && e.getSlot() > 8) {

            e.setCancelled(true);

//            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
//                nt = new NBTItem(e.getCurrentItem());
//                if (!nt.getBoolean("is_heart")) {
//                    e.setCancelled(true);
//                } else {
//                    e.setCursor(new ItemStack(Material.AIR));
//                }
//            } else {
//                nt = null;
//            }

            Logger l = Bukkit.getLogger();
//        l.info(nt.getKeys().toString() + " " + nb.getBoolean("is_heart").toString());

//        if ("§4§lТут може бути ваше сердечко <3".equals(nt.getItem().getItemMeta().getDisplayName()) && !nb.getBoolean("is_heart")) {
//            e.setCancelled(true);
//            return;
//        }
//            if (e.getSlot() < 18 && e.getSlot() > 8) {
//                if (nt != null && nt.getBoolean("is_heart") && !"§4§lТут може бути ваше сердечко <3".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {
//                    if (nb == null) {
//                        e.getInventory().setItem(e.getSlot(), dye);
//                        return;
//                    }
//
//                    if (!nb.getBoolean("is_heart")) {
//                        e.setCancelled(true);
//                        return;
//                    }
//                }
//                if (nb != null && nb.getBoolean("is_heart")) {
//                    if (!nt.getBoolean("is_heart")) {
//                        e.setCancelled(true);
//                        return;
//                    }
//                    else {
//                        e.setCancelled(true);
//                        e.getInventory().setItem(e.getSlot(), nb.getItem());
//                        e.getCursor().setType(Material.AIR);
//                        return;
//                    }
        }

    }}


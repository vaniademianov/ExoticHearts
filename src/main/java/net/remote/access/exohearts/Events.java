package net.remote.access.exohearts;

import de.tr7zw.nbtapi.NBTItem;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
import java.util.logging.Logger;

import static org.bukkit.enchantments.Enchantment.LUCK;

public class Events implements Listener {
    @EventHandler
    public void PlayerInventorySlotChangeEvent(PlayerInventorySlotChangeEvent e) {
        if (isEquipped("lapis", e.getPlayer())) {
            e.getNewItemStack().addEnchantment(LUCK, 1);
        }
    }
    public boolean isEquipped(String s, Player p) {
        NBTItem nb;
        for (int i = 9; i<19; i++) {
            if (p.getInventory().getItem(i) != null || p.getInventory().getItem(i).getType() != Material.AIR) {
                nb = new NBTItem(p.getInventory().getItem(i));
                if (Objects.equals(nb.getString("heart_type"), "lapis")) {
                    return true;
                }
            }
        }
        return false;
    }
    @EventHandler
//    public void OnPlayerMove(PlayerMoveEvent e) {
//        e.getPlayer().sendMessage("Ви порухалися");
//    }
    public void OnPLayerJoin(PlayerJoinEvent e) {
        e.getPlayer().setTexturePack("https://www.dropbox.com/s/9qv9im0893vzl93/exoticpack3.zip?dl=1");
        ItemStack dye = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName("§f§4§lТут може бути ваше сердечко <3");
        meta.setCustomModelData(1717);
        dye.setItemMeta(meta);
        for (int i = 9; i<18; i++) {
            e.getPlayer().getInventory().setItem(i, dye);

        }
    }
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
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


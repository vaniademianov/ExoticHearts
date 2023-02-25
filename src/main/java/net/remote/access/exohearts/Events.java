package net.remote.access.exohearts;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {
    @EventHandler
//    public void OnPlayerMove(PlayerMoveEvent e) {
//        e.getPlayer().sendMessage("Ви порухалися");
//    }
    public void OnPLayerJoin(PlayerJoinEvent e) {
        ItemStack dye = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta meta = dye.getItemMeta();
        meta.setDisplayName("§f§4§lТут може бути ваше сердечко <3");
        meta.setCustomModelData(1717);
        dye.setItemMeta(meta);
        for (int i = 9; i<18; i++) {
            e.getPlayer().getInventory().setItem(i, dye);

        }
    };
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() < 18 && e.getSlot() > 8) {
            e.setCancelled(true);
        }
    };
}

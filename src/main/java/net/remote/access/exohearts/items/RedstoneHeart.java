package net.remote.access.exohearts.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RedstoneHeart implements DefaultItem{

    public void OnRightClick(PlayerInteractEvent e) {

    }

    public void OnLeftClick(PlayerInteractEvent e) {

    }

    public ItemStack selfeee() {
        ItemStack it = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta it_meta = it.getItemMeta();
        it_meta.setCustomModelData(getTexture());
        it_meta.setDisplayName("§f§4§lРедстоунове серце");

        it.setItemMeta(it_meta);
        NBTItem it_nbt = new NBTItem(it);
        it_nbt.setString("heart_type", "redstone");
        it_nbt.setBoolean("is_heart", true);
        return it_nbt.getItem();
    }

    public int getTexture() {
        return 1719;
    }
}

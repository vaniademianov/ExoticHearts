package net.remote.access.exohearts.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface DefaultItem {
    public void OnRightClick(PlayerInteractEvent e);
    public void OnLeftClick(PlayerInteractEvent e);
    public ItemStack selfeee();
    public int getTexture();
}

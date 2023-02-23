package net.remote.access.exohearts;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Events implements Listener {
    @EventHandler
    public void OnPlayerMove(PlayerMoveEvent e) {
        e.getPlayer().sendMessage("Ви порухалися");
    }
}

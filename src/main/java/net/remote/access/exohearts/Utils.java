package net.remote.access.exohearts;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;

import static org.bukkit.Material.*;
import static org.bukkit.Material.STONE;

public class Utils {
    public boolean isEquipped(String s, Player p) {
        NBTItem nb;
        for (int i = 9; i<19; i++) {
            if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() != AIR) {
                nb = new NBTItem(p.getInventory().getItem(i));
                if (Objects.equals(nb.getString("heart_type"), s)) {
                    return true;
                }

            }
        }
        return false;
    }
    boolean is_ore(Block b) {
        ArrayList<Material> mt = new ArrayList<>();
        mt.add(DIAMOND_ORE);
        mt.add(COAL_ORE);
        mt.add(IRON_ORE);
        mt.add(COPPER_ORE);
        mt.add(ANCIENT_DEBRIS);
        mt.add(GOLD_ORE);
        mt.add(NETHER_GOLD_ORE);
        mt.add(EMERALD_ORE);
        mt.add(REDSTONE_ORE);
        mt.add(LAPIS_ORE);
        mt.add(DEEPSLATE_COAL_ORE);
        mt.add(DEEPSLATE_COPPER_ORE);
        mt.add(DEEPSLATE_DIAMOND_ORE);
        mt.add(DEEPSLATE_GOLD_ORE);
        mt.add(DEEPSLATE_EMERALD_ORE);
        mt.add(DEEPSLATE_LAPIS_ORE);
        mt.add(DEEPSLATE_IRON_ORE);
        mt.add(DEEPSLATE_REDSTONE_ORE);
        mt.add(STONE);
        for (Material m : mt) {
            if (m == b.getType()) {
                return true;
            }
        }
        return false;
    }
    int hearts_equepied(Player p) {
        int r = 0;
        NBTItem nb;
        for (int i = 9; i<19; i++) {
            if (p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() != AIR) {
                nb = new NBTItem(p.getInventory().getItem(i));
                if (nb.getBoolean("is_heart")) {
                    r+=1;
                }
            }
        }
        return r;
    }
}

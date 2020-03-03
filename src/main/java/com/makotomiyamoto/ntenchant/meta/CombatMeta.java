package com.makotomiyamoto.ntenchant.meta;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class CombatMeta {
    public static int getItemLevel(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return 0;
        }
        List<String> lore = meta.getLore();
        if (lore == null || lore.size() < 2) {
            return 0;
        }
        String itemLevelLine = lore.get(0);
        return Integer.parseInt(itemLevelLine.split(" ")[2]);
    }
}

package com.makotomiyamoto.ntenchant.meta;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public abstract class Eval {
    public static boolean isEnchantable(ItemStack item) {
        if (item == null) {
            return false;
        }
        return (Enchantment.MENDING.canEnchantItem(item) || isProbablyWeapon(item));
    }
    public static boolean isProbablyWeapon(ItemStack item) {
        if (item == null) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        List<String> lore = meta.getLore();
        if (lore == null || lore.size() < 6) {
            return false;
        }
        return (lore.get(3).contains("Damage"));
    }
    public static boolean itemHitLevelCap(ItemStack item) {
        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        int count = 0;
        for (Enchantment enchantment : enchantments.keySet()) {
            count += enchantments.get(enchantment);
        }
        System.out.println("levels: " + count);
        return count >= 10;
    }
}
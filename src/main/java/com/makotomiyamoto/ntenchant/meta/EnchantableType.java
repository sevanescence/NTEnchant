package com.makotomiyamoto.ntenchant.meta;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum EnchantableType {
    WEAPON,
    BOW,
    CROSSBOW,
    TRIDENT,
    TOOL,
    ARMOR,
    HELMET,
    BOOTS,
    NONE;
    public static EnchantableType getByType(ItemStack itemStack) {
        if (Eval.isProbablyWeapon(itemStack) && Enchantment.DAMAGE_ALL.canEnchantItem(itemStack)) {
            return WEAPON;
        } else if (Enchantment.ARROW_DAMAGE.canEnchantItem(itemStack)) {
            return BOW;
        } else if (Enchantment.PIERCING.canEnchantItem(itemStack)) {
            return CROSSBOW;
        } else if (Enchantment.LOYALTY.canEnchantItem(itemStack)) {
            return TRIDENT;
        } else if (Enchantment.DIG_SPEED.canEnchantItem(itemStack)) {
            return TOOL;
        } else if (Enchantment.WATER_WORKER.canEnchantItem(itemStack)) {
            return HELMET;
        } else if (Enchantment.DEPTH_STRIDER.canEnchantItem(itemStack)) {
            return BOOTS;
        } else if (Enchantment.PROTECTION_ENVIRONMENTAL.canEnchantItem(itemStack)) {
            return ARMOR;
        }
        return NONE;
    }
}

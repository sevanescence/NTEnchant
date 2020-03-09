package com.makotomiyamoto.ntenchant.meta;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InventoryIcon {
    private ItemStack icon;
    public InventoryIcon(Material material) {
        icon = new ItemStack(material);
    }
    public ItemStack build() {
        return icon;
    }
    public InventoryIcon addAttribute(Attribute attribute, AttributeModifier modifier) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.addAttributeModifier(attribute, modifier);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon addEnchant(Enchantment enchantment, int level) {
        icon.addUnsafeEnchantment(enchantment, level);
        return this;
    }
    public InventoryIcon addItemFlags(ItemFlag... itemFlags) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.addItemFlags(itemFlags);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon removeAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.removeAttributeModifier(attribute, modifier);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon removeEnchant(Enchantment enchantment) {
        icon.removeEnchantment(enchantment);
        return this;
    }
    public InventoryIcon removeitemFlags(ItemFlag... itemFlags) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.removeItemFlags(itemFlags);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon setName(String name) {
        name = name.replaceAll("&", "§");
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon setLore(List<String> lore) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon setLore(String combinedList) {
        combinedList = combinedList.replaceAll("&", "§");
        List<String> lore = Arrays.asList(combinedList.split("\n"));
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
        return this;
    }
    public InventoryIcon setStackCount(int amount) {
        icon.setAmount(amount);
        return this;
    }
    public InventoryIcon setUnbreakable(boolean unbreakable) {
        assert icon.getItemMeta() != null;
        ItemMeta meta = icon.getItemMeta();
        meta.setUnbreakable(unbreakable);
        icon.setItemMeta(meta);
        return this;
    }
}

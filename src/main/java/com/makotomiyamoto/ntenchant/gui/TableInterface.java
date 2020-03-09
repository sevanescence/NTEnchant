package com.makotomiyamoto.ntenchant.gui;

import com.makotomiyamoto.ntenchant.Data;
import com.makotomiyamoto.ntenchant.meta.CombatMeta;
import com.makotomiyamoto.ntenchant.meta.EnchantableType;
import com.makotomiyamoto.ntenchant.meta.InventoryIcon;
import com.makotomiyamoto.ntenchant.meta.Roman;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public final class TableInterface implements InventoryHolder, Inventory {
    private Inventory inventory;
    public TableInterface(Player player) {
        inventory = Bukkit.createInventory(player, 27, Data.NAME);
    }
    private TableInterface(Inventory existingInventory) {
        this.inventory = existingInventory;
    }
    public static TableInterface loadFromWindow(Inventory existingInventory) {
        return new TableInterface(existingInventory);
    }
    public Inventory build() {
        ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack slots = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        /* set up item meta */
        ItemMeta emptyMetaTemplate = border.getItemMeta();
        assert emptyMetaTemplate != null;
        emptyMetaTemplate.setDisplayName("§a");
        border.setItemMeta(emptyMetaTemplate);
        slots.setItemMeta(emptyMetaTemplate);
        /* end of ugly code */
        for (int value : Data.BORDER) {
            inventory.setItem(value, border);
        }
        for (int value : Data.ENCH_SLOTS) {
            inventory.setItem(value, slots);
        }
        return inventory;
    }
    private int getReq(ItemStack itemStack, int lvl, float scale) {
        int temp = (int)(CombatMeta.getItemLevel(itemStack)/scale);
        if (temp < 1) temp = 1; temp *= (lvl*Math.pow(scale, -1*scale));
        return Math.max(temp, 1);
    }
    private InventoryIcon buildIcon(ItemStack itemStack, Enchantment enchantment, Material material, float scale) {
        int lvl = itemStack.getEnchantmentLevel(enchantment);
        lvl = (lvl < 1) ? 1 : lvl+1;
        int lvlAdvReq = getReq(itemStack, lvl, scale);
        /* enchantment display styling */
        String enchString = enchantment.getKey().getKey().replaceAll("_", " ");
        String[] split = enchString.split(" ");
        enchString = "";
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].substring(0, 1).toUpperCase().concat(split[i].substring(1));
            enchString = (i == 0) ? enchString.concat(split[i]) : enchString.concat(" " + split[i]);
        }

        /* end of dirty code */
        @SuppressWarnings("UnnecessaryLocalVariable")
        InventoryIcon item = new InventoryIcon(material)
                .addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setName("&d" + enchString + " " + Roman.toRoman(lvl))
                .setLore("&7" + lvlAdvReq + " Exp Levels")
                .addEnchant(enchantment, lvl)
                .addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return item;
    }
    public void render(EnchantableType type, ItemStack item) {
        ItemStack none = new InventoryIcon(Material.LIGHT_GRAY_STAINED_GLASS_PANE).setName("&a").build();
        switch (type) {
            case WEAPON:
                System.out.println("weapon");
                InventoryIcon sharpness = buildIcon(item, Enchantment.DAMAGE_ALL, Material.IRON_SWORD, 1.25f);
                InventoryIcon knockback = buildIcon(item, Enchantment.KNOCKBACK, Material.PISTON, 1.75f);
                InventoryIcon fireAspct = buildIcon(item, Enchantment.FIRE_ASPECT, Material.BLAZE_POWDER, 1.1f);
                InventoryIcon lootingYe = buildIcon(item, Enchantment.LOOT_BONUS_MOBS, Material.GOLD_INGOT, 1.05f);
                InventoryIcon sweepEdge = buildIcon(item, Enchantment.SWEEPING_EDGE, Material.IRON_AXE, 1.15f);
                inventory.setItem(Data.ENCH_SLOTS[0], sharpness.build());
                inventory.setItem(Data.ENCH_SLOTS[1], knockback.build());
                inventory.setItem(Data.ENCH_SLOTS[2], fireAspct.build());
                inventory.setItem(Data.ENCH_SLOTS[3], lootingYe.build());
                inventory.setItem(Data.ENCH_SLOTS[4], sweepEdge.build());
                break;
            case BOW:
                System.out.println("bow");
                InventoryIcon power = buildIcon(item, Enchantment.ARROW_DAMAGE, Material.TIPPED_ARROW, 1.25f);
                inventory.setItem(Data.ENCH_SLOTS[0], power.build());
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.PISTON));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.TIPPED_ARROW));
                inventory.setItem(Data.ENCH_SLOTS[3], new ItemStack(Material.CAULDRON));
                //inventory.setItem(Data.ENCH_SLOTS[4], new ItemStack(Material.ENDER_EYE));
                break;
            case CROSSBOW:
                System.out.println("crossbow");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.DIAMOND_SWORD));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.SUGAR));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.COMPARATOR));
                break;
            case TRIDENT:
                System.out.println("trident");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.STICK));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.TIPPED_ARROW));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.END_CRYSTAL));
                break;
            case TOOL:
                System.out.println("tool");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.FEATHER));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.STRING));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.GOLD_INGOT));
                break;
            case ARMOR:
                System.out.println("armor");
                // TODO check if boots/helmet
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.SHIELD));
                break;
            case HELMET:
                System.out.println("helmet");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.SHIELD));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.FEATHER));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.NAUTILUS_SHELL));
                inventory.setItem(Data.ENCH_SLOTS[3], new ItemStack(Material.FROSTED_ICE));
                break;
            case BOOTS:
                System.out.println("boots");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.SHIELD));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.DIAMOND_PICKAXE));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.HEART_OF_THE_SEA));
                break;
            default:
                System.out.println("no");
                for (int slot : Data.ENCH_SLOTS) {
                    inventory.setItem(slot, none);
                }
                break;
        }
    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public int getMaxStackSize() {
        return inventory.getMaxStackSize();
    }

    @Override
    public void setMaxStackSize(int i) {
        inventory.setMaxStackSize(i);
    }

    @Override
    public ItemStack getItem(int i) {
        return inventory.getItem(i);
    }

    @Override
    public void setItem(int i, ItemStack itemStack) {
        inventory.setItem(i, itemStack);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return inventory.addItem(itemStacks);
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... itemStacks) throws IllegalArgumentException {
        return inventory.removeItem(itemStacks);
    }

    @Override
    public ItemStack[] getContents() {
        return inventory.getContents();
    }

    @Override
    public void setContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        inventory.setContents(itemStacks);
    }

    @Override
    public ItemStack[] getStorageContents() {
        return inventory.getStorageContents();
    }

    @Override
    public void setStorageContents(ItemStack[] itemStacks) throws IllegalArgumentException {
        inventory.setStorageContents(itemStacks);
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return inventory.contains(material);
    }

    @Override
    public boolean contains(ItemStack itemStack) {
        return inventory.contains(itemStack);
    }

    @Override
    public boolean contains(Material material, int i) throws IllegalArgumentException {
        return inventory.contains(material, i);
    }

    @Override
    public boolean contains(ItemStack itemStack, int i) {
        return inventory.contains(itemStack, i);
    }

    @Override
    public boolean containsAtLeast(ItemStack itemStack, int i) {
        return inventory.containsAtLeast(itemStack, i);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return inventory.all(material);
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack itemStack) {
        return inventory.all(itemStack);
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return inventory.first(material);
    }

    @Override
    public int first(ItemStack itemStack) {
        return inventory.first(itemStack);
    }

    @Override
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        inventory.remove(material);
    }

    @Override
    public void remove(ItemStack itemStack) {
        inventory.remove(itemStack);
    }

    @Override
    public void clear(int i) {
        inventory.clear(i);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return inventory.getViewers();
    }

    @Override
    public InventoryType getType() {
        return inventory.getType();
    }

    @Override
    public InventoryHolder getHolder() {
        return inventory.getHolder();
    }

    @Override
    public ListIterator<ItemStack> iterator() {
        return inventory.iterator();
    }

    @Override
    public ListIterator<ItemStack> iterator(int i) {
        return inventory.iterator(i);
    }

    @Override
    public Location getLocation() {
        return inventory.getLocation();
    }
}

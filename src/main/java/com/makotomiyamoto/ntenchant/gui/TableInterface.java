package com.makotomiyamoto.ntenchant.gui;

import com.makotomiyamoto.ntenchant.Data;
import com.makotomiyamoto.ntenchant.meta.EnchantableType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
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
    public void render(EnchantableType type) {
        ItemStack none = new ItemStack(Material.AIR);
        switch (type) {
            case WEAPON:
                System.out.println("weapon");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.IRON_SWORD));
                inventory.setItem(Data.ENCH_SLOTS[1], new ItemStack(Material.PISTON));
                inventory.setItem(Data.ENCH_SLOTS[2], new ItemStack(Material.BLAZE_POWDER));
                inventory.setItem(Data.ENCH_SLOTS[3], new ItemStack(Material.GOLD_INGOT));
                inventory.setItem(Data.ENCH_SLOTS[4], new ItemStack(Material.IRON_AXE));
                break;
            case BOW:
                System.out.println("bow");
                inventory.setItem(Data.ENCH_SLOTS[0], new ItemStack(Material.TIPPED_ARROW));
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

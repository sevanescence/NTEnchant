package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.Data;
import com.makotomiyamoto.ntenchant.gui.TableInterface;
import com.makotomiyamoto.ntenchant.meta.EnchantableType;
import com.makotomiyamoto.ntenchant.meta.Eval;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public final class TableInteractHandler implements Listener {
    private final ItemStack none = new ItemStack(Material.AIR);
    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(Data.NAME)) {
            return;
        }
        event.setCancelled(true);

        ItemStack itemMounted;

        Inventory clickedWindow = event.getClickedInventory();
        assert clickedWindow != null;

        /* handles shift-clicking items */
        if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
            ItemStack clickedItem = event.getCurrentItem();
            InventoryView view = event.getView();
            if (event.getSlot() == Data.ITEM_SLOT && clickedWindow.equals(view.getTopInventory()) && clickedItem != null) {
                view.getBottomInventory().addItem(clickedItem);
                view.getTopInventory().setItem(Data.ITEM_SLOT, none);
            } else if (clickedWindow.equals(event.getView().getBottomInventory())) {
                view.getTopInventory().setItem(Data.ITEM_SLOT, clickedItem);
                view.getBottomInventory().setItem(event.getSlot(), none);
            }
        } else {
            /* handles manual clicking */
            Player player = (Player) event.getWhoClicked();
            InventoryView window = player.getOpenInventory();

            assert event.getCursor() != null;

            if (event.getCurrentItem() != null) {
                if (Eval.isEnchantable(clickedWindow.getItem(event.getSlot()))) {
                    //noinspection ConstantConditions
                    if (event.getCurrentItem().getItemMeta().getLore().get(0).contains("Item Level")) {
                        window.setCursor(event.getCurrentItem());
                        clickedWindow.setItem(event.getSlot(), none);
                    }
                }
            } else if (!event.getCursor().getType().equals(Material.AIR)) {
                clickedWindow.setItem(event.getSlot(), event.getCursor());
                window.setCursor(none);
            }
        }

        itemMounted = event.getView().getTopInventory().getItem(Data.ITEM_SLOT);

        /* handles rendering the enchanting table UI */
        EnchantableType type = EnchantableType.getByType(itemMounted);
        TableInterface window = TableInterface.loadFromWindow(event.getView().getTopInventory());
        window.render(type, itemMounted);

        /* determines if the SuccessfulTableClick event should fire */
        if (clickedWindow.equals(event.getView().getTopInventory())) {
            if (itemMounted == null) {
                System.out.println("there is no item mounted.");
                return;
            }
            // could not use Data.ENCH_SLOT indices for some reason.
            switch (event.getRawSlot()) {
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    ItemStack clickedEnch = event.getClickedInventory().getItem(event.getRawSlot());
                    if (clickedEnch == null)
                        break;
                    if (Eval.itemHitLevelCap(itemMounted)) {
                        break;
                    }
                    Map<Enchantment, Integer> enchantments = clickedEnch.getEnchantments();
                    Enchantment enchantment = null;
                    int level;
                    for (Enchantment ench : enchantments.keySet())
                        enchantment = ench;
                    assert enchantment != null;
                    level = enchantments.get(enchantment);
                    // TODO check if enchantment has reached its cap
                    // TODO check if player has enough levels
                    itemMounted.addEnchantment(enchantment, level);
                    window.render(type, itemMounted);
                    // TODO remove level cost
                    break;
                default:
                    break;
            }
        }

    }
}

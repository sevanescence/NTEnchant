package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.Data;
import com.makotomiyamoto.ntenchant.gui.TableInterface;
import com.makotomiyamoto.ntenchant.meta.EnchantableType;
import com.makotomiyamoto.ntenchant.meta.Eval;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

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
                    //TableInterface gui = TableInterface.loadFromWindow(window.getTopInventory());
                    window.setCursor(event.getCurrentItem());
                    clickedWindow.setItem(event.getSlot(), none);
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
        window.render(type);

    }
}

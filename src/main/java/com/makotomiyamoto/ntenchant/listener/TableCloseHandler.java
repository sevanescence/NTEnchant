package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class TableCloseHandler implements Listener {
    @EventHandler
    public void handleClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(Data.NAME)) {
            return;
        }
        Inventory topWindow = event.getView().getTopInventory(),
                    bottomWindow = event.getView().getBottomInventory();
        ItemStack slot = topWindow.getItem(Data.ITEM_SLOT);
        Player player = (Player) event.getPlayer();
        if (slot != null) {
            if (bottomWindow.firstEmpty() == -1) {
                event.getPlayer().getWorld().dropItem(player.getLocation().add(0,1,0), slot);
            } else {
                bottomWindow.addItem(slot);
            }
        }
    }
}

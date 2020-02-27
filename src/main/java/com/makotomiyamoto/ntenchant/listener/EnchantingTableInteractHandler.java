package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.Data;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public final class EnchantingTableInteractHandler implements Listener {
    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(Data.NAME)) {
            event.setCancelled(true);
            System.out.println("Fired!");
            event.getView().getTopInventory().setItem(Data.ITEM_SLOT, new ItemStack(Material.BEDROCK));
        }
    }
}

package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class EnchantingTableClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
            event.setCancelled(true);
            Inventory gui = Bukkit.createInventory(event.getPlayer(), 27, Data.NAME);
            ItemStack border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemStack slots  = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
            ItemMeta template = border.getItemMeta();
            assert template != null;
            template.setDisplayName("§a");
            border.setItemMeta(template);
            slots.setItemMeta(template);
            for (int value : Data.BORDER) {
                gui.setItem(value, border);
            } for (int value : Data.ENCH_SLOTS) {
                gui.setItem(value, slots);
            }
            event.getPlayer().openInventory(gui);
        }
    }
}

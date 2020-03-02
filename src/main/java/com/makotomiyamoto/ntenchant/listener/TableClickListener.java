package com.makotomiyamoto.ntenchant.listener;

import com.makotomiyamoto.ntenchant.gui.TableInterface;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class TableClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK))
            return;
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
            event.setCancelled(true);
            TableInterface window = new TableInterface(event.getPlayer());
            event.getPlayer().openInventory(window.build());
        }
    }
}

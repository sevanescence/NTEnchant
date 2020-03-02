package com.makotomiyamoto.ntenchant;

import com.makotomiyamoto.ntenchant.listener.TableClickListener;
import com.makotomiyamoto.ntenchant.listener.TableCloseHandler;
import com.makotomiyamoto.ntenchant.listener.TableInteractHandler;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new TableClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new TableInteractHandler(), this);
        this.getServer().getPluginManager().registerEvents(new TableCloseHandler(), this);
        // TODO fix the bug where clicking on the item slot with an item in hand will fucking destroy the item slot
    }

}

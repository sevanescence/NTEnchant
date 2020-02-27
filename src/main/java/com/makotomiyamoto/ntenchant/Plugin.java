package com.makotomiyamoto.ntenchant;

import com.makotomiyamoto.ntenchant.listener.EnchantingTableClickListener;
import com.makotomiyamoto.ntenchant.listener.EnchantingTableInteractHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new EnchantingTableClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new EnchantingTableInteractHandler(), this);
    }

}

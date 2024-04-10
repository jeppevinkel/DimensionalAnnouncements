package io.github.jeppevinkel.dimensionalannouncements;

import io.github.jeppevinkel.dimensionalannouncements.events.PlayerEvents;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class DimensionalAnnouncements extends JavaPlugin {
    public final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}

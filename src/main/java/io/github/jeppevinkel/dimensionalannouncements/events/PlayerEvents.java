package io.github.jeppevinkel.dimensionalannouncements.events;

import io.github.jeppevinkel.dimensionalannouncements.DimensionalAnnouncements;
import io.github.jeppevinkel.dimensionalannouncements.utils.WorldUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class PlayerEvents implements Listener {
    private final DimensionalAnnouncements plugin;

    public PlayerEvents(DimensionalAnnouncements plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        World destinationWorld = player.getWorld();
        ConfigurationSection worldConfig = config.getConfigurationSection("worlds." + destinationWorld.getName());
        String message = null;

        Logger.getGlobal().info(player.getName() + " changed world to " + destinationWorld.getName());

        if (worldConfig != null) {
            Logger.getGlobal().info("There is a config for this world!");
            message = worldConfig.getString("message");
            if (message == null || message.isEmpty()) {
                return;
            }
        } else {
            ConfigurationSection defaultConfig = config.getConfigurationSection("default");

            if (defaultConfig != null && defaultConfig.getBoolean("enabled")) {
                Logger.getGlobal().info("There is a default!");
                message = defaultConfig.getString("message");
            }
        }

        if (message != null) {
            Bukkit.broadcast(deserializeMessage(message, player, destinationWorld));
        }
    }

    private @NotNull Component deserializeMessage(@NotNull String message, @NotNull Player player, @NotNull World world) {

        return plugin.miniMessage.deserialize(message,
                Placeholder.component("player", Component.text(player.getName())),
                Placeholder.component("world", Component.text(WorldUtils.prettifyWorldName(world))),
                Placeholder.component("world_nobase", Component.text(WorldUtils.prettifyWorldName(world, false))),
                Placeholder.component("world_raw", Component.text(world.getName())));
    }
}

package io.github.jeppevinkel.dimensionalannouncements.utils;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.logging.Logger;

public class WorldUtils {
    public static @NotNull String prettifyWorldName(@NotNull World world) {
        return prettifyWorldName(world, false);
    }
    public static @NotNull String prettifyWorldName(@NotNull World world, boolean noBase) {
        return prettifyWorldName(world.getName(), noBase);
    }
    public static @NotNull String prettifyWorldName(@NotNull String worldName, boolean noBase){
        String[] nameParts = worldName.split("_");

        if (noBase && nameParts.length > 1) {
            nameParts = Arrays.copyOfRange(nameParts, 1, nameParts.length);
        }

        for(int i = 0; i < nameParts.length; i++){
            Logger.getGlobal().info(nameParts[i]);
            nameParts[i] = nameParts[i].substring(0, 1).toUpperCase() + nameParts[i].substring(1);
            Logger.getGlobal().info(nameParts[i]);
        }
        return String.join(" ", nameParts);
    }
}

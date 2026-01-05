package toutouchien.itemsadderbettercommands;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import toutouchien.itemsadderbettercommands.commands.ItemsAdderCommand;

@SuppressWarnings("UnstableApiUsage")
public final class ItemsAdderBetterCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            Commands registrar = commands.registrar();
            registrar.register(ItemsAdderCommand.get());
        });

        int pluginId = 28718;
        new Metrics(this, pluginId);
    }
}

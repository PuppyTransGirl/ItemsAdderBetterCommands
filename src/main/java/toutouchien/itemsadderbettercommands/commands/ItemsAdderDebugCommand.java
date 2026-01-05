package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import toutouchien.itemsadderbettercommands.commands.debug.*;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugCommand {
    private ItemsAdderDebugCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("debug")
                .then(ItemsAdderDebugBlockCommand.get())
                .then(ItemsAdderDebugCleanCacheCommand.get())
                .then(ItemsAdderDebugCustomModelDataCommand.get())
                .then(ItemsAdderDebugLiquidCommand.get())
                .then(ItemsAdderDebugSha1Command.get())
                .then(ItemsAdderDebugTagCommand.get())
                .build();
    }
}

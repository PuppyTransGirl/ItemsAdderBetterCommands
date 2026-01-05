package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import toutouchien.itemsadderbettercommands.commands.debug.ItemsAdderDebugBlockCommand;
import toutouchien.itemsadderbettercommands.commands.debug.ItemsAdderDebugCustomModelDataCommand;
import toutouchien.itemsadderbettercommands.commands.debug.ItemsAdderDebugSha1Command;
import toutouchien.itemsadderbettercommands.commands.debug.ItemsAdderDebugTagCommand;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderDebugCommand {
    private ItemsAdderDebugCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("debug")
                .then(ItemsAdderDebugBlockCommand.get())
                .then(ItemsAdderDebugCustomModelDataCommand.get())
                .then(ItemsAdderDebugSha1Command.get())
                .then(ItemsAdderDebugTagCommand.get())
                .build();
    }
}

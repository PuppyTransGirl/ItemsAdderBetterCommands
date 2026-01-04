package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class ItemsAdderCommand {
    private ItemsAdderCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("itemsadder")
                .then(ItemsAdderDebugCommand.get())
                .then(ItemsAdderDropCommand.get())
                .then(ItemsAdderDurabilityCommand.get())
                .then(ItemsAdderGiveCommand.get())
                .then(ItemsAdderInfoCommand.get())
                .then(ItemsAdderReloadCommand.get())
                .then(ItemsAdderRepairCommand.get())
                .build();
    }
}

package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

public class ItemsAdderDebugBlockCommand {
    private ItemsAdderDebugBlockCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("block")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iablock"))
                .executes(ctx -> {
                    Entity executor = ctx.getSource().getExecutor();
                    Bukkit.dispatchCommand(executor, "iablock");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}

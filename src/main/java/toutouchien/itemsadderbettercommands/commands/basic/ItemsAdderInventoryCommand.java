package toutouchien.itemsadderbettercommands.commands.basic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

@SuppressWarnings("UnstableApiUsage")
public class ItemsAdderInventoryCommand {
    private ItemsAdderInventoryCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("inventory")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.user.ia", true))
                .executes(ctx -> {
                    Entity executor = ctx.getSource().getExecutor();
                    Bukkit.dispatchCommand(executor, "ia");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}

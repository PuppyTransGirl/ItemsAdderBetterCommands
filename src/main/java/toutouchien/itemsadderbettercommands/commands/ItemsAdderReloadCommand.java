package toutouchien.itemsadderbettercommands.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

public class ItemsAdderReloadCommand {
    private ItemsAdderReloadCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("reload")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iazip"))
                .executes(ctx -> {
                    Entity executor = ctx.getSource().getExecutor();
                    Bukkit.dispatchCommand(executor, "iazip");

                    return Command.SINGLE_SUCCESS;
                })
                .build();
    }
}

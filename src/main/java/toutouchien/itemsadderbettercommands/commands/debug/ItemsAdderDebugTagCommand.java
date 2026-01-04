package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

public class ItemsAdderDebugTagCommand {
    private ItemsAdderDebugTagCommand() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("tag")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.admin.iatag"))
                .executes(ctx -> {
                    Entity executor = ctx.getSource().getExecutor();
                    Bukkit.dispatchCommand(executor, "iatag");

                    return Command.SINGLE_SUCCESS;
                })
                .then(Commands.literal("pretty-print")
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Bukkit.dispatchCommand(executor, "iatag pretty-print");

                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("show-in-chat")
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Bukkit.dispatchCommand(executor, "iatag show-in-chat");

                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("show-in-console")
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Bukkit.dispatchCommand(executor, "iatag show-in-console");

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}

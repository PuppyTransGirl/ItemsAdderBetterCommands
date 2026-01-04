package toutouchien.itemsadderbettercommands.commands.debug;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import toutouchien.itemsadderbettercommands.utils.CommandUtils;

public class ItemsAdderDebugSha1Command {
    private ItemsAdderDebugSha1Command() {
        throw new IllegalStateException("Command class");
    }

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("sha1")
                .requires(css -> CommandUtils.defaultRequirements(css, "ia.user.iasha1"))
                .then(Commands.literal("local")
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Bukkit.dispatchCommand(executor, "iasha1 local");

                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("online")
                        .executes(ctx -> {
                            Entity executor = ctx.getSource().getExecutor();
                            Bukkit.dispatchCommand(executor, "iasha1 online");

                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
